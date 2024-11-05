package io.github.retrobitcoder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

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


    private ArrayList<Measurement> cuts = new ArrayList<>(); // TODO: use with solver
    private ArrayList<Measurement> rolls = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        cutsFeetList.setItems(cutsFeet);
        cutsInchesList.setItems(cutsInches);

        StringConverter<Integer> converter = new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object.toString();
            }

            @Override
            public Integer fromString(String string) {
                try {
                    Integer value = Integer.parseInt(string);

                    value = value < 0 ? 0 : value;

                    return value;
                } catch (NumberFormatException e) {
                    return 0;
                }
            }
            
        };

        cutsFeetList.setEditable(true);
        cutsFeetList.setCellFactory(TextFieldListCell.forListView(converter));

        cutsInchesList.setEditable(true);
        cutsInchesList.setCellFactory(TextFieldListCell.forListView(converter));

        rollsFeetList.setItems(rollsFeet);
        rollsInchesList.setItems(rollsInches);

        rollsFeetList.setEditable(true);
        rollsFeetList.setCellFactory(TextFieldListCell.forListView(converter));

        rollsInchesList.setEditable(true);
        rollsInchesList.setCellFactory(TextFieldListCell.forListView(converter));

        for (int i = 0; i < 100; i++) {
            cutsFeet.add(0);
            cutsInches.add(0);
            rollsFeet.add(0);
            rollsInches.add(0);
        }
    }
}
