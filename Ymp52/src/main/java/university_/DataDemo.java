package university_;

import java.util.ArrayList;
import java.util.List;

public class DataDemo
{
    public static List<Integer> getAll(Data data) {
        DataIterator iter = new DataIterator(data);
        ArrayList<Integer> res = new ArrayList<>();
        while(iter.hasNext()){
            res.add(iter.next());
        }
        return res;
    }
}
