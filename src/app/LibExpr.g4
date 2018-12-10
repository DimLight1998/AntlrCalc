grammar LibExpr;
import CommonLexerRules;

@header {
package app;
}

prog: stat+;
stat: expr NEWLINE  # printExpr
    | ID '=' expr NEWLINE # assign
    | NEWLINE # blank
    ;

expr: expr op=(MUL | DIV) expr # mulDiv
    | expr op=(ADD | SUB) expr # addSub
    | INT # int
    | ID # id
    | '(' expr ')' # parens
    ;
