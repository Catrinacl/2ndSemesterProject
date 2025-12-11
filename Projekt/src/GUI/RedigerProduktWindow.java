package GUI;

import Model.WhiskyProdukt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RedigerProduktWindow extends Stage {
    private final WhiskyProdukt whiskyProdukt;

    private final Button btnGem = new Button("Gem");
    private final Button btnAnnuller = new Button("Annuller");

    private TextField txfNavn = new TextField();
    private TextField txfBeskrivelse = new TextField();
    private TextField txfslutAlkoholPct = new TextField();
    private CheckBox cbSingleCask = new CheckBox("Single Cask");
    private TextField txfAntalFlasker = new TextField();

    public RedigerProduktWindow(WhiskyProdukt whiskyProdukt) {
        this.whiskyProdukt = whiskyProdukt;
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Rediger produkt: " + whiskyProdukt.getProduktNr());

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Rediger Produkt: " + whiskyProdukt.getProduktNr());
        pane.add(lblHeader, 0, 0, 2, 1);
        lblHeader.setFont(new Font(20));

        pane.add(new Label("Navn:"), 0, 1);
        txfNavn.setText(String.valueOf(whiskyProdukt.getNavn()));
        pane.add(txfNavn, 1, 1);

        pane.add(new Label("Beskrivelse:"), 0, 2);
        txfBeskrivelse.setText(String.valueOf(whiskyProdukt.getBeskrivelse()));
        pane.add(txfBeskrivelse, 1, 2);

        pane.add(new Label("Slut alkohol %:"), 0, 3);
        txfslutAlkoholPct.setText(String.valueOf(whiskyProdukt.getSlutAlkoholProcent()));
        pane.add(txfslutAlkoholPct, 1, 3);

        pane.add(new Label("Single Cask:"), 0, 4);
        cbSingleCask.setSelected(whiskyProdukt.isErSingleCask());
        pane.add(cbSingleCask, 1, 4);

        pane.add(new Label("Antal flasker:"), 0, 5);
        txfAntalFlasker.setText(String.valueOf(whiskyProdukt.getAntalFlasker()));
        pane.add(txfAntalFlasker, 1, 5);

        pane.add(btnGem, 0, 6);
        pane.add(btnAnnuller, 1, 6);

        btnGem.setOnAction(event -> this.gemAction());
        btnAnnuller.setOnAction(event -> this.close());

    }

    private void gemAction() {
        String navn = txfNavn.getText().trim();
        String beskrivelse = txfBeskrivelse.getText().trim();
        String alkoPct = txfslutAlkoholPct.getText().trim();
        String antalFlasker = txfAntalFlasker.getText().trim();
        boolean singleCask = cbSingleCask.isSelected();

        if (navn.isEmpty() || alkoPct.isEmpty() || antalFlasker.isEmpty()) {
            showAlert("Udfyld Navn, Slut alkohol % og Antal flasker.");
            return;
        }

        try {
            double alkohol = Double.parseDouble(alkoPct);
            int antal = Integer.parseInt(antalFlasker);

            whiskyProdukt.setNavn(navn);
            whiskyProdukt.setBeskrivelse(beskrivelse);
            whiskyProdukt.setSlutAlkoholProcent(alkohol);
            whiskyProdukt.setErSingleCask(singleCask);
            whiskyProdukt.setAntalFlasker(antal);

            this.close();

        } catch (NumberFormatException e) {
            showAlert("Slut alkohol % skal være et tal og antal flasker skal være et helt tal");
        }
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }


}
