package com.test.antlr.parser;

import com.test.antlr.domain.*;
import com.test.antlr.grammar.GherkinParser;
import org.antlr.v4.runtime.RuleContext;

import java.util.stream.Collectors;

public class ContextBuilder {

    public static Step buildStep(GherkinParser.StepContext stepContext, Keyword keyword) {
        Step step = new Step(new Location(stepContext), stepContext.getText());
        step.setName(stepContext.stepText().getText());
        step.setKeyword(keyword);
        step.setParameters(stepContext.stepText().parameter()
                .stream()
                .map(GherkinParser.ParameterContext::word)
                .map(RuleContext::getText)
                .collect(Collectors.toList()));
        step.setRows(stepContext.row()
                .stream()
                .map(ContextBuilder::buildRow)
                .collect(Collectors.toList()));
        return step;
    }

    private static Row buildRow(GherkinParser.RowContext rowContext) {
        Row row = new Row(new Location(rowContext), rowContext.getText());
        row.setCells(rowContext.cell()
                .stream()
                .map(cellContext -> cellContext.content().getText())
                .collect(Collectors.toList()));
        return row;
    }

    public static Scenario buildScenario(GherkinParser.ScenarioContext ctx) {
        Location location = new Location(ctx);
        Scenario scenario = new Scenario(location, ctx.getText());
        scenario.setName(ctx.content().getText().trim());
        scenario.setTags(ctx.tags().stream()
                .map(ContextBuilder::buildTag)
                .collect(Collectors.toList()));
        return scenario;
    }

    private static Tag buildTag(GherkinParser.TagsContext tagContext) {
        Tag tag = new Tag();
        tag.setType(tagContext.content().getText());
        tag.setValue(tagContext.value().content().getText());
        return tag;
    }

    // TODO generify both to extend one node
    public static Scenario buildBackground(GherkinParser.BackgroundContext ctx) {
        Location location = new Location(ctx);
        Scenario scenario = new Scenario(location, ctx.getText());
        scenario.setName("Background");
        scenario.setTags(ctx.tags().stream()
                .map(ContextBuilder::buildTag)
                .collect(Collectors.toList()));
        return scenario;
    }
}
