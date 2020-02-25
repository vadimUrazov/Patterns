package ymp4;


import org.junit.jupiter.api.Test;
import squarePolynom.SqPolynom;


import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    @Test
    public void testSquareEquation() throws Exception{

        SquareEquations sq = new SquareEquations(1, 2, 1);
        assertEquals(-1.0,sq.rootsEquation1(), 1E-9);
       SquareEquations s = new SquareEquations(1, 3, -4);
       assertEquals(1.0, s.rootsEquation1(), 1E-9);
      assertThrows(IllegalArgumentException.class, () -> new SquareEquations(1, 1, 1).rootsEquation1());

    }

}
