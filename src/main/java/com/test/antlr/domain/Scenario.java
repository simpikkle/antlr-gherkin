package com.test.antlr.domain;

import java.util.ArrayList;
import java.util.List;

public class Scenario extends GherkinElement {

    private String name;

    private List<Step> steps = new ArrayList<>();

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

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
