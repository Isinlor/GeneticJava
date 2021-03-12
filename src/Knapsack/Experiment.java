package Knapsack;

import Algorithm.Algorithm;
import Contract.Passover;
import Contract.Population;

import java.util.HashSet;
import java.util.Set;

public class Experiment {
    public static void main(String[] args) {
        int populationSize = 100;
        double maxWeight = 10;
        double mutationProbability = 0.008;

        // Initialize all items
        Set<Item> allItems = initializeItems(populationSize*100);
        Initialize initialize = new Initialize(populationSize, allItems, maxWeight);
        StopCondition stopCondition = new StopCondition(10000);
        Select select = new Select(10);
        Crossover crossover = new Crossover(populationSize, maxWeight);
        Mutate mutate = new Mutate(mutationProbability, allItems);
        Reporter reporter = new Reporter();
//
        Algorithm<Individual> algorithm = new Algorithm<Individual>(
                initialize,
                stopCondition,
                new SelectTopK(10),
//                new Passover(),
                crossover,
                mutate,
                reporter
        );
        algorithm.run();

    }

    private static Set<Item> initializeItems(int n) {
        // Initialise the set of n items
        Set<Item> allItems = new HashSet<>();
        for (int i=0; i<n; i++) {
            allItems.add(new Item(i));
        }
        return allItems;
    }
}
