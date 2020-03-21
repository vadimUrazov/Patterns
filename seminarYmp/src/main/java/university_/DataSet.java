package university_;

import java.util.*;
import java.util.function.Consumer;

public class DataSet {
    private List<Data> list;

    public DataSet() {
        this.list = new ArrayList<>();
    }
    public void add(Data data){
        list.add(data);
    }

    public void forEach(Consumer action) {
        Objects.requireNonNull(action);
       list.forEach(action);
    }
    public void sort(){
        Collections.sort(list, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }
}
