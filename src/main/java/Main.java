import domain.Feature;
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

    //private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        GherkinLexer lexer = new GherkinLexer(getFile("sample.feature"));
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        GherkinParser parser = new GherkinParser(commonTokenStream);

        ParseTreeWalker walker = new ParseTreeWalker();
        FeatureListener listener = new FeatureListener();
        ParseTree parseTree = parser.feature();
        walker.walk(listener, parseTree);
        List<Feature> features = listener.getFeatures();
        System.out.println(features);
    }

    private static CharStream getFile(String fileName) throws IOException {
        InputStream featureStream = ClassLoader.getSystemResourceAsStream(fileName);
        Objects.requireNonNull(featureStream);
        return new ANTLRInputStream(featureStream);
    }
}
