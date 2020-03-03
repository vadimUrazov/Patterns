
import java.util.Arrays;

import java.util.Objects;


public class Matrix implements IMatrix {
    private int N;
    private double[] matrix;
    private double determ;
    private boolean isActive = false;

    public Matrix(int n) {
        this.N = n;
        matrix = new double[n * n];
        Arrays.fill(matrix, 0);
    }


    public int getN() {
        return N;
    }

    public double getElem(int i, int j) {
        if (!(i >= 0 && j >= 0 && i < N && j < N)) {
            throw new IllegalArgumentException("Error");
        }
        return matrix[i * N + j];
    }

    public void setElem(int i, int j, double value) {
        if (!(i >= 0 && j >= 0 && i < N && j < N)) {
            throw new IllegalArgumentException("Error");
        }
        this.matrix[i * N + j] = value;
        isActive = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix1 = (Matrix) o;
        return N == matrix1.N &&
                Arrays.equals(matrix, matrix1.matrix);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(N);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }

    public double getDeterminant() {
        double det;
        if (isActive) {
            return determ;
        }

        det = 1;
        int h = 0;
        double buf2;
        double[] buf = new double[N * N];
        for (int i = 0; i < N * N; i++) {
            buf[i] = matrix[i];
        }

        for (int i = 1; i < N; i++) {
            if (buf[h] == 0.0) {
                for (int k = 1; k < (N - i + 1); k++) {
                    if (buf[k * N + h] != 0.0) {
                        for (int t = 0; t < (N - i + 1); t++) {
                            buf[h + t] = buf[h + t] + buf[k * N + h + t];
                        }
                        break;
                    }
                }
            }
            if (buf[h] != 0.0) {
                for (int q = i; q < N; q++) {
                    buf2 = buf[h + (q - i + 1) * N];
                    for (int j = 0; j < N; j++) {
                        buf[j + N * q] = buf[j + N * q] - (buf[j + (i - 1) * N] / buf[h] * buf2);
                    }
                }
            }
            det *= buf[h];
            h += (N + 1);
        }
        det *= buf[h];

        this.determ = det;
        isActive = true;
        return det;
    }

}





