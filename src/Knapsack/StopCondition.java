package Knapsack;

import Contract.Population;

public class StopCondition implements Contract.StopCondition<Individual> {

    private int counter = 0;
    private int maxIterations;

    public StopCondition(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public void reset() {
        counter = 0;
    }

    public boolean shouldRun(Population<Individual> population) {
        counter++;
        return (counter<maxIterations);
    }
}
