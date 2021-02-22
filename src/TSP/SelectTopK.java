package TSP;

import Contract.Population;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SelectTopK implements Contract.Select<Individual> {

    private int selectionSize;

    public SelectTopK(int selectionSize) {
        this.selectionSize = selectionSize;
    }

    public Population<Individual> run(Population<Individual> population) {
        ArrayList<Individual> individuals = new ArrayList<>(population.getIndividuals());
        individuals.sort(Comparator.comparingDouble((Individual::getLength)));
        List<Individual> selectedIndividuals = individuals.subList(0, selectionSize);
        return new Population<>(selectedIndividuals);
    }

}
