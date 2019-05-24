package com.test.antlr.domain;

import java.util.List;

public class TableRow extends GherkinElement {

    private List<TableCell> cells;

    public TableRow(Location location) {
        super(location);
    }

    public List<TableCell> getCells() {
        return cells;
    }

    public void setCells(List<TableCell> cells) {
        this.cells = cells;
    }
}
