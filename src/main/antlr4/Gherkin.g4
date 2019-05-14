grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* Tag* Feature content NewLine+ featureDescription*;
featureDescription: ~(Background | Scenario | ScenarioOutline) content NewLine+ ;

featureBody: background? scenario+;

background: (Space | NewLine)* Tag* Background content NewLine+ blockDescription* blockBody NewLine+;

blockDescription: ~(Given | When | Then) content NewLine+ ;
blockBody: given* when* or* then*;

scenario: (Space | NewLine)* Tag* Scenario content NewLine+ blockDescription* blockBody;

given: (Space | NewLine)* Given stepText;

when: (Space | NewLine)* When stepText;

or: (Space | NewLine)* Or stepText;

then: (Space | NewLine)* Then stepText;

stepText: content* (content | parameter)* (NewLine | EOF);

parameter: '"' word '"';

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