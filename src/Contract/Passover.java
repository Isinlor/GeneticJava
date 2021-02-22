package Contract;

import Contract.Crossover;
import Contract.Population;

public class Passover<T> implements Crossover<T> {
    public Population<T> run(Population<T> population) {
        return population;
    }
}
