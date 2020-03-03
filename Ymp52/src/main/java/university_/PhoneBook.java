package university_;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

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
        }else{
            ArrayList<String> bobsPhones = phoneBook.get(someChris);
            bobsPhones.add(phone);
            phoneBook.put(someChris, bobsPhones);
        }
    }

    public void deletePhone(String phone){
        Iterator<ArrayList<String>> iterValues = phoneBook.values().iterator();
        Iterator<Human> iterKeys = phoneBook.keySet().iterator();
        Human someRichard;
        ArrayList<String> allPhone;
        while(iterValues.hasNext()){
            allPhone=iterValues.next();
            someRichard=iterKeys.next();
            if(allPhone.remove(phone)){
                phoneBook.put(someRichard, allPhone);
            }
        }
    }

    public ArrayList<String> getPhonesOfHuman(Human someTrevor){
        return phoneBook.get(someTrevor);
    }

    public Human findHumanWithPhone(String phone){
        Human res = null;
        Iterator<ArrayList<String>> iterValues = phoneBook.values().iterator();
        Iterator<Human> iterKeys = phoneBook.keySet().iterator();
        Human somePhil;
        ArrayList<String> allPhone;
        boolean flag = true;
        while(iterValues.hasNext() && flag){
            allPhone=iterValues.next();
            somePhil=iterKeys.next();
            if(allPhone.contains(phone)){
                flag=false;
                res=somePhil;
            }
        }
        return res;
    }
}
