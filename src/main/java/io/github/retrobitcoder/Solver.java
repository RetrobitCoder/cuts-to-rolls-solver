package io.github.retrobitcoder;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Solve the knapsack problem
     * @param index
     * @param capacity
     * @return
     */
    private Measurement calcKnapsack(int index, int capacity) {
        if (index < 0 || capacity < 0) {
            return Measurement.ZERO;
        }

        Measurement result = null;

        // cut is more than roll capacity
        if (cuts.get(index).getTotalInches() > capacity) {
            result = calcKnapsack(index - 1, capacity);
        } else {
            Measurement tmp1 = calcKnapsack(index - 1, capacity); // calc without including item
            Measurement tmp2 = Measurement.add(cuts.get(index),
                    calcKnapsack(index - 1, capacity - cuts.get(index).getTotalInches()));

            result = Measurement.max(tmp1, tmp2);
        }

        knapsack[index][capacity] = result;

        return result;
    }

    private void solveKnapsack() {
        knapsack = new Measurement[cuts.size()][rolls.get(0).getTotalInches()];

        for (int i = 0; i < cuts.size(); i++) {
            Arrays.fill(knapsack[i], Measurement.ZERO);
        }

        int index = cuts.size() - 1;
        int capacity = rolls.get(0).getTotalInches() - 1;

        calcKnapsack(index, capacity);

        ArrayList<Measurement> selected = new ArrayList<>();

        while (index >= 0 && capacity > 0) {
            if (knapsack[index][capacity] != Measurement.ZERO) {
                selected.add(cuts.get(index));

                capacity -= cuts.get(index).getTotalInches();
            }

            index--;
        }

        Solution solution = new Solution(rolls.get(0), selected);

        selected.forEach(c -> cuts.remove(c));

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
