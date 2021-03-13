package TSP;

import Contract.Population;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Crossover implements Contract.Crossover<Individual> {

    private double offspring;
    private boolean keepOriginals;
    private Distances distances;

    public Crossover(double offspring, boolean keepOriginals, Distances distances) {
        this.offspring = offspring;
        this.keepOriginals = keepOriginals;
        this.distances = distances;
    }

    public Population<Individual> run(Population<Individual> population) {
        List<Individual> individuals = new ArrayList<>();
        if(keepOriginals) {
            individuals.addAll(population.getIndividuals());
        }

        int tripStops = distances.getSize();
        for (int i = 0; i < offspring; i++) {

            int m = 0, n = 0;
            while(m == n) {
                m = ThreadLocalRandom.current().nextInt(0, individuals.size());
                n = ThreadLocalRandom.current().nextInt(0, individuals.size());
            }

            Individual individualM = individuals.get(m);
            Individual individualN = individuals.get(n);

            int leftStops = tripStops / 2;

            List<Integer> tripList = IntStream.of(
                    Arrays.copyOfRange(individualM.getTrip(), 0, leftStops)
                )
                .boxed()
                .collect(Collectors.toList());

            Set<Integer> selectedStops = new HashSet<Integer>(tripList);
            for (int stop: individualN.getTrip()) {
                if(selectedStops.contains(stop)) continue;
                tripList.add(stop);
            }

            int[] trip = tripList.stream().mapToInt(Integer::intValue).toArray();
            individuals.add(new Individual(
                trip, distances.getTripLength(trip)
            ));

        }

        return new Population<>(individuals);
    }
}
