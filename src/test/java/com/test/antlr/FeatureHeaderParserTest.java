package com.test.antlr;

import com.test.antlr.domain.Feature;
import com.test.antlr.domain.Tag;
import com.test.antlr.parser.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class FeatureHeaderParserTest {

    private Parser parser = new Parser();

    @Test
    public void featureWithoutNameOrBody() {
        String emptyFeature = "Feature:";
        Feature feature = parser.parse(new ByteArrayInputStream(emptyFeature.getBytes()));
        Assertions.assertThat(feature).isNull();
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

    @Test
    public void featureWithTag() {
        String featureFile = "@Tag(value) \nFeature: feature";
        Feature feature = parser.parse(new ByteArrayInputStream(featureFile.getBytes()));
        Assertions.assertThat(feature.getFeatureName()).isEqualTo("feature");
        Assertions.assertThat(feature).isNotNull();
        Assertions.assertThat(feature.getTags()).isNotNull();
        Assertions.assertThat(feature.getTags()).hasSize(1);
        Tag tag = feature.getTags().get(0);
        Assertions.assertThat(tag.getType()).isEqualTo("Tag");
        Assertions.assertThat(tag.getValue()).isEqualTo("value");
    }
}
