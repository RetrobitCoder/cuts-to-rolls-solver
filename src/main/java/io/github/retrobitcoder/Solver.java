package io.github.retrobitcoder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
    private List<Measurement> cuts;
    private List<Measurement> rolls;

    private List<Solution> solutions = new ArrayList<>();

    private Measurement[][] knapsack;

    private boolean noCuts = false;
    private boolean noRolls = false;

    public Solver() {
    }

    public Solver(List<Measurement> cuts, List<Measurement> rolls) {
        this.cuts = cuts;
        this.rolls = rolls;
    }

    public void setCuts(List<Measurement> cuts) {
        this.cuts = cuts;
    }

    public void setRolls(List<Measurement> rolls) {
        this.rolls = rolls;
    }

    public String getSolutions() {
        StringBuilder builder = new StringBuilder();

        if (!noCuts && !noRolls) {
            solutions.forEach(s -> {
                builder.append(s.roll() + ": ");
                builder.append(s.cuts().stream().map(Measurement::toString).collect(Collectors.joining(", ")));
                builder.append('\n');
            });

            builder.deleteCharAt(builder.length() - 1);
        } else {
            builder.append("Cuts and Rolls list can not be all 0s");
        }

        return builder.toString();
    }

    public String getUnusedCuts() {
        StringBuilder builder = new StringBuilder();

        if (!cuts.isEmpty()) {
            builder.append("Unused Cuts: ");
            
            cuts.forEach(cut -> {
                builder.append(cut + ",");
            });

            builder.deleteCharAt(builder.length() - 1);
        } else {
            builder.append("All cuts used");
        }

        return builder.toString();
    }

    /**
     * Solve the knapsack problem
     * @param index
     * @param capacity
     * @return
     */
    private Measurement calcKnapsack(int index, int capacity) {
        if (index == 0 || capacity == 0) {
            return Measurement.ZERO;
        }

        // if already computed, don't compute again
        if (knapsack[index][capacity].getTotalInches() != Measurement.NEGATIVE_ONE.getTotalInches()) {
            return knapsack[index][capacity];
        }

        // cut is more than roll capacity, so exclude item
        Measurement includeCut = Measurement.ZERO;
        if (cuts.get(index - 1).getTotalInches() <= capacity) {
            includeCut = Measurement.add(cuts.get(index - 1), calcKnapsack(index - 1, capacity - cuts.get(index - 1).getTotalInches()));
        }

        Measurement exlcudeCut = calcKnapsack(index - 1, capacity);

        knapsack[index][capacity] = Measurement.max(includeCut, exlcudeCut);

        return knapsack[index][capacity];
    }

    private void printKnapsack(int index, int capacity) {
        System.out.println("Printing knapsack");
        for (int i = 0; i <= index; i++){
            for (int j = 0; j <= capacity; j++) {
                System.out.print(knapsack[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void findCuts(ArrayList<Measurement> selected, int index, int capacity, int maxValue){
        for (int i = index; i >= 0; i--) {
            if (maxValue != knapsack[i][capacity].getTotalInches()) {
                Measurement cut = cuts.get(i);

                selected.add(cut);

                maxValue -= cut.getTotalInches();
                capacity -= cut.getTotalInches();
            }
        }
    }

    private void solveKnapsack() {
        knapsack = new Measurement[cuts.size() + 1][rolls.get(0).getTotalInches() + 1];

        for (int i = 0; i <= cuts.size(); i++) {
            for (int j = 0; j <= rolls.get(0).getTotalInches(); j++) {
                if (i == 0 || j == 0) {
                    knapsack[i][j] = Measurement.ZERO;
                } else {
                    knapsack[i][j] = Measurement.NEGATIVE_ONE;
                }
            }
        }

        int index = cuts.size();
        int capacity = rolls.get(0).getTotalInches();

        Measurement maxValue = calcKnapsack(index, capacity);

        ArrayList<Measurement> selected = new ArrayList<>();

        findCuts(selected, index, capacity, maxValue.getTotalInches());
        
        Solution solution = new Solution(rolls.get(0), selected);

        selected.forEach(cut -> cuts.remove(cut));

        rolls.remove(0);

        solutions.add(solution);
    }

    public void solve() {
        solutions.clear();

        noCuts = cuts == null ? cuts == null : cuts.isEmpty();
        noRolls = rolls == null ? rolls == null : rolls.isEmpty();

        if (!noCuts && !noRolls) {
            while (!rolls.isEmpty()) {
                solveKnapsack();
            }
        }
    }
}
