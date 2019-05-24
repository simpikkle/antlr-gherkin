package com.test.antlr.parser;

import com.test.antlr.domain.Feature;
import com.test.antlr.domain.Keyword;
import com.test.antlr.domain.Scenario;
import com.test.antlr.grammar.GherkinBaseListener;
import com.test.antlr.grammar.GherkinParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeatureListener extends GherkinBaseListener {

    public static final String EOF = "<EOF>";
    private static final Logger LOGGER = LoggerFactory.getLogger(FeatureListener.class);
    private Feature feature;

    private Scenario currentScenario;

    @Override
    public void enterFeature(GherkinParser.FeatureContext ctx) {
        if (isEmpty(ctx.getText())) {
            LOGGER.info("Empty file");
            return;
        }
        LOGGER.info("Start parsing feature");
        feature = new Feature();
    }

    @Override
    public void exitFeature(GherkinParser.FeatureContext ctx) {
        LOGGER.info("Exit parsing feature");
    }

    @Override
    public void enterFeatureHeader(GherkinParser.FeatureHeaderContext ctx) {
        if (isEmpty(ctx.getText())) {
            LOGGER.info("Empty feature header");
            return;
        }
        feature.setFeatureName(ctx.content().getText().trim());
    }

    @Override
    public void enterFeatureDescription(GherkinParser.FeatureDescriptionContext ctx) {
        if (isEmpty(ctx.getText())) {
            LOGGER.info("Empty feature description");
            return;
        }
        feature.setDescription(ctx.content().getText().trim());
    }

    @Override
    public void enterScenario(GherkinParser.ScenarioContext ctx) {
        if (isEmpty(ctx.getText())) {
            LOGGER.info("Empty scenario");
            throw new IllegalArgumentException("Cannot parse empty scenario");
        }
        LOGGER.info("Start parsing scenario: \n" + ctx.getText());
        currentScenario = ContextBuilder.buildScenario(ctx);
    }

    @Override
    public void enterBackground(GherkinParser.BackgroundContext ctx) {
        currentScenario = ContextBuilder.buildBackground(ctx);
    }

    @Override
    public void exitBackground(GherkinParser.BackgroundContext ctx) {
        feature.setBackground(currentScenario);
    }

    @Override
    public void exitScenario(GherkinParser.ScenarioContext ctx) {
        feature.getScenarios().add(currentScenario);
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

    public Feature getFeature() {
        return feature;
    }

    private boolean isEmpty(String text) {
        return text == null || text.isEmpty() || text.equals(EOF);
    }
}
