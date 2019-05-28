package com.test.antlr;

import com.test.antlr.domain.Feature;
import com.test.antlr.domain.Scenario;
import com.test.antlr.parser.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ScenarioParserTest {

    private Parser parser = new Parser();

    private StringBuilder featureBuilder = new StringBuilder("Feature: feature\n");

    @Test
    public void scenarioWithBackground() {
        String featureFile = featureBuilder
                .append("Background:\n When something\n\n")
                .append("Scenario: scenario\n Then something").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getBackground()).isNotNull();
        Assertions.assertThat(feature.getBackground().getSteps()).hasSize(1);

        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("scenario");
    }

    @Test
    public void scenarioWithName() {
        String featureFile = featureBuilder.append("Scenario: scenario").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("scenario");
    }

    @Test
    public void scenarioWithStep() {
        String featureFile = featureBuilder.append("Scenario: scenario \n  When something \n  Then something").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("scenario");
        Assertions.assertThat(scenario.getSteps()).hasSize(2);
        Assertions.assertThat(scenario.getSteps().get(0).getStepText()).isEqualTo("something");
        Assertions.assertThat(scenario.getSteps().get(1).getStepText()).isEqualTo("something");
    }
}
