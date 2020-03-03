import java.util.Arrays;
import java.util.Comparator;

public class MatrixService {


    public static Matrix[] arrangeMatrices(Matrix[] arrayMatrix) {

        Arrays.sort(arrayMatrix, new MatrixCompare());

        return arrayMatrix;
    }

}



