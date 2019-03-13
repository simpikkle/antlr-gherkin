import java.util.ArrayList;
import java.util.List;

public class FeatureListener extends GherkinBaseListener {

    private List<Feature> features = new ArrayList<>();

    private Feature current;

    @Override
    public void enterFeature(GherkinParser.FeatureContext ctx) {
        current = new Feature();
    }

    @Override
    public void exitFeature(GherkinParser.FeatureContext ctx) {
        features.add(current);
    }

    @Override
    public void enterFeatureHeader(GherkinParser.FeatureHeaderContext ctx) {
        current.setFeatureName(ctx.content().getText().trim());
    }

    @Override
    public void enterFeatureDescription(GherkinParser.FeatureDescriptionContext ctx) {
        current.setDescription(ctx.content().getText().trim());
    }

    public List<Feature> getFeatures() {
        return features;
    }
}
