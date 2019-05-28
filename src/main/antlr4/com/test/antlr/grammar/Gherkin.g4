grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* tags* Feature Space* content NewLine+;

featureBody: background? scenario+;

background: (Space | NewLine)* tags* Background Space* content? (NewLine | EOF) blockBody (NewLine | EOF);

blockBody: (given | when | or | then)*;

scenario: (Space | NewLine)* tags* Space* Scenario Space* content (NewLine | EOF) blockBody;

// Annotations

tags: (Space | NewLine)* '@' content value? NewLine;

value: LBracket content RBracket;

// Keywords

given: (Space | NewLine)* Given step;

when: (Space | NewLine)* When step;

or: (Space | NewLine)* Or step;

then: (Space | NewLine)* Then step;

// Steps and data tables

step: Space* stepContent row*;

stepContent: stepText (NewLine+ | EOF);

stepText: (content | Space | parameter)*;

row: Space* '|' cell+ (NewLine+ | EOF);

cell: Space* content '|';

parameter: Quote .*? Quote;

// Common

content: Char (Char|Space)*;

Comment: Space* '#' .*? (NewLine | EOF) -> channel(2);
EmptyLine: NewLine Space+ (NewLine | EOF) -> skip;

And: 'And ';
Or: 'Or ';
Given: 'Given ';
When: 'When ';
Then: 'Then ';
Background: 'Background:';
Scenario: 'Scenario:';
Feature: 'Feature:';

Space : [ \t];
NewLine : '\r\n' | '\n';
Quote: '"';
Char: ~[ \t\r\n]+?;
LBracket: '(';
RBracket: ')';