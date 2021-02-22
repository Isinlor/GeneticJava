package TSP;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Distances {

    private final int size;
    private final double[][] distances;

    public Distances(int size, double min, double max) {
        this.size = size;
        distances = new double[size][size];
        for (int i = 0; i < size; i++) {
            distances[i] = new double[size];
        }
        for (int i = 0; i < size/2; i++) {
            for (int j = 0; j < size; j++) {
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
        for (int i = 1; i < trip.length; i++) {
            length += getDistance(trip[i - 1], trip[i]);
        }
        length += getDistance(trip[trip.length - 1], trip[0]);
        return length;
    }

    public double getDistance(int from, int to) {
        if(from == to) throw new RuntimeException("Wrong trip.");
        return distances[from][to];
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
