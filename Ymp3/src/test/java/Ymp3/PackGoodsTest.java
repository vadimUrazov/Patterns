package Ymp3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class PackGoodsTest {

    @Test
    public void testPackWeightGoods() {
        Packing packing = new Packing("Abc", 12);
        PackWeightGoods p = new PackWeightGoods("abc", "kuku", packing, 25);
        PackWeightGoods p1 = new PackWeightGoods("abc", "kuku", packing, 25);
        assertTrue(p.equals(p1));
        assertFalse(!p.equals(p1));
        assertEquals(25, p.weightNetto());
        assertEquals(37, p.weightBruto());
    }

    @Test

    public void testPackPieceGoods() {
        Packing packing = new Packing("Abc", 12);
        PackPieceGoods p = new PackPieceGoods("abc", "kuku", 25, packing, 5);
        assertEquals(125, p.weightNetto());
        assertEquals(137, p.weightBruto());
        assertTrue(p.equals(p));
        assertFalse(!p.equals(p));
    }

    @Test
    public void testPackSetGoods() {
        List<PackedGoods> ps=new ArrayList<>();
        Packing packing = new Packing("Abc", 12);
        PackSetGoods p=new PackSetGoods(packing,ps,"ash");
        assertFalse(!p.equals(p));
       assertEquals( 0,p.weightNetto());
       assertEquals(12,p.weightBruto());
    }
}
