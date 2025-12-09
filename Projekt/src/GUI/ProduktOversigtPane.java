package GUI;

import Controller.Controller;
import Model.WhiskyProdukt;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableArrayList;

public class ProduktOversigtPane extends GridPane implements Observer {

    private TableView<WhiskyProdukt> tableView;
    private final TextField txfsearchBar = new TextField();

    private final Button btnVisHistorik = new Button("Vis historik");
    private final Button btnSletValgteProdukt = new Button("Slet Produkt");
    private final Button btnRedigerProdukt = new Button("Rediger Produkt");


    public ProduktOversigtPane() {
        Controller.addObserver(this);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        this.add(new Label("Søg efter produkt:"), 0, 0);
        this.add(txfsearchBar, 1, 0);

        this.initContent();
        this.add(tableView, 0, 1, 2, 1);

        this.add(btnRedigerProdukt, 0, 2);
        btnRedigerProdukt.setOnAction(event -> this.redigerProduktAction());

        this.updateProduktOversigt(null);
        txfsearchBar.textProperty().addListener((obs, oldText, newText) -> updateProduktOversigt(newText));

        this.add(btnVisHistorik, 2, 2);
        btnVisHistorik.setOnAction(event -> {
            WhiskyProdukt selectedProdukt = tableView.getSelectionModel().getSelectedItem();

            if (selectedProdukt == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Intet produkt valgt");
                alert.setHeaderText(null);
                alert.setContentText("Vælg et produkt i oversigten for at se historikken");
                alert.showAndWait();
                return;
            }

            new ProduktHistorikWindow(selectedProdukt).show();
        });
    }

    private void initContent() {
        tableView = new TableView<>();

        TableColumn<WhiskyProdukt, String> columnProduktId = new TableColumn<>("Produkt ID");
        columnProduktId.setCellValueFactory(new PropertyValueFactory<>("produktNr"));
        columnProduktId.setPrefWidth(100);

        TableColumn<WhiskyProdukt, String> columnNavn = new TableColumn<>("Navn");
        columnNavn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        columnNavn.setPrefWidth(150);

        TableColumn<WhiskyProdukt, String> columnBeskrivelse = new TableColumn<>("Beskrivelse");
        columnBeskrivelse.setCellValueFactory(new PropertyValueFactory<>("beskrivelse"));
        columnBeskrivelse.setPrefWidth(150);

        TableColumn<WhiskyProdukt, String> columnErSingleCask = new TableColumn<>("Single Cask");
        columnErSingleCask.setCellValueFactory(new PropertyValueFactory<>("erSingleCask"));
        columnErSingleCask.setPrefWidth(150);

        TableColumn<WhiskyProdukt, String> columnslutAlkoholProcent = new TableColumn<>("Slut alkohol %");
        columnslutAlkoholProcent.setCellValueFactory(new PropertyValueFactory<>("slutAlkoholProcent"));
        columnslutAlkoholProcent.setPrefWidth(150);

        TableColumn<WhiskyProdukt, String> columnAntalFlasker = new TableColumn<>("Antal Flasker");
        columnAntalFlasker.setCellValueFactory(new PropertyValueFactory<>("antalFlasker"));
        columnAntalFlasker.setPrefWidth(100);

        tableView.getColumns().add(columnProduktId);
        tableView.getColumns().add(columnNavn);
        tableView.getColumns().add(columnBeskrivelse);
        tableView.getColumns().add(columnErSingleCask);
        tableView.getColumns().add(columnslutAlkoholProcent);
        tableView.getColumns().add(columnAntalFlasker);

        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);

        this.add(btnSletValgteProdukt, 1, 2);
        btnSletValgteProdukt.setOnAction(event -> {
            WhiskyProdukt selectedProdukt = tableView.getSelectionModel().getSelectedItem();
            if (selectedProdukt != null) {
                Controller.deleteProdukt(selectedProdukt);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Intet produkt valgt");
                alert.setHeaderText(null);
                alert.setContentText("Vælg et produkt i oversigten for at slette");
                alert.showAndWait();
            }

        });


    }

    public void updateProduktOversigt(String searchText) {
        List<WhiskyProdukt> alleProdukter = Controller.getWhiskyProdukter();

        final String filterText = (searchText != null ? searchText : txfsearchBar.getText()).toLowerCase();


        List<WhiskyProdukt> filteredList = alleProdukter.stream()
                .filter(whiskyProdukt ->
                        filterText.isEmpty() ||
                                whiskyProdukt.getProduktNr().toLowerCase().contains(filterText) ||
                                whiskyProdukt.getNavn().toLowerCase().contains(filterText) ||
                                String.valueOf(whiskyProdukt.getSlutAlkoholProcent()).contains(filterText))
                .collect(Collectors.toList());

        tableView.setItems(observableArrayList(filteredList));

    }

    private void redigerProduktAction() {
        WhiskyProdukt selectedProdukt = tableView.getSelectionModel().getSelectedItem();
        if (selectedProdukt == null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Intet produkt valgt");
            alert.setHeaderText(null);
            alert.setContentText("Vælg et produkt i oversigten, du vil redigere.");
            alert.showAndWait();
            return;
        }
        RedigerProduktWindow redigerProduktWindow = new RedigerProduktWindow(selectedProdukt);
        redigerProduktWindow.showAndWait();

        updateProduktOversigt(null);
    }

    @Override
    public void update() {
        if (tableView != null) {
            updateProduktOversigt(null);
        }
    }
}
