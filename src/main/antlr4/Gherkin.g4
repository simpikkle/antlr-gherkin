grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* Tag* Feature content NewLine+ featureDescription*;
featureDescription: ~(Background | Scenario | ScenarioOutline) content NewLine+ ;

featureBody: background? scenario+;

background: (Space | NewLine)* Tag* Background content NewLine+ blockDescription* given;
blockDescription: ~(Given | When | Then) content NewLine+ ;
blockBody: given? when? then;

scenario: (Space | NewLine)* Tag* Scenario content NewLine+ blockDescription* blockBody;

given: (Space | NewLine)* Given stepText;

when: firstWhen moreWhen*;
firstWhen: (Space | NewLine)* When stepText;
moreWhen: (Space | NewLine)* (Or | And | When) stepText;

then: (Space | NewLine)* Then stepText;

stepText: content* (content | parameter)* (NewLine | EOF);

parameter: Space* '"' word '"' (Space | NewLine)+;

content: Space* word+;

word: Char (Char|Space)*;

// Tokens
Tag:  '@' WORD_CHAR (Space | NewLine)+;
Comment: Space* '#' .*? NewLine -> skip;

And: 'And ';
Or: 'Or ';
Given: 'Given ';
When: 'When ';
Then: 'Then ';
Background: 'Background: ';
Scenario: 'Scenario: ';
ScenarioOutline: 'Scenario Outline: ';
Feature: 'Feature: ';

Space : [ \t];
NewLine : '\r'? '\n' | '\r';
Char: WORD_CHAR;

fragment WORD_CHAR: ~[ \t\r\n"]+?;