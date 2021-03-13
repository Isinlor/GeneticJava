package Algorithm;

import Contract.*;

public class Algorithm<T> {

    private final boolean print;
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
        this(initialize, stopCondition, select, crossover, mutate, reporter, false);
    }

    public Algorithm(
        Initialize<T> initialize,
        StopCondition<T> stopCondition,
        Select<T> select,
        Crossover<T> crossover,
        Mutate<T> mutate,
        Reporter<T> reporter,
        boolean print
    ) {
        this.initialize = initialize;
        this.stopCondition = stopCondition;
        this.select = select;
        this.crossover = crossover;
        this.mutate = mutate;
        this.reporter = reporter;
        this.print = print;
    }

    public Population<T> run() {
        reporter.reset();
        stopCondition.reset();
        Population<T> population = initialize.run();
        reporter.report(population);
        while(stopCondition.shouldRun(population)) {
            population = select.run(population);
            if(print) System.out.print("select " + population.getSize());
            population = crossover.run(population);
            if(print) System.out.print("| crossover " + population.getSize());
            population = mutate.run(population);
            if(print) System.out.print("| mutate " + population.getSize() + "\n");
            reporter.report(population);
        }
        return population;
    }

}
