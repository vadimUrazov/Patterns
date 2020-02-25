package ymp4;


import squarePolynom.SqPolynom;

/**
 * Hello world!
 *
 */
public class SquareEquations {
    private double A;
    private double B;
    private double C;

    public SquareEquations(double a, double b, double c) {
        A = a;
        B = b;
        C = c;
    }

    public double getA() {
        return A;
    }

    public void setA(double a) {
        A = a;
    }

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




    public double rootsEquation1() throws IllegalArgumentException {
        SqPolynom s = new SqPolynom(A, B, C);
        double res=0; if (s.rootsEquation() == null) {
            throw new IllegalArgumentException();
        }else
       if (s.rootsEquation().length == 2) {
            double r1 = s.rootsEquation()[0];
           double r2 = s.rootsEquation()[1];
           res= Double.max(r1, r2);
        } else if (s.rootsEquation().length == 1) {
           res=s.rootsEquation()[0];
       }

return res;
    }
}
