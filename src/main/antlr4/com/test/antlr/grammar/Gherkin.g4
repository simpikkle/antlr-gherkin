grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* tags* Feature content NewLine+;

featureBody: background? scenario+;

background: (Space | NewLine)* tags* Background content? (NewLine | EOF) blockBody (NewLine | EOF);

blockBody: (given | when | or | then)*;

scenario: (Space | NewLine)* tags* Space* Scenario content (NewLine | EOF) blockBody;

// Annotations

tags: (Space | NewLine)* '@' content value? NewLine;

value: LBracket content RBracket;

// Keywords

given: (Space | NewLine)* Given step;

when: (Space | NewLine)* When step;

or: (Space | NewLine)* Or step;

then: (Space | NewLine)* Then step;

// Steps and data tables

step: stepContent row*;

stepContent: stepText (NewLine+ | EOF);

stepText: (content | parameter)*;

row: Space* '|' cell+ (NewLine+ | EOF);

cell: content '|';

parameter: '"' .*? '"';

// Common

content: Space* Char (Char|Space)*;

Comment: Space* '#' .*? NewLine -> channel(2);
EmptyLine: Space+ (NewLine | EOF) -> skip;

And: 'And ';
Or: 'Or ';
Given: 'Given ';
When: 'When ';
Then: 'Then ';
Background: 'Background:';
Scenario: 'Scenario:';
Feature: 'Feature: ';

Space : [ \t];
NewLine : '\r\n' | '\n';
Char: ~[ \t\r\n]+?;
LBracket: '(';
RBracket: ')';