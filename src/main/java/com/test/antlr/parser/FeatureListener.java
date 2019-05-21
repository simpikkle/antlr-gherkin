package com.test.antlr.parser;

import com.test.antlr.domain.Feature;
import com.test.antlr.domain.Scenario;
import com.test.antlr.grammar.GherkinBaseListener;
import com.test.antlr.grammar.GherkinParser;

import java.util.ArrayList;
import java.util.List;

public class FeatureListener extends GherkinBaseListener {

    private List<Feature> features = new ArrayList<>();

    private Feature currentFeature;

    private Scenario currentScenario;

    @Override
    public void enterFeature(GherkinParser.FeatureContext ctx) {
        currentFeature = new Feature();
    }

    @Override
    public void exitFeature(GherkinParser.FeatureContext ctx) {
        features.add(currentFeature);
    }

    @Override
    public void enterFeatureHeader(GherkinParser.FeatureHeaderContext ctx) {
        currentFeature.setFeatureName(ctx.content().getText().trim());
    }

    @Override
    public void enterFeatureDescription(GherkinParser.FeatureDescriptionContext ctx) {
        currentFeature.setDescription(ctx.content().getText().trim());
    }

    @Override
    public void enterScenario(GherkinParser.ScenarioContext ctx) {
        currentScenario = new Scenario(ctx);
    }

    @Override
    public void enterBackground(GherkinParser.BackgroundContext ctx) {
        currentScenario = new Scenario(ctx);
    }

    @Override
    public void exitBackground(GherkinParser.BackgroundContext ctx) {
        currentFeature.setBackground(currentScenario);
    }

    @Override
    public void exitScenario(GherkinParser.ScenarioContext ctx) {
        currentFeature.getScenarios().add(currentScenario);
    }

    @Override
    public void enterWhen(GherkinParser.WhenContext ctx) {
        currentScenario.getSteps().add(ContextBuilder.buildStep(ctx.step(), Keyword.WHEN));
    }

    @Override
    public void enterOr(GherkinParser.OrContext ctx) {
        currentScenario.getSteps().add(ContextBuilder.buildStep(ctx.step(), Keyword.OR));
    }

    @Override
    public void enterThen(GherkinParser.ThenContext ctx) {
        currentScenario.getSteps().add(ContextBuilder.buildStep(ctx.step(), Keyword.THEN));
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
