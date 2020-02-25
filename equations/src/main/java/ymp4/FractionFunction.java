package ymp4;

public class FractionFunction implements OneArgumentFunction {
    private double A;
    private double B;
    private double C;
    private double D;
private double beginA,endB;

    public FractionFunction(double a, double b, double c, double d, double beginA, double endB) {
        A = a;
        B = b;
        C = c;
        D = d;
        this.beginA = beginA;
        this.endB = endB;
    }

    @Override
    public double solve(double x) {
if(x>endB ||  x <beginA || ( (C * x + D)==0)){
    throw new ArithmeticException();
}
        return (A * x + B) / (C * x + D);
    }

    @Override
    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

    @Override
    public double getB() {
        return B;
    }

    public void setB(double b) {
        B = b;
    }

    public double getC() {
        return C;
    }

    public void setC(double c) {
        C = c;
    }

    public double getD() {
        return D;
    }

    public void setD(double d) {
        D = d;
    }
}
