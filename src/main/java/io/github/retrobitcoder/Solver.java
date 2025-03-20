package io.github.retrobitcoder;

import java.util.ArrayList;
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
        // TODO: probs can combine
        if (measurement.getTotalInches() != 0) {
            return measurement;
        } else if (index == 0 || capacity <= 0) {
            return measurement;
        }

        Measurement result = null;

        // cut is more than roll capacity
        if (cuts.get(index).getTotalInches() > capacity) {
            result = calcKnapsack(index - 1, capacity);
        } else {
            Measurement tmp1 = calcKnapsack(index - 1, capacity); // calc without including item;
            Measurement tmp2 = Measurement.add(cuts.get(index), calcKnapsack(index - 1, capacity - cuts.get(index).getTotalInches()));

            result = Measurement.max(tmp1, tmp2);
        }

        knapsack[index][capacity] = result;

        return result;
    }

    public void solve() {
        noCuts = cuts.isEmpty();
        noRolls = rolls.isEmpty();

        if (!noCuts && !noRolls) {
            // TODO: need to update to handle a list
            knapsack = new Measurement[cuts.size()][rolls.get(0).getTotalInches()];

            for (int i = 0; i < cuts.size(); i++) {
                Arrays.fill(knapsack[i], new Measurement(0, 0));
            }

            int index = cuts.size() - 1;
            int capacity = rolls.get(0).getTotalInches() - 1;

            calcKnapsack(index, capacity);

            for (int i = 0; i < cuts.size(); i++) {
                for (int j = 0; j < rolls.get(0).getTotalInches(); j++) {
                    int val = knapsack[i][j].getTotalInches();

                    if (val != 0) System.out.print("(" + i + "," + j + ")" + ":" + val + " ");
                }
                System.out.println();
            }

            ArrayList<Measurement> selected = new ArrayList<>();

            while (index > 0) {
                if (knapsack[index][capacity] != knapsack[index - 1][capacity]) {
                    selected.add(cuts.get(index));

                    capacity -= cuts.get(index).getTotalInches();
                }

                index--;
            }

            // TODO: create solution obj
            selected.forEach(val -> System.out.println("Val " + val));
        }
    }

}
