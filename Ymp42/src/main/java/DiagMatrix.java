

public class DiagMatrix extends Matrix {

    public DiagMatrix(int n) {
        super(n);

    }

    public DiagMatrix(double[] array) {
        super(array.length);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (i == j) {
                    setElem(i, i, array[i]);
                }


            }
        }
    }

    @Override
    public void setElem(int i, int j, double value) throws IllegalArgumentException {
        if (value == 0) {
            super.setElem(i, j, value);
        } else if (i == j) {
            super.setElem(i, j, value);
        } else throw new IllegalArgumentException("Error");
    }

}

