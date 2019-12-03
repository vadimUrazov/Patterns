package Ymp3;

import java.util.Objects;

public class PackPieceGoods extends PieceGoods implements PackedGoods {
    private Packing packing;
    private int count;

    public PackPieceGoods(String name, String description, int weight,
                          Packing packing, int count) {
        super(name, description, weight);
        this.packing = packing;
        this.count = count;
    }

    public Packing getPacking() {
        return packing;
    }

    public void setPacking(Packing packing) {
        this.packing = packing;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public int weightNetto() {
        return count * getWeight();
    }

    @Override
    public boolean isPackegeSet() {
        return false;
    }

    @Override
    public boolean isWeight() {
        return false;
    }


    public int weightBruto() {
        return weightNetto() + packing.getWeight();
    }

    @Override
    public String toString() {
        return "PackPieceGoods{" +
                "packing=" + packing +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PackPieceGoods that = (PackPieceGoods) o;
        return count == that.count &&
                packing.equals(that.packing);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), packing, count);
    }
}
