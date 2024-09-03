package io.github.retrobitcoder;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class CutsToRollsController implements Initializable{

    @FXML
    private ListView<Measurement> cutsList;

    @FXML
    private ListView<Measurement>rollsList;

    private ObservableList<Measurement> cuts = FXCollections.observableArrayList();
    private ObservableList<Measurement> rolls = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        cutsList.setItems(cuts);
        rollsList.setItems(rolls);

        cuts.add(new Measurement(1, 0));
        cuts.add(new Measurement(0, 24));
        cuts.add(new Measurement(2, 12));

        rolls.add(new Measurement(1, 0));
        rolls.add(new Measurement(0, 24));
        rolls.add(new Measurement(2, 12));

    }
}
