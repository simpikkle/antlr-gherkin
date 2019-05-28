package com.test.antlr.domain;

import java.util.ArrayList;
import java.util.List;

public class Feature {

    private List<Tag> tags;

    private String featureName;

    private String description;

    private Scenario background;

    private List<Scenario> scenarios = new ArrayList<>();

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

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public void setScenarios(List<Scenario> scenarios) {
        this.scenarios = scenarios;
    }

    public void setBackground(Scenario background) {
        this.background = background;
    }

    public Scenario getBackground() {
        return background;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
