package TSP;

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
            for (int j = size/2; j < size; j++) {
                if(i == j) distances[i][j] = 0;
                double distance = ThreadLocalRandom.current().nextDouble(min, max);
                distances[i][j] = distance;
                distances[j][i] = distance;
            }
        }
    }

    public double getDistance(int from, int to) {
        return distances[from][to];
    }

    public int getSize() {
        return size;
    }

}
