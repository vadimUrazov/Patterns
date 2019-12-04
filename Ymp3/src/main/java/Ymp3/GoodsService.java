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

    public static int countByFilterDeep(Shipment sh, Filter filter) {

                return deep(sh.getList(), filter);

        }


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

        return deepCheck(sh.getList());

    }
}
