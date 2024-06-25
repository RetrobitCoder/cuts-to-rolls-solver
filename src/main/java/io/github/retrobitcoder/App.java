package io.github.retrobitcoder;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application
{
    private static double[] cuts = {10.8, 16.4, 22.0, 5.0, 4.0, 8.6};
    private static double[] rolls = {31.6, 100.0};
    
    private static double[][] knapsack;

    private static ArrayList<Double> selected = new ArrayList<>();

    public static double calcKnapsack(int index, double capacity) {
        int capacityIndex = (int)Math.round(capacity) - 1;

        // base
        if (knapsack[index][capacityIndex] != 0.0) {
            return knapsack[index][capacityIndex];
        } else if (index == 0 || capacity <= 0.0) {
            return 0.0;
        }

        double result;

        // cut is more than roll capacity
        if (cuts[index] > capacity) {
            result = calcKnapsack(index - 1, capacity);
        } else {
            double tmp1 = calcKnapsack(index - 1, capacity); // calc without including item
            double tmp2 = cuts[index] + calcKnapsack(index - 1, capacity - cuts[index]); // calc including item

            result = Double.max(tmp1, tmp2);
        }

        knapsack[index][capacityIndex] = result;

        return result;
    }

    @Override
    public void start(Stage stage) { // TODO: refactor this to use fxml
        Label label = new Label("Hello World");
        Scene scene = new Scene(new StackPane(label), 640, 480);

        stage.setScene(scene);
        stage.show();
    }
    public static void main( String[] args )
    {
        // double totalCutSize = 0.0;

        // for (double cut : cuts) {
        //     totalCutSize += cut;
        // }

        // if (rolls[0] > totalCutSize) {
        //     System.out.println("All cuts");
        // }

        // knapsack = new double[cuts.length][(int)Math.round(rolls[0])];

        // for (int i = 0; i < cuts.length; i++) {
        //     Arrays.fill(knapsack[i], 0);
        // }

        // System.out.println(calcKnapsack(cuts.length - 1, rolls[0]));

        // int index = cuts.length - 1;
        // int weight = (int)Math.round(rolls[0]) - 1;

        // while (index > 0) {
        //     if (knapsack[index][weight] != knapsack[index - 1][weight]) {
        //         selected.add(cuts[index]);
        //         weight -= (int) Math.round(cuts[index]);
        //     }

        //     index--;
        // }

        // selected.forEach(val -> System.out.println("Val " + val));

        // // TODO: cleanup, remove selected from cuts list and do again for next roll item

        launch();
    }
}
