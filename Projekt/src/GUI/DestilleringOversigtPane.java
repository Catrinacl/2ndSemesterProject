package GUI;

import Controller.Controller;
import Model.Destillering;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

import java.util.List;

public class DestilleringOversigtPane extends GridPane implements Observer {
    private TableView<Destillering> tableView;
    private TextField searchBar = new TextField();


    public DestilleringOversigtPane() {
        Controller.addObserver(this);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        // række 0
        this.add(new Label("Søg efter Destillering Id:"), 0, 0);
        this.add(searchBar, 1, 0);

        // række 1
        this.initContent();
        this.add(tableView, 0, 1, 2, 1);

        this.updateDestilleringOversigt(null);

        searchBar.textProperty().addListener((obs, oldText, newText) ->
                updateDestilleringOversigt(newText));

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, selected) -> {
            if (selected != null) {
                new DestilleringDetaljeWindow(selected).show();
            }
        });
    }

    private void initContent() {
        tableView = new TableView<>();

        // kolonne 1
        TableColumn<Destillering, String> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("DestilleringId"));
        colId.setPrefWidth(120);

        // kolonne 2
        TableColumn<Destillering, java.time.LocalDate> colStart = new TableColumn<>("Startdato");
        colStart.setCellValueFactory(new PropertyValueFactory<>("startDato"));
        colStart.setPrefWidth(120);

        // kolonne 3
        TableColumn<Destillering, java.time.LocalDate> colSlut = new TableColumn<>("Slutdato");
        colSlut.setCellValueFactory(new PropertyValueFactory<>("slutDato"));
        colSlut.setPrefWidth(120);

        // kolonne 4
        TableColumn<Destillering, String> colKorn = new TableColumn<>("Kornsort");
        colKorn.setCellValueFactory(new PropertyValueFactory<>("kornsort"));
        colKorn.setPrefWidth(120);

        // kolonne 5
        TableColumn<Destillering, String> colBatch = new TableColumn<>("Maltbatch");
        colBatch.setCellValueFactory(new PropertyValueFactory<>("maltBatch"));
        colBatch.setPrefWidth(120);

        tableView.getColumns().addAll(colId, colStart, colSlut, colKorn, colBatch);
        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
    }

    private void updateDestilleringOversigt(String searchText) {
        List<Destillering> alle = Controller.getDestilleringer();
        // TODO: filtrer og sæt tableView items her
    }

    @Override
    public void update() {
        updateDestilleringOversigt(null);
    }
}

