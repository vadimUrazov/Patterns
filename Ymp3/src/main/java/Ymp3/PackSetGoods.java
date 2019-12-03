package Ymp3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//набор товаров
public class PackSetGoods implements PackedGoods {
    private Packing pack;
    private List<PackedGoods> list = new ArrayList<>();
    private String name;

    public PackSetGoods(Packing pack, List<PackedGoods> list, String name) {
        this.pack = pack;
        this.list.addAll(list);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean isPackegeSet() {
        return true;
    }

    @Override
    public boolean isWeight() {
        return false;
    }

    @Override
    public int getCount() {
        return 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Packing getPack() {
        return pack;
    }

    public void setPack(Packing pack) {
        this.pack = pack;
    }

    public List<PackedGoods> getList() {
        return list;
    }

    public void setList(List<PackedGoods> list) {
        this.list.addAll(list);
    }

    public int weightNetto() {
        int sum = 0;
        for (PackedGoods p : list) {
            sum += p.weightNetto();
        }
        return sum;
    }

    public int weightBruto() {
        return weightNetto() + pack.getWeight();
    }

    @Override
    public String toString() {
        return "PackSetGoods{" +
                "pack=" + pack +
                ", list=" + list +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackSetGoods that = (PackSetGoods) o;
        return Objects.equals(pack, that.pack) &&
                Objects.equals(list, that.list) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pack, list, name);
    }
}
