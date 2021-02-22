package Algorithm;

import Contract.*;

public class Algorithm<T> {

    private final Initialize<T> initialize;
    private final StopCondition<T> stopCondition;
    private final Select<T> select;
    private final Crossover<T> crossover;
    private final Mutate<T> mutate;
    private final Reporter<T> reporter;

    public Algorithm(
        Initialize<T> initialize,
        StopCondition<T> stopCondition,
        Select<T> select,
        Crossover<T> crossover,
        Mutate<T> mutate,
        Reporter<T> reporter
    ) {
        this.initialize = initialize;
        this.stopCondition = stopCondition;
        this.select = select;
        this.crossover = crossover;
        this.mutate = mutate;
        this.reporter = reporter;
    }

    public Population<T> run() {
        stopCondition.reset();
        Population<T> population = initialize.run();
        reporter.report(population);
        while(stopCondition.shouldRun(population)) {
            population = select.run(population);
            population = crossover.run(population);
            population = mutate.run(population);
            reporter.report(population);
        }
        return population;
    }

}
