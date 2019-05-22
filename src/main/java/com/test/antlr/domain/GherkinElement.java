package com.test.antlr.domain;

import java.util.List;

public abstract class GherkinElement {

    private final Location location;

    private String text;

    private List<String> comments;

    protected GherkinElement(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<String> getComments() {
        return comments;
    }
}
