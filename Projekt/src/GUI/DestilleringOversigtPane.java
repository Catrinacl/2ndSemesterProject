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

public class DestilleringOversigtPane extends GridPane implements Observer{
    private TableView<Destillering> tableView;
    private TextField searchBar = new TextField();

    public DestilleringOversigtPane() {
        Controller.addObserver(this);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        //række 0
        this.add(new Label("Søg efter Destillat Id:"), 0, 0);
        this.add(searchBar, 1, 0);

        //række 1
        this.initContent();
        this.add(tableView, 0, 1, 2, 1);

        this.updateDestilleringOversigt(null);
        searchBar.textProperty().addListener((obs, oldText, newText)
                -> updateDestilleringOversigt(newText));

        tableView.getSelectionModel().selectedItemProperty().addListener((obs,
                                                                          oldValue, selected)
                ->{
            if (selected != null) {
                new DestilleringDetaljeWindow(selected).show();
            }
        });
    }

    private void initContent() {
        tableView = new TableView<>();

        // kolonne 1
        TableColumn<Destillering, String> colId = new TableColumn<>("Id");
        colId.setCellValueFactory(new PropertyValueFactory<>("distilleringsId"));
        colId.setPrefWidth(120);

        // kolonne 2
        TableColumn<Destillering, String> colStart = new TableColumn<>("Startdato");
        colStart.setCellValueFactory(new PropertyValueFactory<>("startDato"));
        colStart.setPrefWidth(120);

        //kolonne 3
        TableColumn<Destillering, String> colSlut = new TableColumn<>("Slutdato");
        colSlut.setCellValueFactory(new PropertyValueFactory<>("slutDato"));
        colSlut.setPrefWidth(120);

        //kolonne 4
        TableColumn<Destillering, String> colKorn = new TableColumn<>("Kornsort");
        colKorn.setCellValueFactory(new PropertyValueFactory<>("kornsort"));
        colKorn.setPrefWidth(120);

        //kolonne 5
        TableColumn<Destillering, String> colBatch = new TableColumn<>("Maltbatch");
        colBatch.setCellValueFactory(new PropertyValueFactory<>("maltBatch"));
        colBatch.setPrefWidth(120);

        tableView.getColumns().addAll(colId, colStart, colSlut, colKorn, colBatch);
        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
    }

    private void updateDestilleringOversigt(String searchText) {
        List<Destillering> alle = Controller.getDestilleringer();

    }

    @Override
    public void update() {
        updateDestilleringOversigt(null);
    }

}
