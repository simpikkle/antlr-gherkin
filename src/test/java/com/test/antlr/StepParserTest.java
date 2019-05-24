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

}
