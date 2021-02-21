import Contract.*;

public class Algorithm<T> {

    private final Initialize<T> initialize;
    private final StopCondition<T> stopCondition;
    private final Select<T> select;
    private final Crossover<T> crossover;
    private final Mutate<T> mutate;

    public Algorithm(
        Initialize<T> initialize,
        StopCondition<T> stopCondition,
        Select<T> select,
        Crossover<T> crossover,
        Mutate<T> mutate
    ) {
        this.initialize = initialize;
        this.stopCondition = stopCondition;
        this.select = select;
        this.crossover = crossover;
        this.mutate = mutate;
    }

    public Population<T> run() {
        Population<T> population = initialize.run();
        while(stopCondition.shouldStop(population)) {
            population = select.run(population);
            population = crossover.run(population);
            population = mutate.run(population);
        }
        return population;
    }

}
