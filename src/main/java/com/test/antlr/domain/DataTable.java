package com.test.antlr.domain;

import java.util.List;

public class DataTable extends GherkinElement {

    private final List<TableRow> rows;

    public DataTable(Location location, List<TableRow> rows) {
        super(location);
        this.rows = rows;
    }

    public List<TableRow> getRows() {
        return rows;
    }
}
