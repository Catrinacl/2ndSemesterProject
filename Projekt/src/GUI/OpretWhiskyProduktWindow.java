package GUI;

import Controller.Controller;
import Model.WhiskyProdukt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpretWhiskyProduktWindow extends Stage {

    private WhiskyProdukt whiskyProdukt;

    private TextField txfProduktNr;
    private TextField txfNavn;
    private TextField txfBeskrivelse;
    private TextField txfSlutAlkohol;
    private TextField txfAntalFlasker;
    private CheckBox cbSingleCask;

    private final Button btnOpret = new Button("Opret Whiskyprodukt");

    public OpretWhiskyProduktWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Whiskyprodukt");

        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Nyt Whiskyprodukt");
        lblHeader.setFont(new Font(18));
        pane.add(lblHeader, 0, 0, 2, 1);

        txfProduktNr = new TextField();
        txfNavn = new TextField();
        txfBeskrivelse = new TextField();
        txfSlutAlkohol = new TextField();
        txfAntalFlasker = new TextField();
        cbSingleCask = new CheckBox("Single Cask");

        pane.add(new Label("Produkt nr:"), 0, 1);
        pane.add(txfProduktNr, 1, 1);

        pane.add(new Label("Navn:"), 0, 2);
        pane.add(txfNavn, 1, 2);

        pane.add(new Label("Beskrivelse:"), 0, 3);
        pane.add(txfBeskrivelse, 1, 3);

        pane.add(new Label("Slut alkohol %:"), 0, 4);
        pane.add(txfSlutAlkohol, 1, 4);

        pane.add(new Label("Antal flasker:"), 0, 5);
        pane.add(txfAntalFlasker, 1, 5);

        pane.add(cbSingleCask, 1, 6);

        pane.add(btnOpret, 0, 8);
        btnOpret.setOnAction(event -> opretWhiskyProduktAction());
    }

    private void opretWhiskyProduktAction() {
        String produktNr = txfProduktNr.getText().trim();
        String navn = txfNavn.getText().trim();
        String beskrivelse = txfBeskrivelse.getText().trim();
        String slutAlkText = txfSlutAlkohol.getText().trim();
        String antalFlaskerText = txfAntalFlasker.getText().trim();

        if (produktNr.isEmpty() || navn.isEmpty() || slutAlkText.isEmpty() || antalFlaskerText.isEmpty()) {
            showAlert("Fejl", "Udfyld mindst Produkt nr, Navn, Slut alkohol % og Antal flasker.");
            return;
        }

        double slutAlkohol;
        int antalFlasker;
        try {
            slutAlkohol = Double.parseDouble(slutAlkText);
            antalFlasker = Integer.parseInt(antalFlaskerText);
        } catch (NumberFormatException e) {
            showAlert("Fejl", "Slut alkohol % skal være et tal og antal flasker et helt tal.");
            return;
        }

        boolean erSingleCask = cbSingleCask.isSelected();

        // ingen VandTilsaetning endnu = null
        whiskyProdukt = Controller.createWhiskyProdukt(
                produktNr,
                navn,
                beskrivelse,
                slutAlkohol,
                erSingleCask,
                antalFlasker,
                null
        );

        this.close();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }

    public WhiskyProdukt getWhiskyProdukt() {
        return whiskyProdukt;
    }
}
