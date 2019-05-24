package com.test.antlr.domain;

public enum Keyword {
    GIVEN("Given"),
    WHEN("When"),
    THEN("Then"),
    OR("Or")
    ;

    private final String name;

    Keyword(String name) {

        this.name = name;
    }

    public String getName() {
        return name;
    }
}
