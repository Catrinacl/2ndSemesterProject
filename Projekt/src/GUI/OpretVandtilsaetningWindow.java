package GUI;

import Controller.Controller;
import Model.VandTilsaetning;
import Model.WhiskyProdukt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpretVandtilsaetningWindow extends Stage {

    private TextField txfVandtilsaetningID = new TextField();
    private TextField txfVandMaengdeL = new TextField();
    private TextField txfVandkilde = new TextField();
    private ListView<WhiskyProdukt> lvwProdukter = new ListView<>();
    private Button btnTilføj = new Button("Tilføj Vandtilsætning");

    public OpretVandtilsaetningWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Tilføj Vandtilsætning");

        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Tilføj Vandtilsætning");
        lblHeader.setFont(new Font(18));
        pane.add(lblHeader, 0, 0, 2, 1);

        pane.add(new Label("VandtilsætningsID:"), 0, 1);
        pane.add(txfVandtilsaetningID, 1, 1);

        pane.add(new Label("Mængde i Liter:"), 0, 2);
        pane.add(txfVandMaengdeL, 1, 2);

        pane.add(new Label("Vandkilde:"), 0, 3);
        pane.add(txfVandkilde, 1, 3);

        pane.add(new Label("Vælg whiskyprodukt:"), 0, 4);
        lvwProdukter.setPrefHeight(100);
        lvwProdukter.setPrefWidth(250);
        lvwProdukter.getItems().setAll(Controller.getWhiskyProdukter());
        pane.add(lvwProdukter, 0, 5, 1, 1);

        pane.add(btnTilføj, 0, 6);
        btnTilføj.setOnAction(event -> opretVandtilsaetningAction());
    }

    private void opretVandtilsaetningAction() {
        WhiskyProdukt valgtProdukt = lvwProdukter.getSelectionModel().getSelectedItem();

        String vandTilsætningID = txfVandtilsaetningID.getText().trim();
        String vandMaengde = txfVandMaengdeL.getText().trim();
        String vandKilde = txfVandkilde.getText().trim();

        if (valgtProdukt == null) {
            showAlert("Fejl", "Vælg et whiskyprodukt");
            return;
        }

        if (vandTilsætningID.isEmpty() || vandMaengde.isEmpty() || vandKilde.isEmpty()) {
            showAlert("Fejl", "Udfyld alle felter");
            return;
        }

        double vandMaengdeL;
        try {
            vandMaengdeL = Double.parseDouble(vandMaengde);
        } catch (NumberFormatException e) {
            showAlert("Fejl", "Vandmængde skal være tal.");
            return;
        }

        VandTilsaetning vandTilsaetning = Controller.createVandTilsaetning(
                vandTilsætningID,
                vandMaengdeL,
                vandKilde,
                valgtProdukt
        );

        valgtProdukt.setVandTilsaetning(vandTilsaetning);

        Controller.notifyObservers();

        this.close();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

}
