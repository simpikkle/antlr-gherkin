package com.test.antlr.parser;

import com.test.antlr.domain.*;
import com.test.antlr.grammar.GherkinParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.RuleContext;

import java.util.stream.Collectors;

import static com.test.antlr.parser.FeatureListener.EOF;

public class ContextBuilder {

    public static Step buildStep(GherkinParser.StepContext stepContext, Keyword keyword) {
        Step step = new Step(buildLocation(stepContext));
        // TODO remove replacement
        step.setText(keyword.getName() + " " + stepContext.getText().replaceAll(EOF, ""));
        // TODO remove trim
        step.setStepText(stepContext.stepText().getText().trim());
        step.setKeyword(keyword);
        step.setParameters(stepContext.stepText().parameter()
                .stream()
                .map(GherkinParser.ParameterContext::content)
                .map(RuleContext::getText)
                .collect(Collectors.toList()));
        step.setRows(stepContext.row()
                .stream()
                .map(ContextBuilder::buildRow)
                .collect(Collectors.toList()));
        return step;
    }

    private static Location buildLocation(ParserRuleContext context) {
        return new Location(context.getStart().getLine(), context.getStart().getCharPositionInLine());
    }

    private static TableRow buildRow(GherkinParser.RowContext rowContext) {
        TableRow tableRow = new TableRow(buildLocation(rowContext));
        tableRow.setText(rowContext.getText());
        tableRow.setCells(rowContext.cell()
                .stream()
                .map(ContextBuilder::buildCell)
                .collect(Collectors.toList()));
        return tableRow;
    }

    private static TableCell buildCell(GherkinParser.CellContext cellContext) {
        return new TableCell(buildLocation(cellContext), cellContext.content().getText().trim());
    }

    public static Scenario buildScenario(GherkinParser.ScenarioContext ctx) {
        Scenario scenario = new Scenario(buildLocation(ctx));
        // TODO remove replacement
        scenario.setText(ctx.getText().replaceAll(EOF, ""));
        // TODO remove trim
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

    public static Scenario buildBackground(GherkinParser.BackgroundContext ctx) {
        Scenario scenario = new Scenario(buildLocation(ctx));
        scenario.setText(ctx.getText());
        scenario.setTags(ctx.tags().stream()
                .map(ContextBuilder::buildTag)
                .collect(Collectors.toList()));
        return scenario;
    }
}
