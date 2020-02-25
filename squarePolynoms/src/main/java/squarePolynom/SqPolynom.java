package squarePolynom;

public class SqPolynom {
    private double A;
    private double B;
    private double C;

    public SqPolynom(double a, double b, double c) {
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

    public double[] rootsEquation() {

        double[] k = null;
        double D = B * B - 4 * A * C;
        if (A == 0 && B == 0 && C == 0) {
            double[] array;

            array = null;
            k = array;
        } else if (A == 0 && B == 0) {
            double[] array;

            array = null;
            k = array;
        } else if (A == 0) {


            if (B != 0) {
                double[] array = new double[1];
                for (int i = 0; i < 1; i++) {
                    array[i] = (-C / B);
                }
                k = array;


            }


        } else if (D == 0) {
            double[] array = new double[1];
            for (int i = 0; i < 1; i++) {
                array[i] = (-B / (2 * A));
            }
            k = array;


        } else if (D > 0) {
            double[] array = new double[2];
            double r1 = (-B + Math.sqrt(D)) / (2 * A);
            double r2 = (-B - Math.sqrt(D)) / (2 * A);
            array[0] = r1;
            array[1] = r2;
            k = array;
        } else if (D < 0) {
            double[] array;

            array = null;
            k = array;
        }
       System.out.println("версия 4");
        return k;
    }

}
