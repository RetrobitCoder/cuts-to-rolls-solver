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

    private final int DEFAULT_LIST_SIZE = 100;

    private final String SCROLL_BAR_PROPERTY = ".scroll-bar";

    private void bindCutsScrollbars() {
        Node cutsInchesNode = cutsInchesList.lookup(SCROLL_BAR_PROPERTY);
        Node cutsFeetNode = cutsFeetList.lookup(SCROLL_BAR_PROPERTY);

        if (cutsInchesNode instanceof ScrollBar && cutsFeetNode instanceof ScrollBar) {
            final ScrollBar cutsFeetScrollbar = (ScrollBar) cutsFeetNode;
            final ScrollBar cutsInchScrollBar = (ScrollBar) cutsInchesNode;

            cutsFeetScrollbar.valueProperty().bindBidirectional(cutsInchScrollBar.valueProperty());
        }
    }

    private void bindRollsScrollbars() {
        Node rollsInchesNode = rollsInchesList.lookup(SCROLL_BAR_PROPERTY);
        Node rollsFeetNode = rollsFeetList.lookup(SCROLL_BAR_PROPERTY);

        if (rollsInchesNode instanceof ScrollBar && rollsFeetNode instanceof ScrollBar) {
            final ScrollBar rollsFeetScrollbar = (ScrollBar) rollsFeetNode;
            final ScrollBar rollsInchScrollBar = (ScrollBar) rollsInchesNode;

            rollsFeetScrollbar.valueProperty().bindBidirectional(rollsInchScrollBar.valueProperty());
        }
    }

    private void bindScrollbars() {
        cutsInchesList.setOnScroll(event -> bindCutsScrollbars());
        cutsFeetList.setOnScroll(event -> bindCutsScrollbars());

        rollsInchesList.setOnScroll(event -> bindRollsScrollbars());
        rollsFeetList.setOnScroll(event -> bindRollsScrollbars());
    }

    @FXML
    public void initialize() {
        cutsFeetList.setItems(cutsFeet);
        cutsInchesList.setItems(cutsInches);

        StringConverter<Integer> converter = new StringConverter<Integer>() {

            @Override
            public String toString(Integer object) {
                return object == 0 ? "" : object.toString();
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

        for (int i = 0; i < DEFAULT_LIST_SIZE; i++) {
            cutsFeet.add(0);
            cutsInches.add(0);
            rollsFeet.add(0);
            rollsInches.add(0);
        }

        bindScrollbars();
    }
}
