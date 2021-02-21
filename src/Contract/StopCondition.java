package Contract;

public interface StopCondition<T> {
    boolean shouldStop(Population<T> population);
}
