package Contract;

public interface Reporter<T> {
    void reset();
    void report(Population<T> population);
}
