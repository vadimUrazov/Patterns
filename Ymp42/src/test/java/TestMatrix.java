
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class TestMatrix {
    @Test
    public void testMatrixDeterminant() {
        Matrix m = new Matrix(1);
        m.setElem(0, 0, 1);

        Matrix matrix = new Matrix(2);
        matrix.setElem(0, 0, 0);
        matrix.setElem(0, 1, 2);
        matrix.setElem(1, 0, 3);
        matrix.setElem(1, 1, 4);

        Matrix matrix1 = new Matrix(3);
        matrix1.setElem(0, 0, 0);
        matrix1.setElem(0, 1, 0);
        matrix1.setElem(0, 2, 1);
        matrix1.setElem(1, 0, 1);
        matrix1.setElem(1, 1, 1);
        matrix1.setElem(1, 2, 1);
        matrix1.setElem(2, 0, 2);
        matrix1.setElem(2, 1, 2);
        matrix1.setElem(2, 2, 1);


        assertEquals(1.0, m.determinant());
        assertEquals(-6.0, matrix.determinant());
        assertEquals(0.0, matrix1.determinant());


    }

    @Test
    public void testGeterandSetter() {
        Matrix matrix = new Matrix(2);
        matrix.setElem(0, 0, 1);
        matrix.setElem(0, 1, 2);
        matrix.setElem(1, 0, 3);
        matrix.setElem(1, 1, 4);

        UpTriangleMatrix upTriangleMatrix = new UpTriangleMatrix(2);
        upTriangleMatrix.setElem(0, 0, 0);
        upTriangleMatrix.setElem(1, 0, 0);
        upTriangleMatrix.setElem(1, 1, 0);

        assertEquals(0, upTriangleMatrix.getElem(0, 1));
    }

    @Test
    public void testFailSetterElem() {
        DiagMatrix diagMatrix1 = new DiagMatrix(2);
        diagMatrix1.setElem(0, 0, 1);
        diagMatrix1.setElem(1, 1, 2);
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            diagMatrix1.setElem(0, 1, 2);
        });
        assertNotNull(thrown.getMessage());

    }

    @Test
    public void testFailGetter() {
        DiagMatrix diagMatrix1 = new DiagMatrix(2);
        diagMatrix1.setElem(0, 0, 1);
        diagMatrix1.setElem(1, 1, 2);

        UpTriangleMatrix upTriangleMatrix = new UpTriangleMatrix(2);
        upTriangleMatrix.setElem(0, 0, 0);
        upTriangleMatrix.setElem(1, 0, 0);
        upTriangleMatrix.setElem(1, 1, 0);

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            diagMatrix1.getElem(-2, 1000);
        });

        Throwable thrown1 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            upTriangleMatrix.setElem(-2, 1000, 1000);
        });

        assertNotNull(thrown.getMessage());
        assertNotNull(thrown1.getMessage());
    }

    @Test
    public void testDiagAndTrianglematrix() {
        UpTriangleMatrix upTriangleMatrix = new UpTriangleMatrix(2);
        upTriangleMatrix.setElem(0, 0, 0);
        upTriangleMatrix.setElem(1, 0, 0);
        upTriangleMatrix.setElem(1, 1, 0);

        double[] arr = {1.5, 2};
        DiagMatrix diagMatrix = new DiagMatrix(arr);

        DiagMatrix diagMatrix1 = new DiagMatrix(3);
        diagMatrix1.setElem(0, 0, 1);
        diagMatrix1.setElem(1, 1, 2);
        diagMatrix1.setElem(2, 2, 1);


        assertEquals(3, diagMatrix.determinant());
        assertEquals(0, upTriangleMatrix.determinant());
        assertEquals(2, diagMatrix1.determinant());

    }

    @Test
    public void testMatrixServiceCompare() {

        Matrix matrix = new Matrix(2);
        matrix.setElem(0, 0, 1);
        matrix.setElem(0, 1, 0);
        matrix.setElem(1, 0, 0);
        matrix.setElem(1, 1, 1);


        Matrix matrix1 = new Matrix(2);
        matrix1.setElem(0, 0, 1);
        matrix1.setElem(0, 1, 1);
        matrix1.setElem(1, 0, 1);
        matrix1.setElem(1, 1, 2);

        assertEquals(0, MatrixService.compareMatrix(matrix1, matrix));
    }

    @Test
    public void testMatrixServiceSort() {
        Matrix matrix = new Matrix(2);
        matrix.setElem(0, 0, 1);
        matrix.setElem(0, 1, 2);
        matrix.setElem(1, 0, 3);
        matrix.setElem(1, 1, 4);

        Matrix matrix1 = new Matrix(2);
        matrix1.setElem(0, 0, 1);
        matrix1.setElem(0, 1, 1);
        matrix1.setElem(1, 0, 1);
        matrix1.setElem(1, 1, 2);

        Matrix matrix2 = new Matrix(2);
        matrix2.setElem(0, 0, 5);
        matrix2.setElem(0, 1, 2);
        matrix2.setElem(1, 0, 3);
        matrix2.setElem(1, 1, 4);

        Matrix matrix3 = new Matrix(2);
        matrix3.setElem(0, 0, 10);
        matrix3.setElem(0, 1, 2);
        matrix3.setElem(1, 0, 1);
        matrix3.setElem(1, 1, 2);

        Matrix matrix4 = new Matrix(3);
        matrix4.setElem(0, 0, 1);
        matrix4.setElem(0, 1, 0);
        matrix4.setElem(0, 2, 0);
        matrix4.setElem(1, 0, 0);
        matrix4.setElem(1, 1, 0);
        matrix4.setElem(1, 2, 0);
        matrix4.setElem(2, 0, 0);
        matrix4.setElem(2, 1, 0);
        matrix4.setElem(2, 2, 1);

        Matrix[] arrayExpected = {matrix, matrix4, matrix1, matrix2, matrix3};
        Matrix[] array = {matrix1, matrix3, matrix2, matrix, matrix4};

        assertArrayEquals(arrayExpected, MatrixService.arrangeMatrices(array));
    }
}

