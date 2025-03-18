package io.github.retrobitcoder;

import java.util.Arrays;
import java.util.List;

public class Solver {
    private List<Measurement> cuts;
    private List<Measurement> rolls;

    private Measurement[][] knapsack;

    private boolean noCuts = false;
    private boolean noRolls = false;

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

    // TODO: get actual solution
    public String getSolutions() {
        StringBuilder builder = new StringBuilder();

        if (!noCuts && !noRolls) {
            for (Measurement cut : cuts) {
                builder.append(cut.getInches());
                builder.append(",");
            }

            builder.deleteCharAt(builder.length() - 1);
        } else {
            builder.append("Cuts and Rolls list can not be all 0s");
        }

        return builder.toString();
    }

    private Measurement getMeasurement(int index, int capacity) {
        return knapsack[index][capacity];
    } 

    // TODO: capcity needs to be in inches
    private Measurement calcKnapsack(int index, int capacity) {
        Measurement measurement = getMeasurement(index, capacity);

        // base
        if (measurement.getInches() != 0) {
            return measurement;
        } else if (index == 0 || capacity < 0) {
            return null;
        }

        Measurement result = null;

        // cut is more than roll capacity
        if (cuts.get(index).getInches() > capacity) {
            result = calcKnapsack(index - 1, capacity);
        } else {
            Measurement tmp1 = calcKnapsack(index - 1, capacity); // calc without including item;
            Measurement tmp2 = cuts.get(index).getInches() + calcKnapsack(index - 1, capacity - cuts.get(index).getInches());

            result = Measurement.max(tmp1, tmp2); // TODO: need a max function
        }

        return result;
    }

    public void solve() {
        noCuts = cuts.size() == 0;
        noRolls = rolls.size() == 0;

        if (!noCuts && !noRolls) {
            knapsack = new Measurement[cuts.size()][rolls.size()];

            for (int i = 0; i < cuts.size(); i++) {
                Arrays.fill(knapsack[i], null);
            }
        }
    }

}
