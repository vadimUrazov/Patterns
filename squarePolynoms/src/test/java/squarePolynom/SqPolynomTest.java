package squarePolynom;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

/**
 * Unit test for simple App.
 */
public class SqPolynomTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testSquareEquationOneRoot() {
        SqPolynom s = new SqPolynom(1, 2, 1);
        double[] arr = {-1.0};
        assertArrayEquals(arr, s.rootsEquation(), 1E-9);
    }

    @Test
    public void testSquareEquationTwoRoot() {
        SqPolynom s = new SqPolynom(1, 2, -3);
        double[] arr = {1.0, -3.0};
        assertArrayEquals(arr, s.rootsEquation(), 1E-9);
    }

    @Test
    public void testSquareEquationZeroRoot() {
        SqPolynom s = new SqPolynom(1, 1, 1);
        assertNull(s.rootsEquation());
    }

    @Test
    public void testSquareEquation() {
        SqPolynom s = new SqPolynom(0, 2, -4);
        double[] arr = {2.0};
        assertArrayEquals(arr, s.rootsEquation(), 1E-9);
    }
}
