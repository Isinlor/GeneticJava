package Knapsack;

public class Item {
    public int value;
    public int weight;

    public Item(int value) {
        this.value = value;

        this.weight = (int)(10.0 * Math.random());
    }

    public String toString() {

        return "(Value=" + this.value +
                ", Weight=" + this.weight + ")";
    }

}
