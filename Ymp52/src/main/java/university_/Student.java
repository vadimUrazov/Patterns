package university_;

import java.util.Objects;

public class Student extends Human {

    private String facultyName;

    public Student(String surname, String name, String patronymic, int age) {
        super(surname, name, patronymic, age);
    }

    public Student(Human freddy) {
        super(freddy);
    }

    public Student(String surname, String name, String patronymic, int age, String facultyName) {
        super(surname, name, patronymic, age);
        this.facultyName = facultyName;
    }

    public Student(Human freddy, String facultyName) {
        super(freddy);
        this.facultyName = facultyName;
    }

    public Student(Student timmy) {
        super(timmy.getSurname(), timmy.getName(), timmy.getPatronymic(), timmy.getAge());
        this.facultyName = timmy.facultyName;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(getFacultyName(), student.getFacultyName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), getFacultyName());
    }
}

