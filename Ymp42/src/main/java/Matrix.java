
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


    public double determinant() {
        double det;
        if (isActive) {
            return determ;
        }
        int n = getN();
        double a[][] = new double[n][n];
        for (int q = 0; q < n; q++) {
            for (int s = 0; s < n; s++) {
                a[q][s] = getElem(q, s);
            }
        }
        det = deter(a, n);
        determ = det;
        isActive = true;
        return det;
    }

    void getMatr(double[][] mas, double[][] p, int i, int j, int m) {
        int ki, kj, di, dj;
        di = 0;
        for (ki = 0; ki < m - 1; ki++) {
            if (ki == i) di = 1;
            dj = 0;
            for (kj = 0; kj < m - 1; kj++) {
                if (kj == j) dj = 1;
                p[ki][kj] = mas[ki + di][kj + dj];
            }
        }
    }

    double deter(double[][] mas, int m) {
        int i, k, n;
        double[][] p;
        double d;
        p = new double[m][m];

        d = 0;
        k = 1;
        n = m - 1;
        if (m == 1) {
            d = mas[0][0];
            return d;
        }
        if (m == 2) {
            d = mas[0][0] * mas[1][1] - mas[1][0] * mas[0][1];
            return d;
        }
        if (m > 2) {
            for (i = 0; i < m; i++) {
                getMatr(mas, p, i, 0, m);

                d = d + k * mas[i][0] * deter(p, n);
                k = -k;
            }
        }
        return d;
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


}










