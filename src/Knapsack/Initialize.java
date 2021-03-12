package Knapsack;

import Contract.Population;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Initialize implements Contract.Initialize<Individual> {

    private final int populationSize;
    private final Set<Item> allItems;
    private final double maxWeight;

    public Initialize(int populationSize, Set<Item> allItems, double maxWeight) {
        this.populationSize = populationSize;
        this.allItems = allItems;
        this.maxWeight = maxWeight;
    }

    public Population<Individual> run() {
        Set<Individual> individuals = new HashSet<>();
        Item[] allItemsArray = allItems.toArray(new Item[0]);
        for (int i = 0; i < populationSize; i++) {
            Set<Item> selectedItems = fillKnapsack();
            individuals.add(new Individual(selectedItems, this.maxWeight));
        }
        return new Population<Individual>(individuals);
    }

    private Set<Item> fillKnapsack() {
        // The knapsack is packed with random items from allItems, until the next item would exceed maxWeight
        Set<Item> items = new HashSet<>();
        List<Item> list = new LinkedList<>(this.allItems);
        List<Item> sublist = new LinkedList<>();
        Collections.shuffle(list);
        int i = 0;
        while (calculateTotalWeight(sublist)<maxWeight) {
            Item next = list.get(i);
            if ((calculateTotalWeight(sublist)+next.weight)<=maxWeight) {
                sublist.add(next);
                items.add(next);
                i++;
            } else {
                break;
            }

        }
        return items;
    }

    private double calculateTotalWeight(List<Item> items) {
        double weight = 0;
        for (int i=0; i<items.size(); i++) {
            weight = weight + items.get(i).weight;
        }
        return weight;
    }

}
