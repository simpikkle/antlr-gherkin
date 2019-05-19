import domain.Location;
import domain.Row;
import domain.Step;
import org.antlr.v4.runtime.RuleContext;

import java.util.stream.Collectors;

public class ContextBuilder {

    public static Step buildStep(GherkinParser.StepContext stepContext) {
        Step step = new Step(new Location(stepContext), stepContext.getText());
        step.setName(stepContext.stepText().getText());
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
}
