package university_;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestHuman {
    @Test
    public  void testConstruct()  {
        Human obj = new Human("Abc", "Abc", "Abc", 1);
        assertNotNull(obj);
        Human obj2 = new Human(obj);
        assertNotNull(obj2);
        assertEquals(obj.getAge(), obj2.getAge());
        assertEquals(obj.getName(), obj2.getName());
        assertEquals(obj.getPatronymic(), obj2.getPatronymic());
        assertEquals(obj.getSurname(), obj2.getSurname());
    }

    @Test
    public  void testConstructExc(){

        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> {
            Human obj = new Human("Abc", "ABc", "asd", -5);
        });

        assertNotNull(thrown.getMessage());
    }

    @Test
    public  void testSettersAndGetters(){
        Human obj = new Human("abc", "ad", "s", 10);
        obj.setAge(40);
        obj.setName("Lll");
        obj.setPatronymic("Kkk");
        obj.setSurname("Aaa");
        assertEquals(obj.getSurname(), "Aaa");
        assertEquals(obj.getPatronymic(), "Kkk");
        assertEquals(obj.getName(), "Lll");
        assertEquals(obj.getAge(), 40);
    }

}
