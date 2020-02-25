package ymp4;

public class SineFunction implements OneArgumentFunction {
    private double A;
    private double B;
    private double beginA,endB;

    public SineFunction(double a, double b, double beginA, double endB) {
        A = a;
        B = b;
        this.beginA = beginA;
        this.endB = endB;
    }

    @Override
    public double solve(double x) {
        if( x >endB || x<beginA){
            throw new ArithmeticException();
        }
        return A*Math.sin(B*x);
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
