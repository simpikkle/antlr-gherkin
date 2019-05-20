package com.test.antlr.domain;

import org.antlr.v4.runtime.ParserRuleContext;

public class Location {

    private final int line;

    public Location(ParserRuleContext context) {
        this.line = context.getStart().getLine();
    }

    public int getLine() {
        return line;
    }
}
