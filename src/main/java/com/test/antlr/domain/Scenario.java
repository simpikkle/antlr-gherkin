package com.test.antlr.domain;

import com.test.antlr.grammar.GherkinParser;

import java.util.ArrayList;
import java.util.List;

public class Scenario extends GherkinElement {

    private final String name;

    private final List<Step> steps = new ArrayList<>();

    public Scenario(GherkinParser.ScenarioContext ctx) {
        super(new Location(ctx), ctx.getText());
        name = ctx.content().getText().trim();
    }

    public Scenario(GherkinParser.BackgroundContext ctx) {
        super(new Location(ctx), ctx.getText());
        name = "Background";
    }

    public String getName() {
        return name;
    }

    public List<Step> getSteps() {
        return steps;
    }
}
