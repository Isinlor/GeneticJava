package Knapsack;

import Contract.Population;

import java.util.*;

public class SelectTopK implements Contract.Select<Individual> {

    private int selectionSize;

    public SelectTopK(int selectionSize) {
        this.selectionSize = selectionSize;
    }

    public Population<Individual> run(Population<Individual> population) {
        ArrayList<Individual> individuals = new ArrayList<>(population.getIndividuals());
        individuals.sort(Comparator.comparingDouble(individual -> -individual.getValue()));
        List<Individual> selectedIndividuals = individuals.subList(0, selectionSize);
        return new Population<>(selectedIndividuals);
    }

}
