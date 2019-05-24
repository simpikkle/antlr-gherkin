package com.test.antlr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.antlr.domain.Feature;
import com.test.antlr.parser.Parser;

import java.io.IOException;

public class Main {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        Parser parser = new Parser();
        Feature feature = parser.parseFile("sample.feature");
        System.out.println(OBJECT_MAPPER.writeValueAsString(feature));
    }
}
