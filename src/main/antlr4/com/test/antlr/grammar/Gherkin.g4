grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* tags* Feature content NewLine+;

featureBody: background? scenario+;

background: (Space | NewLine)* tags* Background content? (NewLine | EOF) blockBody (NewLine | EOF);

blockBody: (given | when | or | then)*;

scenario: (Space | NewLine)* tags* Space* Scenario content (NewLine | EOF) blockBody;

// Annotations

tags: (Space | NewLine)* '@' content value? NewLine;

value: '(' content ')';

// Keywords

given: (Space | NewLine)* Given step;

when: (Space | NewLine)* When step;

or: (Space | NewLine)* Or step;

then: (Space | NewLine)* Then step;

// Steps and data tables

step: stepText (NewLine+ | EOF) row*;

stepText: (content | parameter)*;

row: Space* '|' cell+ (NewLine+ | EOF);

cell: content '|';

parameter: '"' anyText '"';

anyText: .*?;

// Common

content: Space* Char (Char|Space)*;

Comment: Space* '#' .*? NewLine -> channel(2);
EmptyLine: Space+ (NewLine | EOF) -> skip;

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
NewLine : '\r\n' | '\n';
Char: ~[ \t\r\n]+?;