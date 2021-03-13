package Experiment;

import Algorithm.*;
import Contract.*;
import TSP.Distances;
import TSP.Individual;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FullExperiment {

    public static void main(String[] args) {

        System.out.println("\n\nTSP\n");

        TSP.Crossover tspCrossover;
        StopCondition<TSP.Individual> tspStopCondition;

        int tspPopulationSize = 300;
        TSP.Distances distances = new TSP.Distances(1000, 1, 10);
        tspCrossover = new TSP.Crossover(100, true, distances);

        tspStopCondition = new TimeStopCondition<TSP.Individual>(10 * 1000);
        System.out.println("Crossover: " + runTSPExperiment(
            tspPopulationSize, distances, tspStopCondition, tspCrossover
        ));
        System.out.println("Passover: " + runTSPExperiment(
            tspPopulationSize, distances, tspStopCondition, new Passover<>()
        ));

        tspStopCondition = new IterationStopCondition<Individual>(1000);
        System.out.println("Crossover: " + runTSPExperiment(
            tspPopulationSize, distances, tspStopCondition, tspCrossover
        ));
        System.out.println("Passover: " + runTSPExperiment(
            tspPopulationSize, distances, tspStopCondition, new Passover<>()
        ));


        System.out.println("\n\nKnapsack\n");

        Knapsack.Crossover knapsackCrossover;
        StopCondition<Knapsack.Individual> knapsackStopCondition;

        int knapsackPopulationSize = 100;
        double maxWeight = 10;

        knapsackCrossover = new Knapsack.Crossover(knapsackPopulationSize, maxWeight);

        knapsackStopCondition = new TimeStopCondition<Knapsack.Individual>(10 * 1000);
        System.out.println("Crossover: " + runKnapsackExperiment(
            knapsackPopulationSize, maxWeight, knapsackStopCondition, knapsackCrossover
        ));
        System.out.println("Passover: " + runKnapsackExperiment(
            knapsackPopulationSize, maxWeight, knapsackStopCondition, new Passover<>()
        ));

        knapsackStopCondition = new IterationStopCondition<Knapsack.Individual>(1000);
        System.out.println("Crossover: " + runKnapsackExperiment(
            knapsackPopulationSize, maxWeight, knapsackStopCondition, knapsackCrossover
        ));
        System.out.println("Passover: " + runKnapsackExperiment(
            knapsackPopulationSize, maxWeight, knapsackStopCondition, new Passover<>()
        ));

    }

    public static Map<String, Double> runTSPExperiment(
        int populationSize, Distances distances,
        StopCondition<TSP.Individual> stopCondition,
        Crossover<TSP.Individual> crossover
    ) {

        TSP.Initialize initialize = new TSP.Initialize(populationSize, distances);
        TSP.Mutate mutate = new TSP.Mutate(1, true, distances);
        Select<TSP.Individual> selectTopK = new TSP.SelectTopK(150);

        Population<TSP.Individual> population = initialize.run();

        GenericReporter<TSP.Individual> reporter = new GenericReporter<TSP.Individual>();

        Algorithm<TSP.Individual> algorithm = new Algorithm<>(
            initialize,
            stopCondition,
            selectTopK,
            crossover,
            mutate,
            reporter
        );

        algorithm.run();

        return reporter.getLastEntry();

    }

    public static Map<String, Double> runKnapsackExperiment(
        int populationSize, double maxWeight,
        StopCondition<Knapsack.Individual> stopCondition,
        Crossover<Knapsack.Individual> crossover
    ) {

        double mutationProbability = 0.008;

        // Initialise the set of n items

        Set<Knapsack.Item> allItems = new HashSet<>();
        for (int i=0; i< populationSize * 1000; i++) {
            allItems.add(new Knapsack.Item(i));
        }

        Knapsack.Initialize initialize = new Knapsack.Initialize(populationSize, allItems, maxWeight);
        Knapsack.Select select = new Knapsack.Select(10);
        Knapsack.Mutate mutate = new Knapsack.Mutate(mutationProbability, allItems);
        GenericReporter<Knapsack.Individual> reporter = new GenericReporter<Knapsack.Individual>();

        Algorithm<Knapsack.Individual> algorithm = new Algorithm<Knapsack.Individual>(
            initialize,
            stopCondition,
            new Knapsack.SelectTopK(10),
            crossover,
            mutate,
            reporter
        );
        algorithm.run();

        return reporter.getLastEntry();

    }

}
