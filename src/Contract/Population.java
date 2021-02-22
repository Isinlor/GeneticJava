package Contract;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Population<T> {
    private Set<T> individuals;
    private int populationSize;

    public Population(List<T> individuals) {
        this(new HashSet<>(individuals));
    }

    public Population(Set<T> individuals) {
        this.individuals = individuals;
        this.populationSize = individuals.size();
    }

    public Set<T> getIndividuals() {
        Set<T> copy = new HashSet<>();
        copy.addAll(this.individuals);
        return copy;
    }

    public int getSize() {
        return populationSize;
    }

    public String toString() {
        return "Population{" +
            "populationSize=" + populationSize +
            ", individuals=" + individuals +
            '}';
    }

}
