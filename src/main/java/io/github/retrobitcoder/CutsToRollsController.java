package io.github.retrobitcoder;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.StringConverter;

public class CutsToRollsController{

    @FXML
    private ListView<Measurement> cutsList;

    @FXML
    private ListView<Measurement>rollsList;

    private ObservableList<Measurement> cutsObservableList = FXCollections.observableArrayList();

    private ObservableList<Measurement> rollsObservableList = FXCollections.observableArrayList();

    private ArrayList<Measurement> cuts = new ArrayList<>(); // TODO: use with solver
    private ArrayList<Measurement> rolls = new ArrayList<>();

    private final int DEFAULT_LIST_SIZE = 100;

    private static final String DECIMAL_STRING = "\\.";

    // private final String SCROLL_BAR_PROPERTY = ".scroll-bar";

    // private void bindCutsScrollbars() {
    //     Node cutsInchesNode = cutsInchesList.lookup(SCROLL_BAR_PROPERTY);
    //     Node cutsFeetNode = cutsFeetList.lookup(SCROLL_BAR_PROPERTY);

    //     if (cutsInchesNode instanceof ScrollBar && cutsFeetNode instanceof ScrollBar) {
    //         final ScrollBar cutsFeetScrollbar = (ScrollBar) cutsFeetNode;
    //         final ScrollBar cutsInchScrollBar = (ScrollBar) cutsInchesNode;

    //         cutsFeetScrollbar.valueProperty().bindBidirectional(cutsInchScrollBar.valueProperty());
    //     }
    // }

    // private void bindRollsScrollbars() {
    //     Node rollsInchesNode = rollsInchesList.lookup(SCROLL_BAR_PROPERTY);
    //     Node rollsFeetNode = rollsFeetList.lookup(SCROLL_BAR_PROPERTY);

    //     if (rollsInchesNode instanceof ScrollBar && rollsFeetNode instanceof ScrollBar) {
    //         final ScrollBar rollsFeetScrollbar = (ScrollBar) rollsFeetNode;
    //         final ScrollBar rollsInchScrollBar = (ScrollBar) rollsInchesNode;

    //         rollsFeetScrollbar.valueProperty().bindBidirectional(rollsInchScrollBar.valueProperty());
    //     }
    // }

    // private void bindScrollbars() {
    //     cutsInchesList.setOnScroll(event -> bindCutsScrollbars());
    //     cutsFeetList.setOnScroll(event -> bindCutsScrollbars());

    //     // rollsInchesList.setOnScroll(event -> bindRollsScrollbars());
    //     // rollsFeetList.setOnScroll(event -> bindRollsScrollbars());
    // }

    @FXML
    public void initialize() {
        StringConverter<Measurement> converter = new StringConverter<Measurement>() {
            @Override
            public String toString(Measurement object) {
                return object == null ? "" : object.toString();
            }

            @Override
            public Measurement fromString(String string) {
                try {
                    String [] measurement = string.split(DECIMAL_STRING);

                    if (measurement.length < 2) {
                       if (measurement.length < 1) {
                        return null;
                       }

                       measurement = new String[]{measurement[0], "0"};
                    }

                    Integer feet = Integer.parseInt(measurement[0]);
                    Integer inches = Integer.parseInt(measurement[1]);

                    feet = feet < 0 ? 0 : feet;
                    inches = inches < 0 ? 0 : inches;

                    return new Measurement(feet, inches);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
            
        };

        cutsList.setItems(cutsObservableList);

        cutsList.setEditable(true);
        cutsList.setCellFactory(TextFieldListCell.forListView(converter));

        rollsList.setItems(rollsObservableList);

        rollsList.setEditable(true);
        rollsList.setCellFactory(TextFieldListCell.forListView(converter));

        for (int i = 0; i < DEFAULT_LIST_SIZE; i++) {
            cutsObservableList.add(new Measurement(0, 0));
            rollsObservableList.add(new Measurement(0, 0));
        }
    }
}
