package TSP;

import Contract.Population;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Initialize implements Contract.Initialize<Individual> {

    private int populationSize;
    private Distances distances;

    public Initialize(int populationSize, Distances distances) {
        this.populationSize = populationSize;
        this.distances = distances;
    }

    public Population<Individual> run() {
        Set<Individual> individuals = new HashSet<>();
        for (int i = 0; i < populationSize; i++) {
            int tripLength = distances.getSize();
            int[] trip = new int[tripLength];
            Set<Integer> usedLocations = new HashSet<>();
            while (usedLocations.size() < tripLength) {
                int location = ThreadLocalRandom.current().nextInt(0, tripLength);
                if(usedLocations.contains(location)) continue;
                usedLocations.add(location);
                trip[usedLocations.size() - 1] = location;
            }
            individuals.add(
                new Individual(trip, distances.getTripLength(trip))
            );
        }
        return new Population<>(individuals);
    }

}
