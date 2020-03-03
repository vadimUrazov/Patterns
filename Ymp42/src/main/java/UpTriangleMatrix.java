public class UpTriangleMatrix extends Matrix {
    public UpTriangleMatrix(int n) {
        super(n);
    }

    @Override
    public void setElem(int i, int j, double value) {
        if (value != 0 && j > i) {
            throw new ArrayIndexOutOfBoundsException("Error_Array");
        }
        super.setElem(i, j, value);
    }
}




