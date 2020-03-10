package university_;

import java.util.*;

public class PhoneBook {
    private HashMap<Human, ArrayList<String>> phoneBook;

    public PhoneBook() {
        phoneBook = new HashMap<>();
    }

    public void addPhone(String phone, Human someChris) {
        if (!phoneBook.containsKey(someChris)) {
            ArrayList<String> phones = new ArrayList<>();
            phones.add(phone);
            phoneBook.put(someChris, phones);
        } else {
            ArrayList<String> bobsPhones = phoneBook.get(someChris);
            bobsPhones.add(phone);

        }
    }

    public void deletePhone(String phone) {
        // Iterator<ArrayList<String>> iterValues = phoneBook.values().iterator();
        // Iterator<Human> iterKeys = phoneBook.keySet().iterator();
        //Human someRichard;
        //  ArrayList<String> allPhone;
        for (Human h : phoneBook.keySet()) {
            ArrayList<String> l = phoneBook.get(h);
            //someRichard=new Human(h);
            //phoneBook.put(someRichard,l);
            l.remove(phone);
        }
//        while(iterValues.hasNext()){
//            allPhone=iterValues.next();
//            someRichard=iterKeys.next();
//            if(allPhone.remove(phone)){
//                phoneBook.put(someRichard, allPhone);
//            }
//        }
    }

    public ArrayList<String> getPhonesOfHuman(Human someTrevor) {
        return phoneBook.get(someTrevor);
    }

    public Human findHumanWithPhone(String phone) {

        for (Human h : phoneBook.keySet()) {
            if (phoneBook.get(h).contains(phone)) {

                return h;
            }


        }
        return null;
    }

    public PhoneBook findHumanInPhones(String surname) {
        PhoneBook map=new PhoneBook();

        for (Human h : phoneBook.keySet()) {

            if (h.getSurname().startsWith(surname)) {
                map.phoneBook.put(h, phoneBook.get(h));

            }
        }

        return map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneBook phoneBook1 = (PhoneBook) o;
        return Objects.equals(phoneBook, phoneBook1.phoneBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(phoneBook);
    }
}
