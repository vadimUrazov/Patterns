package university_;

import java.util.List;
import java.util.Objects;

public class Flat {
    private int number;
    private String square;
    private List<Person> list;

    public Flat(int number, String square, List<Person> list) {
        this.number = number;
        this.square = square;
        this.list = list;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public List<Person> getList() {
        return list;
    }

    public void setList(List<Person> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return number == flat.number &&
                square.equals(flat.square) &&
                list.equals(flat.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, square, list);
    }
}
