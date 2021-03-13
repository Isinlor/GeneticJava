package TSP;

import Contract.ScoreReport;

import java.util.Arrays;

public class Individual implements ScoreReport {

    private int[] trip;
    private double length;

    public Individual(int[] trip, double length) {
        this.trip = trip;
        this.length = length;
    }

    public double getScore() {
        return getLength();
    }

    public int[] getTrip() {
        return trip;
    }

    public double getLength() {
        return length;
    }

    public String toString() {
        return "Individual{" +
            "length=" + length +
            ", trip=" + Arrays.toString(trip) +
            '}';
    }

}
