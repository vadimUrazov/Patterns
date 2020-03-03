package lmp.calculation;


/**
 * Unit test for simple App.
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class SquareTrinomialTest {
    private SquareTrinomial squareTrinomial;
    private double[] roots;

    @BeforeEach
    public void setUp() {
        squareTrinomial = new SquareTrinomial(1, 2, -3);
        roots = new double[2];
        roots[0] = -3;
        roots[1] = 1;
    }

    @Test
    public void quadraticSolution() {
        Arrays.sort(roots);
        Arrays.sort(squareTrinomial.quadraticSolution());
        assertArrayEquals(roots, squareTrinomial.quadraticSolution());
    }

}