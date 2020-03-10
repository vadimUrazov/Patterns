package university_;

import java.util.*;

public class ListDemo {


    public static List<Human> getListSameSurnameWithHenry(List<Human> listOfHumans, Human goodHenry) {
        List<Human> result = new ArrayList<>();


        for (int i = 0; i < listOfHumans.size(); i++) {
            if (listOfHumans.get(i).getSurname().equals(goodHenry.getSurname())) {
                result.add(new Human(listOfHumans.get(i)));

            }
        }
        return result;
    }

    public static List<Human> deletHenryFromList(List<Human> listOfHumans, Human poorHenry) {
        List<Human> result = new ArrayList<>();
        int pos = 0;
        for (int i = 0; i < listOfHumans.size(); i++) {
            if (!poorHenry.equals(listOfHumans.get(i))) {
                result.add(new Human(listOfHumans.get(i)));

            }
        }
        return result;
    }

    public static List<Set<Integer>> removeIntersectSets(List<Set<Integer>> listOfSets, Set<Integer> set) {
        List<Set<Integer>> result = new ArrayList<>();

        for (int i = 0; i < listOfSets.size(); i++) {
            boolean flag = true;
            Iterator<Integer> iter = listOfSets.get(i).iterator();
            while (iter.hasNext() && flag) {
                if (set.contains(iter.next())) {
                    flag = false;
                }
            }
            if (flag) {
                result.add(listOfSets.get(i));

            }
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
        while (itr.hasNext()) {
            int id = itr.next();
            if (mapId.containsKey(id)) {
                result.add(mapId.get(id));
            }
        }
        return result;
    }

    public static Set<Integer> getIdAge(Map<Integer, Human> mapId) {
        Set<Integer> result = new HashSet<>();

        for (Integer i : mapId.keySet()) {
            if (mapId.get(i).getAge() >= 18) {
                result.add(i);
            }
        }

        return result;
    }


    public static Map<Integer, Integer> getMapId(Map<Integer, Human> mapId) {
        Map<Integer, Integer> res = new HashMap<>();
        for (Integer i : mapId.keySet()) {
            res.put(i, mapId.get(i).getAge());

        }

        return res;
    }

    private static List<Human> fun(int age, Set<Human> humans) {
        List<Human> result = new ArrayList<>();
        for (Human h : humans) {
            if (age == h.getAge()) {
                result.add(h);
            }
        }
        return result;
    }

    public static Map<Integer, List<Human>> getAgeMap(Set<Human> humans) {

        Map<Integer, List<Human>> res = new HashMap<>();
        for (Human h : humans) {
            int age = h.getAge();
            if (!res.containsKey(age)) {
                res.put(age, fun(age, humans));
            }
        }

        return res;
    }


}