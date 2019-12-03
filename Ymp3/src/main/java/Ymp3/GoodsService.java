package Ymp3;

import java.util.List;

public class GoodsService {
    public static int countByFilter(Shipment shipment, Filter filter) {
        int count = 0;
        for (PackedGoods p : shipment.getList()) {
            if (filter.apply(p.getName())) {
                count += p.getCount();
            }

        }


        return count;
    }

    public static boolean isPackSetGoodsOf(PackedGoods pack) {
        return pack instanceof PackSetGoods;
    }

    public static int deep(List<PackedGoods> p, Filter filter) {
        int count = 0;
        for (PackedGoods k : p) {
            if (isPackSetGoodsOf(k)) {
                List<PackedGoods> list = ((PackSetGoods) k).getList();
                count = deep(list, filter);
            } else {

                if (filter.apply(k.getName())) {
                    count += 1;

                }

            }
        }
        return count;
    }

    public static int countByFilterDeep1(Shipment sh, Filter filter) {
        int count = 0;
        for (PackedGoods pack : sh.getList()) {


            if (isPackSetGoodsOf(pack)) {

                List<PackedGoods> list = ((PackSetGoods) pack).getList();
                deep(list, filter);


                for (PackedGoods p : list) {
                    if (filter.apply(p.getName())) {
                        count += 1;
                    }
                }
            }
        }


        return count;
    }

//    public static int countByFilterDeep(Shipment sh, Filter filter) {
//        int count = 0;
//        for (PackedGoods pack : sh.getList()) {
//            if (pack.isPackegeSet()) {
//                List<PackedGoods> list = ((PackSetGoods) pack).getList();
//                for (PackedGoods p : list) {
//                    if (filter.apply(p.getName())) {
//                        count += 1;
//                        break;
//                    }
//                }
//
//            }
//        }
//
//        return count;
//    }

    public static boolean deepCheck(List<PackedGoods> p) {
        boolean z=true;
        for (PackedGoods k : p) {
            if (isPackSetGoodsOf(k)) {
                List<PackedGoods> list = ((PackSetGoods) k).getList();
               z= deepCheck(list);
            } else {

                if (!(k instanceof PackWeightGoods)) {
                    return false;

                }

            }
        }
        return z;
    }

    public static boolean checkAllWeighted(Shipment sh) {
        boolean isWeight=true;
        for (int i = 0; i < sh.getList().size(); i++) {
            if (sh.getList().get(i) instanceof PackSetGoods) {
                List<PackedGoods> list = ((PackSetGoods) sh.getList().get(i)).getList();
                isWeight = deepCheck(list);
            } else {
                if (!(sh.getList().get(i) instanceof PackWeightGoods)) {
                    return false;
                }
            }

        }

        return isWeight;
    }

}
