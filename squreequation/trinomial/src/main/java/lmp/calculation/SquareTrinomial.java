package lmp.calculation;

/**
 * Hello world!
 *
 */
public class SquareTrinomial {
    private double a;
    private double b;
    private double c;

    public SquareTrinomial(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SquareTrinomial)) return false;
        SquareTrinomial that = (SquareTrinomial) o;
        return Double.compare(that.a, a) == 0 &&
                Double.compare(that.b, b) == 0 &&
                Double.compare(that.c, c) == 0;
    }


    public double[] quadraticSolution()
    {
        double[] roots = new double[2];
        double D;
        D = b*b - 4*a*c;
        if (D >=0) {

            roots[0]=(-b-Math.sqrt(D))/2*a;
            roots[1]=(-b+Math.sqrt(D))/2*a;
        }
        if (D < 0)
        {
            throw new  IllegalArgumentException("incorrect data");
        }
        return roots;
    }
}
