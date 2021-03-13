package Knapsack;

import Contract.ScoreReport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Individual implements ScoreReport {

    public Set<Item> items;
    public double maxWeight;

    public Individual(Set<Item> items, double maxWeight) {
        this.items = items;
        this.maxWeight = maxWeight;
    }

    public double getScore() {
        return getValue();
    }

    public double getValue() {
        double value = 0;
        for (Item item: items) {
            value += item.value;
        }
        return value;
    }

    public boolean isValid() {
        double weight = 0;
        for (Item item: items) {
            weight += item.weight;
            if(weight > this.maxWeight) return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder itemString = new StringBuilder();
        itemString.append("[");
        for (Item item: this.items) {
            itemString.append(item.toString());
        }
        itemString.append("]");
        return "Individual knapsack {" +
                "Validity=" + isValid() +
                ", Number of items=" + items.size() +
                ", Value=" + getValue() +
                ", Items=" + itemString.toString() +
                '}';
    }

//    public Set<Item> getItems() {
//        return new HashSet<>(this.items);
//    }

}
