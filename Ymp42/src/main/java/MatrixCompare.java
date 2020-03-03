import java.util.Comparator;

public class MatrixCompare implements Comparator<Matrix>{

    @Override
    public int compare(Matrix o1, Matrix o2) {
        Comparator<Matrix> comparator = Comparator.comparingDouble(Matrix::getDeterminant);

        return comparator.compare(o1, o2);
    }
}
