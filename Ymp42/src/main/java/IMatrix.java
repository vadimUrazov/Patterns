public interface IMatrix {
    double getElem(int i, int j);

    void setElem(int i, int j, double value);

    double determinant();
}
