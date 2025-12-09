package GUI;

import Controller.Controller;
import Model.Fad;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.stream.Collectors;
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

        this.add(btnRedigerFad, 0, 2);
        btnRedigerFad.setOnAction(event -> this.redigerFadAction());


        this.updateFadOversigt(null);
        txfsearchBar.textProperty().addListener((obs, oldText, newText) -> updateFadOversigt(newText));

        this.add(btnVisDetaljer, 2, 2);
        btnVisDetaljer.setOnAction(event -> {
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
        });



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

        tableView.getColumns().add(columnFadId);
        tableView.getColumns().add(columnDestillatIndhold);
        tableView.getColumns().add(columnStørrelse);

        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);

        this.add(btnSletFad, 1, 2);

        btnSletFad.setOnAction(event -> {
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

        });
        
    }

    public void updateFadOversigt(String searchText) {
        List<Fad> alleFade = Controller.getFade();

        final String filterText = (searchText != null ? searchText : txfsearchBar.getText()).toLowerCase();

        List<Fad> filteredList = alleFade.stream()
                .filter(fad -> filterText.isEmpty() ||
                        fad.getFadId().toLowerCase().contains(filterText) ||
                        fad.getDestillatID() != null && fad.getDestillatID().toLowerCase().contains(filterText) ||
                        String.valueOf(fad.getStoerrelseL()).contains(filterText))
                .collect(Collectors.toList());

        // Opdater TableView
        tableView.setItems(observableArrayList(filteredList));
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


    @Override
    public void update() {
        if (tableView != null) {
            updateFadOversigt(null);
        }
    }
}
