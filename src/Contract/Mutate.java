package Contract;

public interface Mutate<T> {
    Population<T> run(Population<T> population);
}
