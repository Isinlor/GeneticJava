package Algorithm;

import Contract.Population;
import Contract.StopCondition;

public class IterationStopCondition<T> implements StopCondition<T> {

    private int counter;
    private int maxIterations;

    public IterationStopCondition(int maxIterations) {
        this.maxIterations = maxIterations;
    }

    public void reset() {
        counter = 0;
    }

    public boolean shouldRun(Population<T> population) {
        counter++;
        return counter <= maxIterations;
    }

}
