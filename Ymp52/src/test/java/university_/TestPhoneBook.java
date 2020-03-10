package university_;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestPhoneBook {
    @Test
    public void testConstruct(){
        PhoneBook obj = new PhoneBook();
        assertNotNull(obj);
    }

    @Test
    public void testAddPhoneAndFindHumanWithPhone()  {
        PhoneBook obj = new PhoneBook();
        Human someJacob = new Human("Abc", "Qwe", "Woops", 25);
        obj.addPhone("88005553535", someJacob);
        obj.addPhone("80976986859", someJacob);
        obj.addPhone("80145986859", someJacob);
        obj.addPhone("80546465869", new Human("Asd", "Bob", "Epsol", 22));
        obj.addPhone("23213753165", new Human("AdDsd", "Bodasdb", "Epsol", 22));
        obj.addPhone("68148945643", new Human("AsAsd", "Bsob", "Epasol", 22));
        assertEquals(obj.findHumanWithPhone("88005553535"), someJacob);
        assertEquals(obj.findHumanWithPhone("80976986859"), someJacob);
        assertEquals(obj.findHumanWithPhone("80145986859"), someJacob);
    }

    @Test
    public void testDeletePhoneAndGetPhonesOfHuman()  {
        PhoneBook obj = new PhoneBook();
        Human someJacob = new Human("Abc", "Qwe", "Woops", 25);
        obj.addPhone("88005553535", someJacob);
        obj.addPhone("80976986859", someJacob);
        obj.addPhone("80145986859", someJacob);
        obj.addPhone("80546465869", new Human("Asd", "Bob", "Epsol", 20));
        obj.addPhone("23213753165", new Human("AdDsd", "Bodasdb", "Epsol", 24));
        obj.addPhone("68148945643", new Human("AsAsd", "Bsob", "Epasol", 87));
        obj.deletePhone("88005553535");
        ArrayList<String> res = new ArrayList<>();
        res.add("80976986859");
        res.add("80145986859");
        assertEquals(obj.getPhonesOfHuman(someJacob), res);
    }
@Test
    public void testFindStartWithPhoneBook(){
    PhoneBook obj = new PhoneBook();

    obj.addPhone("80145986859", new Human("Abc", "Qwe", "Woops", 25));
    obj.addPhone("80546465869", new Human("Asd", "Bob", "Epsol", 22));
    obj.addPhone("23213753165", new Human("AdDsd", "Bodasdb", "Epsol", 22));
    obj.addPhone("68148945643", new Human("AsAsd", "Bsob", "Epasol", 22));
  PhoneBook hash=new PhoneBook();

    hash.addPhone("80145986859",new Human("Abc", "Qwe", "Woops", 25));

        assertTrue(obj.findHumanInPhones("Ab").equals(hash));


}
}
