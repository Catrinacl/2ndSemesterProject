package GUI;

import Controller.Controller;
import Model.WhiskyProdukt;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import java.util.ArrayList;
import java.util.List;

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

        btnRedigerProdukt.setOnAction(event -> this.redigerProduktAction());

        this.updateProduktOversigt(null);
        txfsearchBar.textProperty().addListener((obs, oldText, newText) -> updateProduktOversigt(newText));

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

        HBox buttonBox = new HBox(15);
        buttonBox.getChildren().addAll(btnRedigerProdukt, btnSletValgteProdukt, btnVisHistorik);
        buttonBox.setPadding(new Insets(10, 0, 0, 0));
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        this.add(buttonBox, 0, 2, 2, 1);
    }

    private void initContent() {
        tableView = new TableView<>();

        //Kolonne 1
        TableColumn<WhiskyProdukt, String> columnProduktId = new TableColumn<>("Produkt ID");
        columnProduktId.setCellValueFactory(new PropertyValueFactory<>("produktNr"));
        columnProduktId.setPrefWidth(100);

        //Kolonne 2
        TableColumn<WhiskyProdukt, String> columnNavn = new TableColumn<>("Navn");
        columnNavn.setCellValueFactory(new PropertyValueFactory<>("navn"));
        columnNavn.setPrefWidth(150);

        //Kolonne 3
        TableColumn<WhiskyProdukt, String> columnBeskrivelse = new TableColumn<>("Beskrivelse");
        columnBeskrivelse.setCellValueFactory(new PropertyValueFactory<>("beskrivelse"));
        columnBeskrivelse.setPrefWidth(150);

        //Kolonne 4
        TableColumn<WhiskyProdukt, Boolean> columnErSingleCask = new TableColumn<>("Single Cask");
        columnErSingleCask.setCellValueFactory(new PropertyValueFactory<>("erSingleCask"));
        columnErSingleCask.setPrefWidth(150);

        columnErSingleCask.setCellFactory(col -> new TableCell<WhiskyProdukt, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item ? "Ja" : "Nej");
                }
            }
        });

        //Kolonne 5
        TableColumn<WhiskyProdukt, String> columnslutAlkoholProcent = new TableColumn<>("Slut alkohol %");
        columnslutAlkoholProcent.setCellValueFactory(new PropertyValueFactory<>("slutAlkoholProcent"));
        columnslutAlkoholProcent.setPrefWidth(150);

        //Kolonne 6
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
    }

    public void updateProduktOversigt(String searchText) {
        List<WhiskyProdukt> alleProdukter = Controller.getWhiskyProdukter();

        String filterText;
        if (searchText != null) {
            filterText = searchText.toLowerCase();
        } else {
            filterText = txfsearchBar.getText().toLowerCase();
        }

        List<WhiskyProdukt> filteredList = new ArrayList<>();

        if (filterText.isEmpty()) {
            filteredList.addAll(alleProdukter);
        } else {
            for (WhiskyProdukt produkt : alleProdukter) {

                String produktNr = produkt.getProduktNr().toLowerCase();
                String navn = produkt.getNavn().toLowerCase();
                String alkohol = String.valueOf(produkt.getSlutAlkoholProcent());

                if (produktNr.contains(filterText) ||
                        navn.contains(filterText) ||
                        alkohol.contains(filterText)) {

                    filteredList.add(produkt);
                }
            }
        }
        tableView.setItems(FXCollections.observableArrayList(filteredList));
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
