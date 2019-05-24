package com.test.antlr;

import com.test.antlr.domain.Feature;
import com.test.antlr.parser.Parser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

public class ParserTest {

    Parser parser = new Parser();

    @Test
    public void testEmptyFile() {
        String emptyFeature = "";
        Feature feature = parser.parse(new ByteArrayInputStream(emptyFeature.getBytes()));
        Assertions.assertThat(feature).isNull();
    }
}
