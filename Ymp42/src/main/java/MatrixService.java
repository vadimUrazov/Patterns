import java.util.Arrays;
import java.util.Comparator;

public class MatrixService {
    public static int compareMatrix(Matrix matrix1,Matrix matrix2) {
        Comparator<Matrix> comparator = Comparator.comparingDouble(Matrix::determinant);

        return comparator.compare(matrix1,matrix2);
    }
    public static Matrix[] arrangeMatrices(Matrix[] arrayMatrix){
        Comparator<Matrix> comparator= Comparator.comparingDouble(Matrix::determinant);

        Arrays.sort(arrayMatrix,comparator);
        return arrayMatrix;
    }

}



