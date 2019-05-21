package com.test.antlr.domain;

import java.util.ArrayList;
import java.util.List;

public class Scenario extends GherkinElement {

    private final List<Step> steps = new ArrayList<>();

    private List<Tag> tags;

    private String name;

    public Scenario(Location location, String text) {
        super(location, text);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
