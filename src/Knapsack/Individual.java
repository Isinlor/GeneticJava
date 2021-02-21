package Knapsack;

import java.util.HashSet;
import java.util.Set;

public class Individual {

    private Set<Item> items = new HashSet<>();

    public Individual(Set<Item> items) {
        this.items = items;
    }

    public double getValue() {
        double value = 0;
        for (Item item: items) {
            value += item.value;
        }
        return value;
    }

    public boolean isValid(double maxWeight) {
        double weight = 0;
        for (Item item: items) {
            weight += item.weight;
            if(weight > maxWeight) return false;
        }
        return true;
    }

}
