package Knapsack;

import Contract.Population;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Initialize implements Contract.Initialize<Individual> {

    private final int populationSize;
    private final Set<Item> allItems;

    public Initialize(int populationSize, Set<Item> allItems) {
        this.populationSize = populationSize;
        this.allItems = allItems;
    }

    public Population<Individual> run() {
        Item[] allItemsArray = allItems.toArray(new Item[0]);
        for (int i = 0; i < populationSize; i++) {
            Set<Item> items = new HashSet<>();
            int itemIndex = ThreadLocalRandom.current().nextInt(0, allItemsArray.length);
        }
        return new Population<>();
    }

}
