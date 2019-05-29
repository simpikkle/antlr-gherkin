grammar Gherkin;

feature: featureHeader featureBody;

featureHeader: (Space | NewLine)* tags* Feature Space* content NewLine+;

featureBody: background? scenario+;

background: (Space | NewLine)* tags* Background Space* content? (NewLine | EOF) blockBody (NewLine | EOF);

blockBody: (given | when | or | then)*;

scenario: (Space | NewLine)* tags* Space* Scenario Space* content (NewLine | EOF) blockBody;

// Annotations

tags: (Space | NewLine)* At anyText value? Space* NewLine;

anyText: .*?;

value: LBracket content RBracket;

// Keywords

given: (Space | NewLine)* Given step;

when: (Space | NewLine)* When step;

or: (Space | NewLine)* Or step;

then: (Space | NewLine)* Then step;

// Steps and data tables

step: Space* stepContent row*;

stepContent: stepText (NewLine+ | EOF);

stepText: (contentNoQuotes | Space | parameter)*;

row: Space* Pipe cell+ (NewLine+ | EOF);

cell: Space* contentNoPipes Pipe;

parameter: Quote anyText Quote;

// Common

contentNoQuotes: (Char|LBracket) (Char|LBracket|RBracket|At|Pipe|Space)*;

contentNoPipes: (Char|LBracket) (Char|LBracket|RBracket|At|Quote|Space)*;

content: (Char|LBracket) (Char|LBracket|RBracket|At|Quote|Pipe|Space)*;

Comment: Space* '#' .*? (NewLine | EOF) -> channel(2);
EmptyLine: NewLine Space+ (NewLine | EOF) -> skip;

And: 'And ';
Or: 'Or ';
Given: 'Given ';
When: 'When ';
Then: 'Then ';
Background: 'Background' COLON;
Scenario: 'Scenario' COLON;
Feature: 'Feature' COLON;

Space : [ \t];
NewLine : '\r\n' | '\n';
Quote: '"';
LBracket: '(';
RBracket: ')';
At: '@';
Pipe: '|';
Char: ~[ \t\r\n()]+?;

fragment COLON: ':';