package com.test.antlr;

import com.test.antlr.domain.Feature;
import com.test.antlr.domain.Step;
import com.test.antlr.parser.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class StepParserTest {

    private Parser parser = new Parser();

    private StringBuilder featureBuilder = new StringBuilder("Feature: feature\nScenario: scenario\n");

    @Test
    public void simpleStep() {
        String featureFile = featureBuilder.append("Then do something").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));

        Assertions.assertThat(feature.getScenarios().get(0).getSteps()).hasSize(1);
        Step step = feature.getScenarios().get(0).getSteps().get(0);
        Assertions.assertThat(step.getStepText()).isEqualTo("do something");
        Assertions.assertThat(step.getText()).isEqualTo("Then do something");
    }

    @Test
    public void simpleParameterStep() {
        String featureFile = featureBuilder.append("Then field \"fieldName\" must contain value").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));

        Assertions.assertThat(feature.getScenarios().get(0).getSteps()).hasSize(1);
        Step step = feature.getScenarios().get(0).getSteps().get(0);
        Assertions.assertThat(step.getStepText()).isEqualTo("field \"fieldName\" must contain value");
        Assertions.assertThat(step.getParameters()).hasSize(1);
        Assertions.assertThat(step.getParameters().get(0)).isEqualTo("fieldName");
    }

    @Test
    public void multiWordParameter() {
        String featureFile = featureBuilder.append("Then field \"field name\" must contain value").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));

        Assertions.assertThat(feature.getScenarios().get(0).getSteps()).hasSize(1);
        Step step = feature.getScenarios().get(0).getSteps().get(0);
        Assertions.assertThat(step.getParameters()).hasSize(1);
        Assertions.assertThat(step.getParameters().get(0)).isEqualTo("field name");
    }

    @Test
    public void regexParameter() {
        String featureFile = featureBuilder.append("Then field must match \"^([a-zA-z]+@[a-zA-z]{3,}.[com|net])$\"").toString();
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));

        Assertions.assertThat(feature.getScenarios().get(0).getSteps()).hasSize(1);
        Step step = feature.getScenarios().get(0).getSteps().get(0);
        Assertions.assertThat(step.getParameters()).hasSize(1);
        Assertions.assertThat(step.getParameters().get(0)).isEqualTo("^([a-zA-z]+@[a-zA-z]{3,}.[com|net])$");
    }

}
