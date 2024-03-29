package app;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * App
 */
public class App {
    public static void main(String[] args) throws Exception {
        String inputFile = null;
        if (args.length > 0)
            inputFile = args[0];
        InputStream is = System.in;
        if (inputFile != null)
            is = new FileInputStream(inputFile);
        CharStream inputStream = CharStreams.fromStream(is);
        LibExprLexer lexer = new LibExprLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        LibExprParser parser = new LibExprParser(tokenStream);
        ParseTree tree = parser.prog();
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}