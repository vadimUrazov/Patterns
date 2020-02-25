package ymp4;

public class LinearyFunction implements OneArgumentFunction {
    private double A;
    private double B;
    private double beginA,endB;

    public LinearyFunction(double a, double b, double beginA, double endB) {
        A = a;
        B = b;
        this.beginA = beginA;
        this.endB = endB;
    }

    @Override
    public double solve(double x) {
        if( x  >endB ||  x <beginA){
            throw new ArithmeticException();
        }
        return A * x + B;
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
}
