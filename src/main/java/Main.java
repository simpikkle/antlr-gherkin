import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Objects;

public class Main {

    private static Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        InputStream featureStream = ClassLoader.getSystemResourceAsStream("sample.feature");
        Objects.requireNonNull(featureStream);

        ANTLRInputStream inputStream = new ANTLRInputStream(featureStream);
        GherkinLexer lexer = new GherkinLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        GherkinParser parser = new GherkinParser(commonTokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        FeatureListener listener = new FeatureListener();
        ParseTree parseTree = parser.feature();
        walker.walk(listener, parseTree);
        System.out.println(listener.getFeatures().get(0));
    }
}
