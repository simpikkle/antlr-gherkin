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
import java.util.Objects;

public class Parser {

    public Feature parseFile(String fileName) {
        InputStream featureStream = ClassLoader.getSystemResourceAsStream(fileName);
        return parse(featureStream);
    }

    public Feature parse(InputStream inputStream) {
        GherkinLexer lexer = new GherkinLexer(getCharStream(inputStream));
        CollectorTokenSource tokenSource = new CollectorTokenSource(lexer);
        CommonTokenStream commonTokenStream = new CommonTokenStream(tokenSource);
        GherkinParser parser = new GherkinParser(commonTokenStream);

        FeatureListener listener = new FeatureListener();
        ParseTree parseTree = parser.feature();
        ParseTreeWalker.DEFAULT.walk(listener, parseTree);
        return listener.getFeature();
    }

    private static CharStream getCharStream(InputStream inputStream) {
        Objects.requireNonNull(inputStream);
        try {
            return new ANTLRInputStream(inputStream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
