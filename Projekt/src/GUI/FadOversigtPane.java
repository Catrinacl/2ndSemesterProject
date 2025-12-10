package GUI;

import Controller.Controller;
import Model.Fad;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;
import static javafx.collections.FXCollections.observableArrayList;


public class FadOversigtPane extends GridPane implements Observer {

    private TableView<Fad> tableView;
    private final TextField txfsearchBar = new TextField();

    private final Button btnSletFad = new Button("Slet Valgt Fad");
    private final Button btnRedigerFad = new Button("Rediger Fad");
    private final Button btnVisDetaljer = new Button("Vis detaljer");


    public FadOversigtPane() {
        Controller.addObserver(this);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        this.add(new Label("Søg efter fad:"), 0, 0);
        this.add(txfsearchBar, 1, 0);

        this.initContent();
        this.add(tableView, 0, 1, 2, 1);

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(btnRedigerFad, btnSletFad, btnVisDetaljer);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER_LEFT);

        this.add(buttonBox, 0, 2, 2, 1);

        btnSletFad.setOnAction(event -> sletFadAction());
        btnRedigerFad.setOnAction(event -> redigerFadAction());
        btnVisDetaljer.setOnAction(event -> visDetaljerAction());

        txfsearchBar.textProperty().addListener((obs, oldText, newText) -> updateFadOversigt(newText));

        this.updateFadOversigt(null);

    }

    private void initContent() {
        tableView = new TableView<>();

        //Kolonne 1
        TableColumn<Fad, String> columnFadId = new TableColumn<>("Fad ID");
        columnFadId.setCellValueFactory(new PropertyValueFactory<>("fadId"));
        columnFadId.setPrefWidth(100);

        //Kolonne 2
        TableColumn<Fad, String> columnDestillatIndhold = new TableColumn<>("Indhold (Destillat ID)");
        columnDestillatIndhold.setCellValueFactory(new PropertyValueFactory<>("destillatID"));
        columnDestillatIndhold.setPrefWidth(150);

        //Kolonne 3
        TableColumn<Fad, Integer> columnStørrelse = new TableColumn<>("Størrelse (Liter)");
        columnStørrelse.setCellValueFactory(new PropertyValueFactory<>("stoerrelseL"));
        columnStørrelse.setPrefWidth(100);

        TableColumn<Fad, Boolean> columnModen = new TableColumn<>("Klar til aftapning");
        columnModen.setCellValueFactory(new PropertyValueFactory<>("klarTilAftapningTekst"));
        columnModen.setPrefWidth(100);

        tableView.getColumns().add(columnFadId);
        tableView.getColumns().add(columnDestillatIndhold);
        tableView.getColumns().add(columnStørrelse);
        tableView.getColumns().add(columnModen);

        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
        
    }

    public void updateFadOversigt(String searchText) {
        List<Fad> alleFade = Controller.getFade();

        String filterText;
        if (searchText != null) {
            filterText = searchText.toLowerCase();
        } else {
            filterText = txfsearchBar.getText().toLowerCase();
        }

        List<Fad> filteredList = new ArrayList<>();

        if (filterText.isEmpty()) {
            filteredList.addAll(alleFade);
        } else {
            for (Fad fad : alleFade) {

                String fadId = fad.getFadId().toLowerCase();
                String destillatId = (fad.getDestillatID() != null ? fad.getDestillatID().toLowerCase() : "");
                String stoerrelse = String.valueOf(fad.getStoerrelseL());

                if (fadId.contains(filterText) ||
                        destillatId.contains(filterText) ||
                        stoerrelse.contains(filterText)) {

                    filteredList.add(fad);
                }
            }
        }
        tableView.setItems(observableArrayList(filteredList));
    }

    private void sletFadAction() {
        Fad selectedFad = tableView.getSelectionModel().getSelectedItem();
        if (selectedFad != null) {
            Controller.deleteFad(selectedFad);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Intet fad valgt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad i oversigten for at slette");
            alert.showAndWait();
        }
    }

    private void redigerFadAction() {
        Fad selectedFad = tableView.getSelectionModel().getSelectedItem();

        if (selectedFad == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Intet fad valgt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad i oversigten, du vil redigere.");
            alert.showAndWait();
            return;
        }
        RedigerFadWindow redigerFadWindow = new RedigerFadWindow(selectedFad);
        redigerFadWindow.showAndWait();

        updateFadOversigt(null);
    }

    private void visDetaljerAction() {
        Fad selectedFad = tableView.getSelectionModel().getSelectedItem();
        if (selectedFad == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Intet fad valgt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et fad i oversigten for at se detaljerne");
            alert.showAndWait();
            return;
        }
        new FadDetaljeWindow(selectedFad).show();
    }


    @Override
    public void update() {
        if (tableView != null) {
            updateFadOversigt(null);
            tableView.refresh();
        }
    }
}
