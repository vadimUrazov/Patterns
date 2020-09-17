package org.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
interface SubExpression {
    Number value();

    void add(SubExpression expr);

    void sub(SubExpression expr);

    SubExpression getSubExpression(int index);


}

class IntegerValue implements SubExpression {
    private Integer value;

    public IntegerValue(Integer value) {
        this.value = value;
    }

    @Override
    public Number value() {
        return value;
    }

    @Override
    public void add(SubExpression expr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sub(SubExpression expr) {
        throw new UnsupportedOperationException();
    }

    @Override
    public SubExpression getSubExpression(int index) {
        throw new UnsupportedOperationException();
    }
}

class Expression implements SubExpression {
    private List<SubExpression> exprs;

    public Expression(SubExpression... exprs) {
        this.exprs = new ArrayList<>();
        for (SubExpression expr : exprs) {
            this.exprs.add(expr);
        }
    }

    @Override
    public Number value() {
        Number result = new Float(0);

        for (SubExpression expr : exprs) {
            result = result.floatValue() + expr.value().floatValue();
        }

        return result;
    }

    @Override
    public void add(SubExpression expr) {
        exprs.add(expr);
    }

    @Override
    public void sub(SubExpression expr) {
        if (expr instanceof IntegerValue) {
            exprs.add(new IntegerValue(-1 * expr.value().intValue()));
        }

    }

    @Override
    public SubExpression getSubExpression(int index) {
        return exprs.get(index);
    }
}

public class Client {
    public static void main(String[] args) {
        // Вычислим выражение - 20 - (5-2) - (11+6)
        // Приведем к следующему виду 20 - a - b
        SubExpression expr = new Expression();

        SubExpression a = new Expression(new IntegerValue(5), new IntegerValue(-2));
        SubExpression b = new Expression(new IntegerValue(11), new IntegerValue(6));

        expr.add(new IntegerValue(20));
        expr.sub(a);
        expr.sub(b);

        System.out.println(expr.value());


    }
}
