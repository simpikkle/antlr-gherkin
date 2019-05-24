package com.test.antlr.domain;

import java.util.List;

public class Step extends GherkinElement {

    private String name;

    private List<String> parameters;

    private List<TableRow> rows;

    private Keyword keyword;

    public Step(Location location) {
        super(location);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    public List<String> getParameters() {
        return parameters;
    }

    public List<TableRow> getRows() {
        return rows;
    }

    public void setRows(List<TableRow> tableRows) {
        this.rows = rows;
    }

    public void setKeyword(Keyword keyword) {
        this.keyword = keyword;
    }

    public Keyword getKeyword() {
        return keyword;
    }
}
