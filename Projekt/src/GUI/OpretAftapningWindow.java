package GUI;

import Controller.Controller;
import Model.Aftapning;
import Model.Destillat;
import Model.WhiskyProdukt;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OpretAftapningWindow extends Stage {

    private Aftapning aftapning;

    private TextField txfAftapningsNr;
    private TextField txfAlkoholProcent;
    private TextField txfVolumenL;
    private DatePicker dpDato;

    private final ListView<Destillat> lvwDestillater = new ListView<>();
    private final ListView<WhiskyProdukt> lvwProdukter = new ListView<>();

    private final Button btnOpret = new Button("Opret Aftapning");

    public OpretAftapningWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Aftapning");

        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Ny Aftapning");
        lblHeader.setFont(new Font(18));
        pane.add(lblHeader, 0, 0, 2, 1);

        txfAftapningsNr = new TextField();
        txfAlkoholProcent = new TextField();
        txfVolumenL = new TextField();
        dpDato = new DatePicker(LocalDate.now());

        // vælg destillat
        pane.add(new Label("Vælg destillat:"), 0, 1);
        lvwDestillater.setPrefHeight(100);
        lvwDestillater.setPrefWidth(250);
        lvwDestillater.getItems().setAll(Controller.getDestillater());
        pane.add(lvwDestillater, 0, 2, 2, 1);

        // vælg whiskyprodukt
        pane.add(new Label("Vælg whiskyprodukt:"), 0, 3);
        lvwProdukter.setPrefHeight(100);
        lvwProdukter.setPrefWidth(250);
        lvwProdukter.getItems().setAll(Controller.getWhiskyProdukter());
        pane.add(lvwProdukter, 0, 4, 2, 1);

        // felter til aftapning
        pane.add(new Label("Aftapnings nr:"), 0, 5);
        pane.add(txfAftapningsNr, 1, 5);

        pane.add(new Label("Alkohol %:"), 0, 6);
        pane.add(txfAlkoholProcent, 1, 6);

        pane.add(new Label("Volumen (L):"), 0, 7);
        pane.add(txfVolumenL, 1, 7);

        pane.add(new Label("Dato:"), 0, 8);
        pane.add(dpDato, 1, 8);

        // opret-knap
        pane.add(btnOpret, 0, 10);
        btnOpret.setOnAction(event -> opretAftapningAction());
    }

    private void opretAftapningAction() {
        Destillat valgtDestillat = lvwDestillater.getSelectionModel().getSelectedItem();
        WhiskyProdukt valgtProdukt = lvwProdukter.getSelectionModel().getSelectedItem();

        String aftapningsNr = txfAftapningsNr.getText().trim();
        String alkoholText = txfAlkoholProcent.getText().trim();
        String volumenText = txfVolumenL.getText().trim();
        LocalDate dato = dpDato.getValue();

        if (valgtDestillat == null || valgtProdukt == null) {
            showAlert("Fejl", "Vælg både destillat og whiskyprodukt.");
            return;
        }

        if (aftapningsNr.isEmpty() || alkoholText.isEmpty() || volumenText.isEmpty() || dato == null) {
            showAlert("Fejl", "Udfyld alle felter for aftapningen.");
            return;
        }

        double alkoholProcent;
        double volumenL;
        try {
            alkoholProcent = Double.parseDouble(alkoholText);
            volumenL = Double.parseDouble(volumenText);
        } catch (NumberFormatException e) {
            showAlert("Fejl", "Alkohol % og volumen skal være tal.");
            return;
        }

        aftapning = Controller.createAftapning(
                aftapningsNr,
                alkoholProcent,
                dato,
                volumenL,
                valgtDestillat,
                valgtProdukt
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

    public Aftapning getAftapning() {
        return aftapning;
    }
}
