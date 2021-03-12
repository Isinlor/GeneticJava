package Knapsack;

import Contract.Population;

import java.util.*;

public class Select implements Contract.Select<Individual> {
    private int tournamentSize;
    private Individual dummyIndividual = new Individual(new HashSet<>(), 0);
    public Select(int tournamentSize) {
        this.tournamentSize = tournamentSize;
    }

    public Population<Individual> run(Population<Individual> population) {
        return applyTournamentSelection(population, tournamentSize);
    }

    private Population<Individual> applyTournamentSelection(Population<Individual> population,
                                                            int tournamentSize) {
        Set<Individual> newIndividuals = new HashSet<Individual>();
        // select and run a tournament up to tournamentSize
        while (newIndividuals.size() < tournamentSize) {
            Population<Individual> tournament = selectTournament(population, tournamentSize);
            Individual winner = compete(tournament);
            newIndividuals.add(winner);
        }
        return new Population<Individual>(newIndividuals);
    }

    private Population<Individual> selectTournament(Population<Individual> population, int tournamentSize) {
        Set<Individual> fitIndividuals = population.getIndividuals();
        // Randomly select tournament from old population
        List<Individual> list = new LinkedList<Individual>(fitIndividuals);
        Collections.shuffle(list);
        Set<Individual> randomSet = new HashSet<Individual>(list.subList(0, tournamentSize));
        return new Population<Individual>(randomSet);
    }

    private Individual compete(Population<Individual> tournament) {
        // Select the fittest individual from the tournament
        Set<Individual> competitors = tournament.getIndividuals();
        Iterator<Individual> iterator = competitors.iterator();
        int maxValue = 0;
        Individual winner = dummyIndividual;
        while (iterator.hasNext()) {
            Individual currentCompetitor = iterator.next();
            if (currentCompetitor.getValue()>maxValue) {
                winner = currentCompetitor;
            }
        }
        return winner;
    }


}
