package TSP;

import Algorithm.*;
import Contract.Passover;
import Contract.Population;
import Contract.Select;
import TSP.*;

public class TSP {
    public static void main(String[] args) {

        Distances distances = new Distances(1000, 1, 10);
        Initialize initialize = new Initialize(300, distances);
        Mutate mutate = new Mutate(1, true, distances);
        Select<Individual> selectTopK = new SelectTopK(150);

        Population<Individual> population = initialize.run();
        System.out.println(population);
        System.out.println(mutate.run(population));

        Algorithm<Individual> algorithm = new Algorithm<>(
            initialize,
            new TimeStopCondition<Individual>(10*1000),
            selectTopK,
            new Crossover(100, true, distances),
            mutate,
            new Reporter()
        );

        algorithm.run();

    }
}
