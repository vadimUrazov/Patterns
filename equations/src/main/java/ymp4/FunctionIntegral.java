package ymp4;

public class FunctionIntegral<F extends OneArgumentFunction> implements Function<F> {
    private double A;
    private double B;

    public FunctionIntegral(double a, double b) {
        A = a;
        B = b;
    }

    @Override
    public double apply(F f) {
        int n = 100;
        double res = 0;
        double k;
        if (A == B) {
            return res;
        }
        if (A > B) {
            k = B;
            B = A;
            A = k;
        }

        double result = 0;
        double h = (B - A) / n;

        for (int i = 0; i < n; i++) {
            result += f.solve(A + h * (i + 0.5));
        }
        result *= h;

        return result;
    }


}
