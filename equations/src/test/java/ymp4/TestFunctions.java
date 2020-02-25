package ymp4;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestFunctions {
    @Test
    public void testLineaEquation() throws Exception {
        LinearyFunction f = new LinearyFunction(1, 1,-100,100);
        assertEquals(1, f.solve(0), 1E-9);
    }

    @Test
    public void testSineEquation() throws Exception {
        SineFunction f = new SineFunction(1, 1,-100,100);
        assertEquals(0, f.solve(0), 1E-9);
    }

    @Test
    public void testExpEquation() throws Exception {
        ExpFunction f = new ExpFunction(1, 1,-100,100);
        assertEquals(2, f.solve(0), 1E-9);
    }
    @Test
    public void testEquation() throws Exception{
      FractionFunction f=new FractionFunction(1,1,1,1,-100,100);
        assertEquals(1,f.solve(0),1E-9);
    }
    @Test
    public void testFunctional() throws Exception{

        LinearyFunction function = new LinearyFunction(2,0,-100,100);
        FunctionSquare<OneArgumentFunction> solver = new FunctionSquare<>();

        FunctionIntegral<OneArgumentFunction> integral = new FunctionIntegral(1,1);
assertEquals(0,integral.apply(function),1E-9);

    }
    @Test
    public void testIntegral(){
        FunctionIntegral<OneArgumentFunction> integral = new FunctionIntegral(0,2*Math.PI);
        SineFunction sineFunction = new SineFunction(1, 1,-100,100);
        LinearyFunction function = new LinearyFunction(2,0,-100,100);
      assertEquals(0.0,integral.apply(sineFunction),1E-9);
    }
    @Test
    public void testExpIntegral(){
        FunctionIntegral<OneArgumentFunction> integral = new FunctionIntegral(0,1);
        ExpFunction fun=new ExpFunction(1,0,-100,100);
       assertEquals(1.718274668972308,integral.apply(fun),1E-9);
    }
    @Test
public void testFraction(){
        FractionFunction f=new FractionFunction(1,0,1,0,-100,100);
        FunctionIntegral<OneArgumentFunction> integral = new FunctionIntegral(0,1);
        assertEquals(1.0,integral.apply(f),1E-9);
    }


}
