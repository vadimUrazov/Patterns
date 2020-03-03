package university_;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TestData {
    @Test
    public  void testConstructNoParametr() {
        Data obj = new Data();
        assertNotNull(obj);
        assertEquals(obj.getNameOfSet(), "Default Data");
        assertEquals(obj.getSet(), new ArrayList<Group>());
    }

    @Test
    public  void testConstruct()  {
        Data obj = new Data("Jhonny", new Group(),
                new Group(1, 1, 2, 3), new Group(2, 2, 8));
        ArrayList<Group> res = new ArrayList<>();
        res.add(new Group());
        res.add(new Group(1, 1, 2, 3));
        res.add(new Group(2, 2, 8));
        assertNotNull(obj);
        assertEquals(obj.getSet(), res);
        assertEquals(obj.getNameOfSet(), "Jhonny");
    }

    @Test
    public  void testConstructCopyAndCopyWithName()  {
        Data obj = new Data("Jhonny", new Group(),
                new Group(1, 1, 2, 3), new Group(2, 2, 8));
        Data copyObj = new Data(obj);
        assertNotNull(copyObj);
        assertEquals(obj.getNameOfSet(), copyObj.getNameOfSet());
        assertEquals(copyObj.getSet(), obj.getSet());
        obj = new Data(copyObj, "Willy");
        assertNotNull(obj);
        assertEquals(obj.getSet(), copyObj.getSet());
        assertNotEquals(obj.getNameOfSet(), copyObj.getNameOfSet());
        assertEquals(obj.getNameOfSet(), "Willy");
    }

    @Test
    public  void testSetters()  {
        Data obj = new Data();
        obj.setNameOfSet("Polly");
        ArrayList<Group> res = new ArrayList<>();
        res.add(new Group());
        res.add(new Group(1, 1, 2, 3));
        res.add(new Group(2, 2, 8));
        obj.setSet(res);
        assertEquals(obj.getNameOfSet(), "Polly");
        assertEquals(obj.getSet(), res);
        obj.setSet(new Group(1,1,1,1,1), new Group(2,2,2,2), new Group(3,3));
        ArrayList<Group> secondRes = new ArrayList<>();
        secondRes.add(new Group(1,1,1,1,1));
        secondRes.add(new Group(2,2,2,2));
        secondRes.add(new Group(3,3));
        assertEquals(obj.getSet(), secondRes);
    }

    @Test
    public  void testAdd()  {
        Data obj = new Data("Jhonny", new Group(),
                new Group(1, 1, 2, 3), new Group(2, 2, 8));
        ArrayList<Group> res = new ArrayList<>();
        res.add(new Group());
        res.add(new Group(1, 1, 2, 3));
        res.add(new Group(2, 2, 8));
        res.add(new Group(3, 8,9,1,2));
        res.add(new Group(33, 48,9,31,25));
        obj.addSet(new Group(3, 8,9,1,2), new Group(33, 48,9,31,25));
        assertEquals(obj.getSet(), res);
        ArrayList<Group> res2 = new ArrayList<>();
        res2.add(new Group(33, 48,9,31,25));
        res2.add(new Group(37, 88,19,1,2));
        res2.add(new Group(21, 155, 2, 23));
        res2.add(new Group(782, 82, 81));
        res.addAll(res2);
        obj.addSet(res2);
        assertEquals(obj.getSet(), res);
    }

    @Test
    public  void testLength()  {
        Data obj = new Data();
        assertEquals(obj.length(), 0);
        ArrayList<Group> res = new ArrayList<>();
        res.add(new Group());
        res.add(new Group(1, 1, 2, 3));
        obj.setSet(res);
        assertEquals(obj.length(), 2);
        res.add(new Group(2, 2, 8));
        res.add(new Group(3, 8,9,1,2));
        res.add(new Group(33, 48,9,31,25));
        obj.setSet(res);
        assertEquals(obj.length(), 5);
    }

    @Test
    public  void testIterator()  {
        Data obj = new Data("Jhonny", new Group(),
                new Group(1, 23, 2, 3), new Group(2, 2, 8));
        assertNotNull(obj.iterator());
        assertTrue(obj.iterator().hasNext());
        assertEquals(obj.iterator().next(), (Integer)23);
    }


}
