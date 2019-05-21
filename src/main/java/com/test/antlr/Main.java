package com.test.antlr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.antlr.domain.Feature;
import com.test.antlr.parser.Parser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Main {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        List<Feature> features = parser.parseFile("sample.feature");;
        System.out.println(OBJECT_MAPPER.writeValueAsString(features));
    }
}
