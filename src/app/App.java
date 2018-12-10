package app;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

/**
 * App
 */
public class App {
    public static void main(String[] args) throws Exception {
        ANTLRInputStream inputStream = new ANTLRInputStream(System.in);
        LibExprLexer lexer = new LibExprLexer(inputStream);
        CommonTokenStream tokenStream = new CommonTokenStream(lexer);
        LibExprParser parser = new LibExprParser(tokenStream);
        ParseTree tree = parser.prog();
        EvalVisitor eval = new EvalVisitor();
        eval.visit(tree);
    }
}