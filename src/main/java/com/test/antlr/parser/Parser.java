package com.test.antlr.parser;

import com.test.antlr.domain.Feature;
import com.test.antlr.grammar.GherkinLexer;
import com.test.antlr.grammar.GherkinParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;

public class Parser {

    public List<Feature> parseFile(String fileName) {
        GherkinLexer lexer = new GherkinLexer(getFile(fileName));
        CollectorTokenSource tockenSource = new CollectorTokenSource(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tockenSource);
        GherkinParser parser = new GherkinParser(commonTokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        FeatureListener listener = new FeatureListener();
        ParseTree parseTree = parser.feature();
        walker.walk(listener, parseTree);
        return listener.getFeatures();
    }

    private static CharStream getFile(String fileName) {
        InputStream featureStream = ClassLoader.getSystemResourceAsStream(fileName);
        Objects.requireNonNull(featureStream);
        try {
            return new ANTLRInputStream(featureStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
