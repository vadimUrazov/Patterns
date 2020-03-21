package university_;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Group {
    private int id;
    private Integer[] data;

    public Group() {
        id = 1;
        data = new Integer[1000];
    }

    public Group(int id, int... data)  {
        setId(id);
        List<Integer> l= new ArrayList<>();

        for (int i = 0; i < data.length; i++) {
            l.add(data[i]);

        }
        this.data=l.toArray(new Integer[0]);
    }

    public Group(Group obj) {
        List<Integer> l= new ArrayList<>();
        this.id = obj.id;
        for (int i = 0; i < obj.data.length; i++) {

           l.add(obj.data[i]);
        }
        this.data=l.toArray(new Integer[0]);
    }

    public Group(Group obj, int id) {
        List<Integer> l= new ArrayList<>();
        this.id = id;
        for (int i = 0; i < obj.data.length; i++) {
           l.add(obj.data[i]);
        }
        this.data=l.toArray(new Integer[0]);
    }

    public int getId() {
        return id;
    }

    public Integer[] getData() {
        return data;
    }

    public int length() {
        return data.length;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error");
        }
        this.id = id;
    }

    public void setData(Integer[] data) {
        List<Integer> l= new ArrayList<>();


        for (int i = 0; i < data.length; i++) {
            l.add(data[i]);

        }
        this.data=l.toArray(new Integer[0]);
    }

    public void setData(int... data) {
        List<Integer> l= new ArrayList<>();
        for (Integer i: data) {
            l.add(i);
        }
        setData(l.toArray(new Integer[0]));
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", data=" + Arrays.toString(data) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getId() == group.getId() &&
                Arrays.equals(getData(), group.getData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getData());
    }
}

