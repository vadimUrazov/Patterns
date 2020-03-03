package university_;

import java.util.Objects;

public class Human {

    private String surname, name, patronymic;
    private int age;

    public Human(String surname, String name, String patronymic, int age) {
        setAge(age);
        setName(name);
        setSurname(surname);
        setPatronymic(patronymic);

    }

    public Human(Human freddy) {
        setSurname(freddy.surname);
        setName(freddy.name);
        setPatronymic(freddy.patronymic);
        setAge(freddy.age);

    }

    public String getSurname() {
        return this.surname;
    }

    public String getName() {
        return this.name;
    }

    public String getPatronymic() {
        return this.patronymic;
    }

    public int getAge() {
        return this.age;
    }

    public void setSurname(String surname) {
        if (surname == null || "".equals(surname)) {
            throw new IllegalArgumentException("Error");
        }
        this.surname = surname;
    }

    public void setName(String name) {
        if (name == null || "".equals(name)) {
            throw new IllegalArgumentException("Error");
        }
        this.name = name;
    }

    public void setPatronymic(String patronymic) {
        if (patronymic == null || "".equals(patronymic)) {
            throw new IllegalArgumentException("Error");
        }
        this.patronymic = patronymic;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Error");
        }
        this.age = age;
    }

    @Override
    public String toString() {
        return "Human{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age &&
                Objects.equals(surname, human.surname) &&
                Objects.equals(name, human.name) &&
                Objects.equals(patronymic, human.patronymic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(surname, name, patronymic, age);
    }
}
