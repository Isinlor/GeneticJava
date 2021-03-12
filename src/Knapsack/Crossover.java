package Knapsack;

import Contract.Population;

import java.util.*;

public class Crossover implements Contract.Crossover<Individual> {

    int crossedPopulationSize;
    double maxWeight;
    public Crossover(int crossedPopulationSize, double maxWeight) {
        this.crossedPopulationSize=crossedPopulationSize;
        this.maxWeight=maxWeight;
    }

    public Population<Individual> run(Population<Individual> population) {
        Set<Individual> individuals = population.getIndividuals();
        Set<Individual> newIndividuals = new HashSet<>(population.getIndividuals());
        for (int i=0; i<crossedPopulationSize; i++) {
            Individual A = selectRandomIndividual(individuals);
            Individual B = selectRandomIndividual(individuals);
            while (B.equals(A)) {
                B = selectRandomIndividual(individuals);
            }
            Individual child = applyCrossover(A, B);
            newIndividuals.add(child);
        }
        return new Population<Individual>(newIndividuals);
    }

    public Individual selectRandomIndividual(Set<Individual> individuals) {
        List<Individual> list = new LinkedList<Individual>(individuals);
        Random rand = new Random();
        int next = rand.nextInt(list.size());
        return list.get(next);
    }

    public Individual applyCrossover(Individual A, Individual B) {
        Set<Item> itemsA = A.items;
        Set<Item> itemsB = B.items;
        Set<Item> mixedItems = mix(itemsA, itemsB);
        return new Individual(mixedItems, A.maxWeight);
    }

    public Set<Item> mix(Set<Item> itemsA, Set<Item> itemsB) {
        // Randomly select items from each parent in turn, until maxWeight is
        Set<Item> itemsAandB = new HashSet<>();
        itemsAandB.addAll(itemsA);
        itemsAandB.addAll(itemsB);
        List<Item> listA = new LinkedList<>(itemsA);
        Collections.shuffle(listA);
        List<Item> listB = new LinkedList<>(itemsB);
        Collections.shuffle(listB);
        Set<Item> mixedItems = new HashSet<>();
        int i = 0;
        Item next;
        while ((calculateTotalWeight(new LinkedList<>(mixedItems))<maxWeight) && mixedItems.size()<itemsAandB.size()){
            Random rand = new Random();
            if (i%2==0) {
                int nextIndex = rand.nextInt(listA.size());
                next = listA.get(nextIndex);
            } else {
                int nextIndex = rand.nextInt(listB.size());
                next = listB.get(nextIndex);
            }
            if ((calculateTotalWeight(new LinkedList<>(mixedItems))+next.weight)<=maxWeight) {
                mixedItems.add(next);
            } else {
                break;
            }
            i++;
        }
        return mixedItems;
    }

    private double calculateTotalWeight(List<Item> items) {
        double weight = 0;
        for (int i=0; i<items.size(); i++) {
            weight = weight + items.get(i).weight;
        }
        return weight;
    }
    // TODO: Implement elitist crossover which retains the fittest two individuals from the family (incl. parents)
}
