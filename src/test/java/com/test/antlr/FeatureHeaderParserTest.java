package com.test.antlr;

import com.test.antlr.domain.Feature;
import com.test.antlr.parser.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class FeatureHeaderParserTest {

    private Parser parser = new Parser();

    @Test
    public void featureWithoutName() {
        String emptyFeature = "Feature:";
        Feature feature = parser.parse(new ByteArrayInputStream(emptyFeature.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getFeatureName()).isNull();
    }

    @Test
    public void featureWithSpaceWithoutName() {
        String featureFile = "Feature: ";
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getFeatureName()).isEmpty();
    }

    @Test
    public void featureWithName() {
        String featureFile = "Feature: feature";
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getFeatureName()).isEqualTo("feature");
    }
}
