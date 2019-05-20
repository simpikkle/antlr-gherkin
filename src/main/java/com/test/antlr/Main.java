package com.test.antlr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.antlr.domain.Feature;
import com.test.antlr.grammar.GherkinLexer;
import com.test.antlr.grammar.GherkinParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class Main {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        GherkinLexer lexer = new GherkinLexer(getFile("sample.feature"));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        GherkinParser parser = new GherkinParser(commonTokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        FeatureListener listener = new FeatureListener();
        ParseTree parseTree = parser.feature();
        walker.walk(listener, parseTree);
        List<Feature> features = listener.getFeatures();
        System.out.println(OBJECT_MAPPER.writeValueAsString(features));
    }

    private static CharStream getFile(String fileName) throws IOException {
        InputStream featureStream = ClassLoader.getSystemResourceAsStream(fileName);
        Objects.requireNonNull(featureStream);
        return new ANTLRInputStream(featureStream);
    }
}
