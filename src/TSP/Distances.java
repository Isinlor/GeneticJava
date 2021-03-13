package TSP;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Distances {

    private final int size;
    private final double[][] distances;
    private final double min;
    private final double max;

    public Distances(int size, double min, double max) {
        this.size = size;
        this.min = min;
        this.max = max;
        distances = new double[size][size];
        for (int i = 0; i < size; i++) {
            distances[i] = new double[size];
        }
        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if(i == j) {
                    distances[i][j] = 0;
                    continue;
                }
                double distance = ThreadLocalRandom.current().nextDouble(min, max);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
    }

    public double getTripLength(int[] trip) {
        double length = 0;
        for (int i = 1; i < size; i++) {
            length += getDistance(trip[i - 1], trip[i]);
        }
        length += getDistance(trip[size - 1], trip[0]);
        return length;
    }

    public double getTripLength(Integer[] trip) {
        double length = 0;
        for (int i = 1; i < size; i++) {
            length += getDistance(trip[i - 1], trip[i]);
        }
        length += getDistance(trip[size - 1], trip[0]);
        if(length < size * min || length > size * max) throw new RuntimeException("Wrong trip length.");
        return length;
    }

    public double getDistance(int from, int to) {
        if(from == to) throw new RuntimeException("Wrong trip.");
        double distance = distances[from][to];
        if(distance != distances[to][from]) throw new RuntimeException("Wrong distances.");
        if(distance <= 0 || distance < min || distance > max) throw new RuntimeException(
            "The distance: " + distance + " for from: " + from + " to: " + to + " is wrong!"
        );
        return distance;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return "Distances{" +
            "size=" + size +
            ", distances=" + Arrays.deepToString(distances) +
            '}';
    }
}
