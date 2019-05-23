package com.test.antlr.parser;

import com.test.antlr.grammar.GherkinLexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenFactory;
import org.antlr.v4.runtime.TokenSource;

import java.util.ArrayList;
import java.util.List;

public class CollectorTokenSource implements TokenSource {

    private final GherkinLexer lexer;

    private List<Token> collectedTokens = new ArrayList<>();

    public CollectorTokenSource(GherkinLexer lexer) {
        this.lexer = lexer;
    }

    @Override
    public Token nextToken() {
        Token token = lexer.nextToken();
        if (shouldCollect(token)) {
            collectedTokens.add(token);
        }
        return token;
    }

    private boolean shouldCollect(Token token) {
        return token.getChannel() != Token.DEFAULT_CHANNEL;
    }

    public List<Token> getCollectedTokens() {
        return collectedTokens;
    }

    @Override
    public int getLine() {
        return lexer.getLine();
    }

    @Override
    public int getCharPositionInLine() {
        return lexer.getCharPositionInLine();
    }

    @Override
    public CharStream getInputStream() {
        return lexer.getInputStream();
    }

    @Override
    public String getSourceName() {
        return "Collect hidden channel " + lexer.getSourceName();
    }

    @Override
    public TokenFactory<?> getTokenFactory() {
        return lexer.getTokenFactory();
    }

    @Override
    public void setTokenFactory(TokenFactory<?> tokenFactory) {
        lexer.setTokenFactory(tokenFactory);
    }
}
