package io.github.retrobitcoder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CutsToRollsController implements Initializable{

    @FXML
    private ListView<Integer> cutsFeetList;

    @FXML
    private ListView<Integer> cutsInchesList;

    @FXML
    private ListView<Integer>rollsFeetList;

    @FXML
    private ListView<Integer>rollsInchesList;

    private ObservableList<Integer> cutsFeet = FXCollections.observableArrayList();
    private ObservableList<Integer> cutsInches = FXCollections.observableArrayList();

    private ObservableList<Integer> rollsFeet = FXCollections.observableArrayList();
    private ObservableList<Integer> rollsInches = FXCollections.observableArrayList();


    private ArrayList<Measurement> cuts = new ArrayList<>();
    private ArrayList<Measurement> rolls = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        cutsFeetList.setItems(cutsFeet);
        cutsInchesList.setItems(cutsInches);

        rollsFeetList.setItems(rollsFeet);
        rollsInchesList.setItems(rollsInches);

        cuts.add(new Measurement(1, 0));
        cuts.add(new Measurement(0, 24));
        cuts.add(new Measurement(2, 12));

        for (Measurement cut : cuts) {
            cutsFeet.add(cut.getFeet());
            cutsInches.add(cut.getInches());
        }

        rolls.add(new Measurement(1, 0));
        rolls.add(new Measurement(0, 24));
        rolls.add(new Measurement(2, 12));

        for (Measurement roll : rolls) {
            rollsFeet.add(roll.getFeet());
            rollsInches.add(roll.getInches());
        }

    }
}
