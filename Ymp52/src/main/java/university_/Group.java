package university_;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Group {
    private int id;
    private List<Integer> data;

    public Group() {
        id = 1;
        data = new ArrayList<>();
    }

    public Group(int id, int... data)  {
        setId(id);
        this.data = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            this.data.add(data[i]);
        }
    }

    public Group(Group obj) {
        this.id = obj.id;
        this.data = new ArrayList<>();
        for (int i = 0; i < obj.data.size(); i++) {
            this.data.add(obj.data.get(i));
        }
    }

    public Group(Group obj, int id) {
        this.id = id;
        this.data = new ArrayList<>();
        for (int i = 0; i < obj.data.size(); i++) {
            this.data.add(obj.data.get(i));
        }
    }

    public int getId() {
        return id;
    }

    public List<Integer> getData() {
        return data;
    }

    public int length() {
        return data.size();
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Error");
        }
        this.id = id;
    }

    public void setData(List<Integer> data) {
        this.data.clear();
        for (int i = 0; i < data.size(); i++) {
            this.data.add(data.get(i));
        }
    }

    public void setData(int... data) {
        this.data.clear();
        for (int i = 0; i < data.length; i++) {
            this.data.add(data[i]);
        }
    }

    public void addData(List<Integer> data) {
        this.data.addAll(data);
    }

    public void addData(int... data) {
        for (int i = 0; i < data.length; i++) {
            this.data.add(data[i]);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Group)) return false;
        Group group = (Group) o;
        return getId() == group.getId() &&
                Objects.equals(getData(), group.getData());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getData());
    }
}

