package ymp4;



public class FunctionSquare<F extends OneArgumentFunction> implements Function<F> {

    @Override
    public double apply(F f) {
       double middle=(f.getA()+f.getB())/2;
       return f.solve(f.getA())+f.solve(f.getB())+f.solve(middle);
    }
}
