package Algorithm;

import Contract.Population;
import Contract.StopCondition;

public class TimeStopCondition<T> implements StopCondition<T> {

    private long startTime;
    private long maxTime;

    public TimeStopCondition(int maxTime) {
        this.maxTime = maxTime;
    }

    public void reset() {
        startTime = System.currentTimeMillis();
    }

    public boolean shouldRun(Population<T> population) {
        return System.currentTimeMillis() - startTime < maxTime;
    }

}
