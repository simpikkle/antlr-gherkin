grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* Tag* Feature content NewLine+ featureDescription*;
featureDescription: ~(Background | Scenario | ScenarioOutline) content NewLine+ ;

featureBody: background? (scenario | outlineScenario)+;

background: (Space | NewLine)* Tag* Background content NewLine+ blockDesc* given;
blockDesc: ~(Given) content NewLine+ ;
blockBody: given? when? then;

scenario: (Space | NewLine)* Tag* Scenario content NewLine+  blockDesc* blockBody;
outlineScenario: (Space | NewLine)* Tag* ScenarioOutline content NewLine+ blockDesc* blockBody;

given: firstGiven moreGiven*;
firstGiven: (Space | NewLine)* Given ruleBody;
moreGiven: (Space | NewLine)* (And | But | Given) ruleBody;

when: firstWhen moreWhen*;
firstWhen: (Space | NewLine)* When ruleBody;
moreWhen: (Space | NewLine)* (And | But | When) ruleBody;

then: firstThen moreThen*;
firstThen: (Space | NewLine)* Then ruleBody;
moreThen: (Space | NewLine)* (And | But | Then) ruleBody;

ruleBody: ruleText (NewLine | EOF);
ruleText: content;

content: Space* Word (Word|Space)*;

// Tokens
Tag:  '@' WD (Space | NewLine)+;
Comment: Space* '#' .*? NewLine -> skip;

And: 'And ';
But: 'But ';
Given: 'Given ';
When: 'When ';
Then: 'Then ';
Background: 'Background: ';
Scenario: 'Scenario: ';
ScenarioOutline: 'Scenario Outline: ';
Feature: 'Feature: ';

Space : [ \t];
NewLine : '\r'? '\n' | '\r';
Word: WD;

fragment WD: ~[ \t\r\n]+?;
