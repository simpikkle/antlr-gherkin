package com.test.antlr.domain;

public class TableCell extends GherkinElement {

    private final String value;

    public TableCell(Location location, String value) {
        super(location);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
