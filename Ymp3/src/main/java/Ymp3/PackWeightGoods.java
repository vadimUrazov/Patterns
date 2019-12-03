package Ymp3;

import java.util.Objects;

public class PackWeightGoods extends WeightGoods implements PackedGoods {
    private Packing pack;
    private int weight;

    public PackWeightGoods(String name, String description, Packing pack, int weight) {
        super(name, description);
        this.pack = pack;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Packing getPack() {
        return pack;
    }

    public void setPack(Packing pack) {
        this.pack = pack;
    }

    @Override
    public int weightNetto() {
        return weight;
    }

    @Override
    public boolean isPackegeSet() {
        return false;
    }

    @Override
    public boolean isWeight() {
        return true;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public int weightBruto() {
        return pack.getWeight() + weight;
    }


    @Override
    public String toString() {
        return "PackWeightGoods{" +
                "pack=" + pack +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PackWeightGoods that = (PackWeightGoods) o;
        return weight == that.weight &&
                pack.equals(that.pack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pack, weight);
    }
}
