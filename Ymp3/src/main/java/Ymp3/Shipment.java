package Ymp3;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Shipment {
    private List<PackedGoods> list = new ArrayList<>();

    public Shipment(List<PackedGoods> list) {
        this.list = list;
    }

    public List<PackedGoods> getList() {
        return list;
    }

    public void setList(List<PackedGoods> list) {
        this.list = list;
    }

    public int weightBrutto() {
        int weight = 0;
        for (PackedGoods p : list) {
            weight += p.weightBruto();
        }
        return weight;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "list=" + list +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shipment shipment = (Shipment) o;
        return Objects.equals(list, shipment.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }
}
