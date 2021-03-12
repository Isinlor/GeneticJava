package Knapsack;

import Contract.Population;

import java.util.*;

public class Mutate implements Contract.Mutate<Individual> {

    private double mutationProbability;
    private List<Item> allItems;

    public Mutate(double mutationProbability, Set<Item> allItems) {
        assert mutationProbability<1.0;
        this.mutationProbability = mutationProbability;
        this.allItems = new LinkedList<Item>(allItems);
    }

    public Population<Individual> run(Population<Individual> population) {
        // Apply mutation at population level
        Set<Individual> mutatedIndividuals = new HashSet<>();
        Iterator<Individual> iterator = population.getIndividuals().iterator();
        while(iterator.hasNext()) {
            Individual currentIndividual = iterator.next();
            Individual mutatedIndividual = mutate(currentIndividual);
            mutatedIndividuals.add(mutatedIndividual);
            mutatedIndividuals.add(currentIndividual);
        }
        return new Population<Individual>(mutatedIndividuals);
    }

    public Individual mutate(Individual individual) {
        List<Item> list = new LinkedList<Item>(individual.items);
        Set<Item> mutatedItems = new HashSet<>();
        // Change items
        for (int i=0; i<list.size(); i++) {
            Item currentItem = list.get(i);
            double random = Math.random();
            if (random<mutationProbability) {
                Random rand = new Random();
                mutatedItems.add(allItems.get(rand.nextInt(list.size())));
            } else {
                mutatedItems.add(currentItem);
            }
        }
        double random = Math.random();
        // Add new item with mutationProbability
        if (random<mutationProbability) {
            Collections.shuffle(allItems);
            if (calculateTotalWeight(new LinkedList<Item>(mutatedItems))<=allItems.get(0).value) {
                mutatedItems.add(allItems.get(0));
            }
        }
        return new Individual(mutatedItems, individual.maxWeight);
    }

    private double calculateTotalWeight(List<Item> items) {
        double weight = 0;
        for (int i=0; i<items.size(); i++) {
            weight = weight + items.get(i).weight;
        }
        return weight;
    }
}
