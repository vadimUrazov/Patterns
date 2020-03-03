package university_;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Data implements Iterable {
    private String nameOfSet;
    private List<Group> set;

    public Data() {
        nameOfSet = "Default Data";
        set = new ArrayList<>();
    }

    public Data(String nameOfSet, Group... data) {
        this.nameOfSet = nameOfSet;
        set = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            set.add(new Group(data[i]));
        }
    }

    public Data(Data obj) {
        this.nameOfSet = obj.nameOfSet;
        set = new ArrayList<>();
        for (int i = 0; i < obj.set.size(); i++) {
            set.add(new Group(obj.set.get(i)));
        }
    }

    public Data(Data obj, String nameOfSet) {
        this.nameOfSet = nameOfSet;
        set = new ArrayList<>();
        for (int i = 0; i < obj.set.size(); i++) {
            set.add(obj.set.get(i));
        }
    }

    public void setNameOfSet(String nameOfSet) {
        this.nameOfSet = nameOfSet;
    }

    public String getNameOfSet() {
        return nameOfSet;
    }

    public void setSet(ArrayList<Group> set) {
        this.set.clear();
        for (int i = 0; i < set.size(); i++) {
            this.set.add(new Group(set.get(i)));
        }
    }

    public void setSet(Group... set) {
        this.set.clear();
        for (int i = 0; i < set.length; i++) {
            this.set.add(new Group(set[i]));
        }
    }

    public List<Group> getSet() {
        return set;
    }

    public void addSet(List<Group> set) {
        for (int i = 0; i < set.size(); i++) {
            this.set.add(new Group(set.get(i)));
        }
    }

    public void addSet(Group... set) {
        for (int i = 0; i < set.length; i++) {
            this.set.add(new Group(set[i]));
        }
    }

    public int length() {
        return this.set.size();
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
                Objects.equals(getSet(), data.getSet());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getNameOfSet(), getSet());
    }
}
