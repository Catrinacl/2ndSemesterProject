package GUI;

import Controller.Controller;
import Model.Destillat;
import Model.Fad;
import Model.LagerMedarbejder;
import Model.Paafyldning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OpretPaafyldningWindow extends Stage {

    private TextField txfPaafyldningsId = new TextField();
    private TextField txfMaengdeL = new TextField();
    private TextField txfalkoholPcVedPaafyldning = new TextField();

    private ListView<Destillat> lvwDestillat = new ListView<>();
    private ListView<Fad> lvwFad = new ListView<>();

    private final DatePicker dpDato = new DatePicker(LocalDate.now());
    private final ComboBox<LagerMedarbejder> cbbMedarbejder = new ComboBox<>();
    private Button btnOpret = new Button("Opret Påfyldning");





    public OpretPaafyldningWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Påfyldning");

        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // Overskrift
        Label lblHeader = new Label("Opret Ny Påfyldning");
        lblHeader.setFont(new Font(20));
        pane.add(lblHeader, 0, 0, 2, 1);

        pane.add(new Label("PåfyldningsID:"), 0, 1);
        pane.add(txfPaafyldningsId, 1, 1);

        pane.add(new Label("Vælg destillat:"), 0, 2);
        pane.add(lvwDestillat, 0, 4);
        lvwDestillat.setPrefHeight(80);
        lvwDestillat.setPrefWidth(75);
        lvwDestillat.getItems().setAll(Controller.getDestillater());
        lvwDestillat.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        pane.add(new Label("Mængde i Liter:"), 0, 5);
        pane.add(txfMaengdeL, 1, 5);

        pane.add(new Label("Alkohol % Ved Påfyldning:"), 0, 6);
        pane.add(txfalkoholPcVedPaafyldning, 1, 6);

        pane.add(new Label("Dato:"), 0, 7);
        pane.add(dpDato, 0, 7);

        pane.add(new Label("Udført af:"), 0, 8);
        cbbMedarbejder.getItems().setAll(Controller.getLagerMedarbejdere());
        pane.add(cbbMedarbejder, 0, 9);

        pane.add(new Label("Vælg Fad:"), 0, 10);
        pane.add(lvwFad, 0, 11);
        lvwFad.setPrefHeight(80);
        lvwFad.setPrefWidth(75);
        lvwFad.getItems().setAll(Controller.getFade());
        lvwFad.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        pane.add(btnOpret, 0, 18);
        btnOpret.setOnAction(event -> opretPaafyldningAction());

    }

    private void opretPaafyldningAction() {

        String paafyldningsId = txfPaafyldningsId.getText().trim();
        String maengdeLText = txfMaengdeL.getText().trim();
        String alkoholPcVedPaafyldning = txfalkoholPcVedPaafyldning.getText().trim();

        if (paafyldningsId.isEmpty() || maengdeLText.isEmpty() || alkoholPcVedPaafyldning.isEmpty()) {
            showAlert("Fejl", "Udfyld påfyldningsID, Mængde og Alkohol %");
            return;
        }

        double maengdeL;
        double alkoholPct;
        try {
            maengdeL = Double.parseDouble(maengdeLText);
            alkoholPct = Double.parseDouble(alkoholPcVedPaafyldning);
            if (maengdeL <= 0 || alkoholPct < 0 || alkoholPct > 100) {
                showAlert("Fejl", "Mængde skal være >0 og Alkohol % mellem 0 og 100");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Fejl", "Mængde(L) og Alkohol % skal være tal");
            return;
        }

        Fad selectedFad = lvwFad.getSelectionModel().getSelectedItem();
        Destillat selectedDestillat = lvwDestillat.getSelectionModel().getSelectedItem();
        LagerMedarbejder selectedMedarbejder = cbbMedarbejder.getSelectionModel().getSelectedItem();
        LocalDate dato = dpDato.getValue();

        if (selectedFad == null || selectedDestillat == null || selectedMedarbejder == null) {
            showAlert("Fejl", "Vælg Fad, Destillat og Medarbejder");
            return;
        }

        Paafyldning paafyldning = Controller.createPaafyldning(
                paafyldningsId,
                maengdeL,
                alkoholPct,
                dato,
                selectedMedarbejder,
                selectedFad,
                selectedDestillat
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
}
