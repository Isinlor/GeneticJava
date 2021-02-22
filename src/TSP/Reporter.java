package TSP;

import Contract.Population;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Reporter implements Contract.Reporter<Individual> {

    public void report(Population<Individual> population) {
        ArrayList<Individual> individuals = new ArrayList<>(population.getIndividuals());
        individuals.sort(Comparator.comparingDouble((Individual::getLength)));
        System.out.println(individuals.get(0).getLength());
    }

}
