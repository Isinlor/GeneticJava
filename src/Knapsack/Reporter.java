package Knapsack;

import Contract.Population;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Reporter implements Contract.Reporter<Individual> {
    ArrayList<Double> topScoresPerRound = new ArrayList<>();

    public void report(Population<Individual> population) {
        ArrayList<Individual> individuals = new ArrayList<>(population.getIndividuals());
        ArrayList<Double> fitnessScores = aggregateFitnessScores(individuals);
        //printHistogram(fitnessScores);
        double[] arr = fitnessScores.stream().mapToDouble(d -> d).toArray();
        System.out.println("Average fitness of the population:\t" + Arrays.stream(arr).average().getAsDouble());
        individuals.sort(Comparator.comparingDouble((Individual::getValue)));
        double topScore = individuals.get(individuals.size()-1).getValue();
        topScoresPerRound.add(topScore);
        System.out.println(
                "Population size:" + population.getSize() + "\tTop fitness score of the population:\t" + topScore
        );
    }

    public ArrayList<Double> aggregateFitnessScores (ArrayList<Individual> individuals) {
        ArrayList<Double> fitnessScores = new ArrayList<>();
        for (Individual ind: individuals) {
            fitnessScores.add(ind.getValue());
        }
        return fitnessScores;
    }

    public void printHistogram(ArrayList<Double> scores) {
        ArrayList<Double> normalizedScores = normalize(scores);
        int numRows = 10;
        for (int i=0; i<numRows; i++) {
            for (int j=0; j<normalizedScores.size(); j++) {
                double score = normalizedScores.get(j)*numRows;
                if (score >= (numRows-i)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.print("\n");
        }
    }

    private ArrayList<Double> normalize(ArrayList<Double> scores) {
        // Normalize scores to be between 0 and 1
        double maxFitness = Collections.max(scores);
        ArrayList<Double> normalized = new ArrayList<>();
        for (int i=0; i<scores.size(); i++) {
            double score = scores.get(i);
            normalized.add(score/maxFitness);
        }
        return normalized;
    }
}
