package university_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Data implements Iterable {
    private String nameOfSet;
    private Group[] set;

    public Data() {
        nameOfSet = "Default Data";
        set = new Group[1000];
    }

    public Data(String nameOfSet, Group... data) {
        this.nameOfSet = nameOfSet;
        List<Group> l= new ArrayList<>();
        set = new Group[1000];
        for (int i = 0; i < data.length; i++) {
            l.add(new Group(data[i]));
        }
        set=l.toArray(new Group[0]);
    }

    public Data(Data obj) {
        this.nameOfSet = obj.nameOfSet;
        set = new Group[1000];
        List<Group> l= new ArrayList<>();
        for (int i = 0; i < obj.set.length; i++) {
            l.add(new Group(obj.set[i]));
        }
        set=l.toArray(new Group[0]);
    }

    public Data(Data obj, String nameOfSet) {
        this.nameOfSet = nameOfSet;
        List<Group> l= new ArrayList<>();
        set = new Group[1000];
        for (int i = 0; i < obj.set.length; i++) {
            l.add(obj.set[i]);
        }
        set=l.toArray(new Group[0]);
    }

    public void setNameOfSet(String nameOfSet) {
        this.nameOfSet = nameOfSet;
    }

    public String getNameOfSet() {
        return nameOfSet;
    }

    public void setGroupArray(Group[] set) {
        List<Group> l= new ArrayList<>();
        for (int i = 0; i < set.length; i++) {
            l.add(new Group(set[i]));
        }
        this.set=l.toArray(new Group[0]);
    }

    public void setSet(Group... set) {
        List<Group> l= new ArrayList<>();
        for (Group i: set) {
            l.add(i);
        }
        setGroupArray(l.toArray(new Group[0]));
    }

    public Group[] getSet() {
        return set;
    }

    @Override
    public String toString() {
        return "Data{" +
                "nameOfSet='" + nameOfSet + '\'' +
                ", set=" + Arrays.toString(set) +
                '}';
    }

    public int length() {
        return this.set.length;
    }

    @Override
    public DataIterator iterator() {
        DataIterator iter = new DataIterator(this);
        return iter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Data)) return false;
        Data data = (Data) o;
        return Objects.equals(getNameOfSet(), data.getNameOfSet()) &&
                Arrays.equals(getSet(), data.getSet());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNameOfSet(), getSet());
    }
}
