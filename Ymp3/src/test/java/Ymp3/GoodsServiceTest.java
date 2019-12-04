package Ymp3;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class GoodsServiceTest {
    @Test
    public void countByFilterTest() {
        Packing pack = new Packing("pack", 12);
        PackPieceGoods pieceGoods = new PackPieceGoods("pie apple", "qwer", 12, pack, 2);
        PackWeightGoods weightGoods = new PackWeightGoods("pie orange", "qwer", pack, 1);

        List<PackedGoods> list = new ArrayList<>();

        PackSetGoods packSetGoods = new PackSetGoods(pack, list, "pie");
        list.add(weightGoods);
        list.add(pieceGoods);
        list.add(packSetGoods);


        Filter filter = new BeginStringFilter("pie");
        Shipment shipment = new Shipment(list);
        int count = GoodsService.countByFilter(shipment, filter);
        assertEquals(4, count);

    }

    @Test
    public void countByFilterDeepTest() {
        Packing pack = new Packing("pack", 12);
        PackPieceGoods pieceGoods = new PackPieceGoods("pie apple", "qwer", 12, pack, 2);
        PackWeightGoods weightGoods = new PackWeightGoods("pie orange", "qwer", pack, 1);
        PackPieceGoods pieceGoods1 = new PackPieceGoods(" apple", "qwer", 12, pack, 2);
        PackWeightGoods weightGoods1 = new PackWeightGoods(" orange", "qwer", pack, 1);
        PackPieceGoods pieceGoods2 = new PackPieceGoods(" meat", "qwerre", 12, pack, 2);
        PackWeightGoods weightGoods2 = new PackWeightGoods("pie pinnaple", "qwerw", pack, 12);
        Goods goods = new Goods("pe", "wer");
        Goods goods1 = new Goods("pie", "wer");
        List<PackedGoods> list = new ArrayList<>();
        List<PackedGoods> list1 = new ArrayList<>();
        List<PackedGoods> list2 = new ArrayList<>();
        List<PackedGoods> list3 = new ArrayList<>();
        list.add(weightGoods);
        list.add(pieceGoods);
        PackSetGoods packSetGoods = new PackSetGoods(pack, list, "ie");
        list1.add(pieceGoods1);
        list1.add(weightGoods1);
        PackSetGoods packSetGoods1 = new PackSetGoods(pack, list1, "e");
        list3.add(pieceGoods2);
        list3.add(weightGoods2);
        list3.add(goods);
        list3.add(goods1);
        PackSetGoods packSetGoods2 = new PackSetGoods(pack, list3, "iye");
        packSetGoods1.getList().add(packSetGoods2);
        list2.add(packSetGoods);
        list2.add(packSetGoods1);
list2.add(new PackWeightGoods("abcd", "qwer", pack, 1));
        Filter filter = new BeginStringFilter("abc");
        Shipment shipment = new Shipment(list2);
        assertEquals(1, GoodsService.countByFilterDeep(shipment, filter));

    }

    @Test
    public void checkAllWeightedTest() {
        Packing pack = new Packing("pack", 12);
        PackPieceGoods pieceGoods = new PackPieceGoods("pie apple", "qwer", 12, pack, 2);
        PackWeightGoods weightGoods = new PackWeightGoods("pie orange", "qwer", pack, 1);



        List<PackedGoods> list1 = new ArrayList<>();
        list1.add(pieceGoods);
        PackSetGoods packSetGoods1 = new PackSetGoods(pack, list1, "pi");

        PackSetGoods packSetGoodsWeight = new PackSetGoods(pack, list1, "pi");
        packSetGoodsWeight.getList().add(weightGoods);

        List<PackedGoods> list = new ArrayList<>();
        list.add(weightGoods);
        PackSetGoods packSetGoods = new PackSetGoods(pack, list, "pie");
        packSetGoods.getList().add(packSetGoods1);

        List<PackedGoods> list2 = new ArrayList<>();

        list2.add(packSetGoods);
        list2.add(packSetGoodsWeight);

        Shipment shipment = new Shipment(list2);
        assertFalse(GoodsService.checkAllWeighted(shipment));

        List<PackedGoods> list3 = new ArrayList<>();
        packSetGoodsWeight = new PackSetGoods(pack, list, "pi");
        list3.add(packSetGoodsWeight);
        Shipment shipment1 = new Shipment(list3);
        assertTrue(GoodsService.checkAllWeighted(shipment1));
    }
}
