package TSP;

import Contract.Population;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Mutate implements Contract.Mutate<Individual> {

    private double mutationChance;
    private boolean keepOriginals;
    private Distances distances;

    public Mutate(double mutationChance, boolean keepOriginals, Distances distances) {
        this.mutationChance = mutationChance;
        this.keepOriginals = keepOriginals;
        this.distances = distances;
    }

    public Population<Individual> run(Population<Individual> population) {

        Set<Individual> individuals = new HashSet<>();
        for(Individual individual: population.getIndividuals()) {

            double draw = ThreadLocalRandom.current().nextDouble(0, 1);
            if(draw > mutationChance) {
                individuals.add(individual);
                continue;
            }

            if(keepOriginals) individuals.add(individual);

            int[] trip = individual.getTrip().clone();

            int i = 0, j = 0;
            while(i == j) {
                i = ThreadLocalRandom.current().nextInt(0, trip.length);
                j = ThreadLocalRandom.current().nextInt(0, trip.length);
            }

            int temp = trip[i];
            trip[i] = trip[j];
            trip[j] = temp;

            individuals.add(
                new Individual(trip, distances.getTripLength(trip))
            );

        }

        return new Population<>(individuals);

    }
}
