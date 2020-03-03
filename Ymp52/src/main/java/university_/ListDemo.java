package university_;

import java.util.*;

public class ListDemo {


    public static List<Human> getListSameSurnameWithHenry(List<Human> listOfHumans, Human goodHenry) {
        List<Human> result = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < listOfHumans.size(); i++) {
            if (listOfHumans.get(i).getSurname().equals(goodHenry.getSurname())) {
                result.add(pos, new Human(listOfHumans.get(i)));
                pos++;
            }
        }
        return result;
    }

    public static List<Human> deletHenryFromList(List<Human> listOfHumans, Human poorHenry) {
        List<Human> result = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < listOfHumans.size(); i++) {
            if (!poorHenry.equals(listOfHumans.get(i))) {
                result.add(pos, new Human(listOfHumans.get(i)));
                pos++;
            }
        }
        return result;
    }

    public static List<Set<Integer>> removeIntersectSets(List<Set<Integer>> listOfSets, Set<Integer> set) {
        List<Set<Integer>> result = new ArrayList<>();
        int pos = 0;
        boolean flag = true;
        Iterator<Integer> iter;

        for (int i = 0; i < listOfSets.size(); i++) {
            iter = listOfSets.get(i).iterator();
            while (iter.hasNext() && flag) {
                if (set.contains(iter.next())) {
                    flag = false;
                }
            }
            if (flag) {
                result.add(pos, listOfSets.get(i));
                pos++;
            }
            flag = true;
        }
        return result;
    }

    public static Set<Human> getSetOfOldest(List<Human> listOfHumans) {
        Set<Human> result = new HashSet<>();
        int maxAge = 0;
        for (int i = 0; i < listOfHumans.size(); i++) {
            if (maxAge < listOfHumans.get(i).getAge()) {
                result.clear();
                maxAge = listOfHumans.get(i).getAge();
                result.add(listOfHumans.get(i));
            }
            if (maxAge == listOfHumans.get(i).getAge()) {
                result.add(listOfHumans.get(i));
            }
        }
        return result;
    }

    public static List<Human> makeListFromSet(Set<Human> setOfHumans) {
        List<Human> result = new ArrayList<>();
        int pos = 0;
        int numberOfElem = 0;
        boolean flag = true;
        Iterator<Human> iter = setOfHumans.iterator();
        Human someJohn;
        while (iter.hasNext()) {
            if (result.isEmpty()) {
                result.add(0, iter.next());
                numberOfElem++;
            } else {
                someJohn = iter.next();
                while (flag) {

                    if (result.get(pos).getSurname().compareTo(someJohn.getSurname()) > 0) {
                        result.add(pos, someJohn);
                        flag = false;
                        numberOfElem++;
                    } else {
                        if ((result.get(pos).getSurname().compareTo(someJohn.getSurname()) == 0) &&
                                result.get(pos).getName().compareTo(someJohn.getName()) > 0) {
                            result.add(pos, someJohn);
                            flag = false;
                            numberOfElem++;
                        } else {
                            if (result.get(pos).getSurname().compareTo(someJohn.getSurname()) == 0 &&
                                    result.get(pos).getName().compareTo(someJohn.getName()) == 0 &&
                                    result.get(pos).getPatronymic().compareTo(someJohn.getPatronymic()) > 0) {
                                result.add(pos, someJohn);
                                flag = false;
                                numberOfElem++;
                            } else {
                                if (result.get(pos).getSurname().compareTo(someJohn.getSurname()) == 0 &&
                                        result.get(pos).getName().compareTo(someJohn.getName()) == 0 &&
                                        result.get(pos).getPatronymic().compareTo(someJohn.getPatronymic()) == 0) {
                                    result.add(pos, someJohn);
                                    flag = false;
                                    numberOfElem++;
                                }
                            }
                        }
                    }
                    pos++;
                    if (pos == numberOfElem) {
                        result.add(pos, someJohn);
                        flag = false;
                        numberOfElem++;
                    }
                }
                flag = true;
                pos = 0;
            }
        }
        return result;
    }

    public static Set<Human> identifeHumans(Map<Integer, Human> mapId, Set<Integer> setOfId) {
        Set<Human> result = new HashSet<>();
        Iterator<Integer> itr = setOfId.iterator();
        int id;
        while (itr.hasNext()) {
            id = itr.next();
            if (mapId.get(id) != null) {
                result.add(mapId.get(id));
            }
        }
        return result;
    }

    public static Set<Integer> getIdAge(Map<Integer, Human> mapId) {
        Set<Integer> result = new HashSet<>();
        Iterator<Human> iterValues = mapId.values().iterator();
        Iterator<Integer> iterKeys = mapId.keySet().iterator();
        Human bigJhonny;
        int id;
        while (iterValues.hasNext()) {
            bigJhonny = iterValues.next();
            id = iterKeys.next();
            if (bigJhonny.getAge() >= 18) {
                result.add(id);
            }
        }
        return result;
    }


    public static Map<Integer, Integer> getMapId(Map<Integer, Human> mapId) {
        Map<Integer, Integer> b=new HashMap<>();
        for (Integer i : mapId.keySet()) {
           b.put(i, mapId.get(i).getAge());

        }

        return b;
    }
private static List<Human>fun(int age,Set<Human> humans){
        List<Human> f=new ArrayList<>();
    for (Human h: humans) {
        if(age==h.getAge()){
            f.add(h);
        }
    }
        return f;
}
    public static Map<Integer, List<Human>> getAgeMap(Set<Human> humans) {

        Map<Integer, List<Human>> g=new HashMap<>();
        for (Human h : humans) {
            int age = h.getAge();
                g.put(age, fun(age,humans) );
            }

        return g;
    }


}