package university_;

import java.util.List;
import java.util.Objects;

public class House {
    private String numbHouse,address;
     private Person person;
     private List<Flat> list;

    public House(String numbHouse, String address, Person person, List<Flat> list) {
        this.numbHouse = numbHouse;
        this.address = address;
        this.person = person;
        this.list = list;
    }


    public String getNumbHouse() {
        return numbHouse;
    }

    public void setNumbHouse(String numbHouse) {
        this.numbHouse = numbHouse;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Flat> getList() {
        return list;
    }

    public void setList(List<Flat> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return numbHouse.equals(house.numbHouse) &&
                address.equals(house.address) &&
                person.equals(house.person) &&
                list.equals(house.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbHouse, address, person, list);
    }

}
