package university_;

import java.util.Objects;

public class Person {
    private String surname, name, middlename;
    private int date, mounth, year;

    public Person(String surname, String name, String middlename, int date, int mounth, int year) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.date = date;
        this.mounth = mounth;
        this.year = year;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getMounth() {
        return mounth;
    }

    public void setMounth(int mounth) {
        this.mounth = mounth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return date == person.date &&
                mounth == person.mounth &&
                year == person.year &&
                surname.equals(person.surname) &&
                name.equals(person.name) &&
                middlename.equals(person.middlename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, middlename, date, mounth, year);
    }
}
