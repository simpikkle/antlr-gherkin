import domain.Feature;
import domain.Location;
import domain.Scenario;

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
        currentScenario = new Scenario(new Location(ctx), ctx.getText());
        currentScenario.setName(ctx.content().getText().trim());
    }

    @Override
    public void enterBackground(GherkinParser.BackgroundContext ctx) {
        currentScenario = new Scenario(new Location(ctx), ctx.getText());
        currentScenario.setName("Background");
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
        currentScenario.getSteps().add(ContextBuilder.buildStep(ctx.step()));
    }

    @Override
    public void enterOr(GherkinParser.OrContext ctx) {
        currentScenario.getSteps().add(ContextBuilder.buildStep(ctx.step()));
    }

    @Override
    public void enterThen(GherkinParser.ThenContext ctx) {
        currentScenario.getSteps().add(ContextBuilder.buildStep(ctx.step()));
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
