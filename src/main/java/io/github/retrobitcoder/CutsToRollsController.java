package io.github.retrobitcoder;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class CutsToRollsController {

    @FXML
    private Button clearCutsButton;

    @FXML
    private Button clearRollsButton;

    @FXML
    private Button runButton;

    @FXML
    private ListView<Measurement> cutsList;

    @FXML
    private ListView<Measurement> rollsList;

    @FXML
    private TextArea resultsTextArea;

    private ObservableList<Measurement> cutsObservableList = FXCollections.observableArrayList();

    private ObservableList<Measurement> rollsObservableList = FXCollections.observableArrayList();

    private static final int DEFAULT_LIST_SIZE = 100;

    private static final String DECIMAL_STRING = "\\.";

    private Solver solver = new Solver();

    // TODO: make a code snippet for this synching scroll bar scroll
    // private final String SCROLL_BAR_PROPERTY = ".scroll-bar";

    // private void bindCutsScrollbars() {
    // Node cutsInchesNode = cutsInchesList.lookup(SCROLL_BAR_PROPERTY);
    // Node cutsFeetNode = cutsFeetList.lookup(SCROLL_BAR_PROPERTY);

    // if (cutsInchesNode instanceof ScrollBar && cutsFeetNode instanceof ScrollBar)
    // {
    // final ScrollBar cutsFeetScrollbar = (ScrollBar) cutsFeetNode;
    // final ScrollBar cutsInchScrollBar = (ScrollBar) cutsInchesNode;

    // cutsFeetScrollbar.valueProperty().bindBidirectional(cutsInchScrollBar.valueProperty());
    // }
    // }

    // private void bindRollsScrollbars() {
    // Node rollsInchesNode = rollsInchesList.lookup(SCROLL_BAR_PROPERTY);
    // Node rollsFeetNode = rollsFeetList.lookup(SCROLL_BAR_PROPERTY);

    // if (rollsInchesNode instanceof ScrollBar && rollsFeetNode instanceof
    // ScrollBar) {
    // final ScrollBar rollsFeetScrollbar = (ScrollBar) rollsFeetNode;
    // final ScrollBar rollsInchScrollBar = (ScrollBar) rollsInchesNode;

    // rollsFeetScrollbar.valueProperty().bindBidirectional(rollsInchScrollBar.valueProperty());
    // }
    // }

    // private void bindScrollbars() {
    // cutsInchesList.setOnScroll(event -> bindCutsScrollbars());
    // cutsFeetList.setOnScroll(event -> bindCutsScrollbars());

    // // rollsInchesList.setOnScroll(event -> bindRollsScrollbars());
    // // rollsFeetList.setOnScroll(event -> bindRollsScrollbars());
    // }

    private void clearCuts() {
        cutsList.getItems().forEach(x -> {
            x.setFeet(0);
            x.setInches(0);
        });

        cutsList.refresh();
    }

    private void clearRolls() {
        rollsList.getItems().forEach(x -> {
            x.setFeet(0);
            x.setInches(0);
        });

        rollsList.refresh();
    }

    private void run() {
        resultsTextArea.setText("Running . . . ");

        ArrayList<Measurement> cuts = new ArrayList<>(cutsObservableList);
        ArrayList<Measurement> rolls = new ArrayList<>(rollsObservableList);

        cuts.removeIf(x -> x.getTotalInches() == 0);
        rolls.removeIf(x -> x.getTotalInches() == 0);

        solver.setCuts(cuts);
        solver.setRolls(rolls);

        solver.solve();

        resultsTextArea.setText(solver.getSolutions());
    }

    private void initButtons() {
        clearCutsButton.setOnAction(event -> clearCuts());

        clearRollsButton.setOnAction(event -> clearRolls());

        runButton.setOnAction(event -> run());
    }

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
                    String[] measurement = string.split(DECIMAL_STRING);

                    if (measurement.length < 2) {
                        if (measurement.length < 1) {
                            return null;
                        }

                        measurement = new String[] { measurement[0], "0" };
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
        // // TODO: make note that setOnKeyTyped has getCode as UNDEFINED
        // cutsList.setOnKeyReleased(new EventHandler<KeyEvent>() {
        // @Override
        // public void handle(KeyEvent event) {
        // if (event.getCode() == KeyCode.ENTER) {
        // int index = cutsList.getSelectionModel().getSelectedIndex();

        // cutsList.getSelectionModel().

        // cutsList.getSelectionModel().select(index + 1);

        // //cutsList.requestFocus();
        // }
        // }
        // });

        cutsList.setOnEditCommit(new EventHandler<ListView.EditEvent<Measurement>>() {
            @Override
            public void handle(EditEvent<Measurement> event) {
                cutsList.getItems().set(event.getIndex(), event.getNewValue());

                int nextIndex = event.getIndex() + 1 >= cutsList.getItems().size() ? event.getIndex()
                        : event.getIndex() + 1;

                cutsList.getSelectionModel().select(nextIndex);

                cutsList.requestFocus();
            }
        });

        cutsList.setItems(cutsObservableList);

        cutsList.setEditable(true);
        cutsList.setCellFactory(TextFieldListCell.forListView(converter));

        rollsList.setOnEditCommit(new EventHandler<ListView.EditEvent<Measurement>>() {
            @Override
            public void handle(EditEvent<Measurement> event) {
                rollsList.getItems().set(event.getIndex(), event.getNewValue());

                int nextIndex = event.getIndex() + 1 >= rollsList.getItems().size() ? event.getIndex()
                        : event.getIndex() + 1;

                rollsList.getSelectionModel().select(nextIndex);

                rollsList.requestFocus();
            }
        });

        rollsList.setItems(rollsObservableList);

        rollsList.setEditable(true);
        rollsList.setCellFactory(TextFieldListCell.forListView(converter));

        for (int i = 0; i < DEFAULT_LIST_SIZE; i++) {
            cutsObservableList.add(new Measurement(0, 0));
            rollsObservableList.add(new Measurement(0, 0));
        }

        initButtons();
    }
}
