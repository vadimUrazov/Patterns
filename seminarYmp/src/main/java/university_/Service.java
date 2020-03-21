package university_;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Service {
    public static List<Data> getNameList(List<Data> list, String name) {
        List<Data> res = new LinkedList<>();
        if (name != null && !"".equals(name)) {
            for (Data d : list) {
                if (d.getName().equals(name)) {
                    res.add(d);
                }
            }
        } else {
            throw new NullPointerException("illegal argument");
        }

        return res;
    }

    public static List<Data> getLevelList(List<Data> list, double level) {
        List<Data> res = new LinkedList<>();
        for (Data d : list) {
            if (Double.compare(Math.abs(d.getValue()), level) <= 0) {
                res.add(d);
            }
        }
        return res;
    }

    public static Set<Double> getIsNameDataContainsSet(List<Data> list, Set<String> names) {
        Set<Double> set = new HashSet<>();
        for (Data d : list) {
            if (names.contains(d.getName())) {
                set.add(d.getValue());
            }
        }
        return set;
    }

    public static String[] getPositiveValue(List<Data> list) {
        Set<String> set = new HashSet<>();
        for (Data d : list) {
            if (Double.compare(d.getValue(), 0) > 0) {
                set.add(d.getName());
            }
        }
        return set.toArray(new String[0]);
    }

    public static Set<Data> getUnionSet(List<Set<Data>> list) {
        Set<Data> arr[] = list.toArray(new Set[0]);
        Set<Data> union1 = new HashSet<>();
        Set<Data> union2 = new HashSet<>();
        for (int i = 0; i < arr.length / 2; i++) {
            for (Data d : arr[i]) {
                union1.add(d);
            }
        }
        for (int j = arr.length / 2; j < arr.length; j++) {
            for (Data d : arr[j]) {
                union2.add(d);
            }
        }




        return Stream.concat(union1.stream(), union2.stream()).collect(Collectors.toSet());
    }

    public static Set<Data> getIntersectsSet(List<Set<Data>> list) {
        Set<Data> arr[] = list.toArray(new Set[0]);
        Set<Data> union1 = new HashSet<>();
        Set<Data> union2 = new HashSet<>();
        for (int i = 0; i < arr.length / 2; i++) {
            for (Data d : arr[i]) {
                union1.add(d);
            }
        }
        for (int j = arr.length / 2; j < arr.length; j++) {
            for (Data d : arr[j]) {
                union2.add(d);
            }
        }


        return  union1.stream().filter(union2::contains).collect(Collectors.toSet());
    }
public static List<Set<Data>> getMaxSizeSet(List<Set<Data>> list){
    List<Set<Data>> l=new ArrayList<>();
    Collections.sort(list, new Comparator<Set<Data>>() {
        @Override
        public int compare(Set<Data> o1, Set<Data> o2) {
            return Integer.compare(o1.size(),o2.size());
        }
    });

    for (Set<Data> s: list) {
        if(s.size()==list.get(list.size()).size()){
            l.add(s);
            list.remove(s);
        }
    }
    l.add(list.get(list.size()));
   list.remove(list.size());
        return l;
    }
}

