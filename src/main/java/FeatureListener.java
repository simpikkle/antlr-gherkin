import org.antlr.v4.runtime.RuleContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        currentScenario = new Scenario();
        currentScenario.setName(ctx.content().getText().trim());
    }

    @Override
    public void exitScenario(GherkinParser.ScenarioContext ctx) {
        currentFeature.getScenarios().add(currentScenario);
    }

    @Override
    public void enterWhen(GherkinParser.WhenContext ctx) {
        Step step = new Step();
        GherkinParser.StepTextContext stepContext = ctx.firstWhen().stepText();
        step.setName(stepContext.getText());
        step.setParameters(stepContext.parameter()
                .stream()
                .map(GherkinParser.ParameterContext::word)
                .map(RuleContext::getText)
                .collect(Collectors.toList()));
        currentScenario.getSteps().add(step);
    }

    @Override
    public void enterThen(GherkinParser.ThenContext ctx) {
        Step step = new Step();
        step.setName(ctx.stepText().getText());
        currentScenario.getSteps().add(step);
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
