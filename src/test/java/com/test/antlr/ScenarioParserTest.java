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
    public void scenarioWithSpacesAfterBackground() {
        String featureFile = featureBuilder
                .append("Background: \n When something\n\n")
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
    public void scenarioWithSpacesBeforeColon() {
        String featureFile = featureBuilder.append("Scenario    : scenario").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("scenario");
    }

    @Test
    public void scenarioWithAtInName() {
        String featureFile = featureBuilder.append("Scenario: scenario@mail.mail").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("scenario@mail.mail");
    }

    @Test
    public void scenarioWithQuotesInName() {
        String featureFile = featureBuilder.append("Scenario: some \"name\"").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("some \"name\"");
    }

    @Test
    public void scenarioWithTags() {
        String featureFile = featureBuilder.append("@Tag(value)\n@Tag2(value2)\nScenario: scenario").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getScenarios()).hasSize(1);
        Scenario scenario = feature.getScenarios().get(0);
        Assertions.assertThat(scenario.getName()).isEqualTo("scenario");
        Assertions.assertThat(scenario.getTags()).hasSize(2);
        Assertions.assertThat(scenario.getTags().get(0).getType()).isEqualTo("Tag");
        Assertions.assertThat(scenario.getTags().get(0).getValue()).isEqualTo("value");
        Assertions.assertThat(scenario.getTags().get(1).getType()).isEqualTo("Tag2");
        Assertions.assertThat(scenario.getTags().get(1).getValue()).isEqualTo("value2");
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
