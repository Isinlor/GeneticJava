package Contract;
import TSP.Individual;

import java.util.*;

public class GenericReporter<T extends ScoreReport> implements Reporter<T> {

    private int counter;
    private long startTime;
    private List<Map<String, Double>> history = new ArrayList<>();

    public void reset() {
        counter = 0;
        history = new ArrayList<>();
        startTime = System.currentTimeMillis();
    }

    public void report(Population<T> population) {

        ArrayList<T> individuals = new ArrayList<>(population.getIndividuals());
        individuals.sort(Comparator.comparingDouble((ScoreReport::getScore)));
        double minScore = individuals.get(0).getScore();
        double maxScore = individuals.get(individuals.size() - 1).getScore();



        Map<String, Double> entry = new LinkedHashMap<>();
        entry.put("min", minScore);
        entry.put("max", maxScore);
        entry.put("iteration", (double)counter);
        entry.put("time", (double)(System.currentTimeMillis() - startTime));

        history.add(entry);
        counter++;

    }

    public List<Map<String, Double>> getHistory() {
        return history;
    }

    public Map<String, Double> getLastEntry() {
        return history.get(history.size() - 1);
    }

}