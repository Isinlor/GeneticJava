package Contract;

public interface StopCondition<T> {
    void reset();
    boolean shouldRun(Population<T> population);
}
