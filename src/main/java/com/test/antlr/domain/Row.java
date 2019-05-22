package com.test.antlr.domain;

import java.util.List;

public class Row extends GherkinElement {

    private List<String> cells;

    public Row(Location location) {
        super(location);
    }

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }
}
