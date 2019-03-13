public class Feature {

    private String featureName;

    private String description;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "featureName='" + featureName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
