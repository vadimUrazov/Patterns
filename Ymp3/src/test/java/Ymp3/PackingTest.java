package Ymp3;


import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class PackingTest {

    @Test
    public void testName() {
        String name="Abc";
        Packing packing=new Packing("Abc",12);
        assertTrue(packing.getName().equals(name));
    }

    @Test
    public void testWeight() {
        String name="Abc";int weight=12;
        Packing packing=new Packing("Abc",12);
        assertTrue(packing.getWeight()==12);
    }

    @Test
    public void testEquals() {
        Packing packing=new Packing("Abc",12);
        Packing packingOther=new Packing("Abc",13);
        assertFalse(packingOther.equals(packing));
        assertTrue(packing.equals(packing));
    }
}
