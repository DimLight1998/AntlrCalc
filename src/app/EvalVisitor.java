package app;

import java.util.HashMap;
import java.util.Map;

/**
 * EvalVisitor
 */
public class EvalVisitor extends LibExprBaseVisitor<Integer> {
    Map<String, Integer> memory = new HashMap<String, Integer>();

    @Override
    public Integer visitPrintExpr(LibExprParser.PrintExprContext ctx) {
        System.out.printf("[%s]:  %s\n", ctx.expr().getText(), visit(ctx.expr()));
        return 0;
    }

    @Override
    public Integer visitAssign(LibExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Integer value = visit(ctx.expr());
        memory.put(id, value);
        return 0;
    }

    @Override
    public Integer visitMulDiv(LibExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LibExprParser.MUL)
            return left * right;
        return left / right;
    }

    @Override
    public Integer visitAddSub(LibExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));
        if (ctx.op.getType() == LibExprParser.ADD)
            return left + right;
        return left - right;
    }

    @Override
    public Integer visitInt(LibExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    @Override
    public Integer visitId(LibExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id))
            return memory.get(id);
        return 0;
    }

    @Override
    public Integer visitParens(LibExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Integer visitProg(LibExprParser.ProgContext ctx) {
        Integer ret = visitChildren(ctx);
        System.out.printf("Memory after running: \n");
        for (String varName : memory.keySet()) {
            System.out.printf("%s: %d\n", varName, memory.get(varName));
        }
        return ret;
    }
}