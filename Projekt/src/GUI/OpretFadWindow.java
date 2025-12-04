package GUI;

import Controller.Controller;
import Model.Fad;
import Model.Paafyldning;
import Model.LagerMedarbejder;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.ArrayList;

public class OpretFadWindow extends Stage {

    private Fad fad;

    // Fad-felter
    private final TextField txfFadNr = new TextField();
    private final TextField txfStoerrelseL = new TextField();
    private final TextField txfTraeType = new TextField();
    private final TextField txfTidligereIndhold = new TextField();
    private final TextField txfStatus = new TextField();

    // Påfyldning-input
    private final TextField txfPaafyldningstid = new TextField();
    private final TextField txfMaengdeL = new TextField();
    private final TextField txfAlkoholPcVedPaafyldning = new TextField();
    private final DatePicker dpDato = new DatePicker(LocalDate.now());
    private final ComboBox<LagerMedarbejder> cbbMedarbejder = new ComboBox<>();

    // Visning af valgte påfyldninger
    private final ListView<String> lvwPaafyldninger = new ListView<>();

    private final Button btnTilfoejPaafyldning = new Button("Tilføj påfyldning");
    private final Button btnOpret = new Button("Opret Fad");

    // Lokale påfyldninger som fadet skal oprettes med
    private final ArrayList<Paafyldning> paafyldningerTilFad = new ArrayList<>();

    public OpretFadWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Fad");

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
        Label lblHeader = new Label("Opret Nyt Fad");
        lblHeader.setFont(new Font(20));
        pane.add(lblHeader, 0, 0, 2, 1);

        // Fad-data
        pane.add(new Label("Fad Nr:"), 0, 1);
        pane.add(txfFadNr, 1, 1);

        pane.add(new Label("Størrelse (L):"), 0, 2);
        pane.add(txfStoerrelseL, 1, 2);

        pane.add(new Label("Trætype:"), 0, 3);
        pane.add(txfTraeType, 1, 3);

        pane.add(new Label("Tidligere indhold:"), 0, 4);
        pane.add(txfTidligereIndhold, 1, 4);

        pane.add(new Label("Status:"), 0, 5);
        pane.add(txfStatus, 1, 5);

        // Påfyldning-input sektion
        Label lblPaafyldningHeader = new Label("Tilføj påfyldninger til fadet");
        lblPaafyldningHeader.setFont(new Font(14));
        pane.add(lblPaafyldningHeader, 0, 7, 2, 1);

        pane.add(new Label("Påfyldningstid:"), 0, 8);
        pane.add(txfPaafyldningstid, 1, 8);

        pane.add(new Label("Mængde (L):"), 0, 9);
        pane.add(txfMaengdeL, 1, 9);

        pane.add(new Label("Alkohol % ved påfyldning:"), 0, 10);
        pane.add(txfAlkoholPcVedPaafyldning, 1, 10);

        pane.add(new Label("Dato:"), 0, 11);
        pane.add(dpDato, 1, 11);

        pane.add(new Label("Udført af:"), 0, 12);
        cbbMedarbejder.getItems().setAll(Controller.getLagerMedarbejdere());
        pane.add(cbbMedarbejder, 1, 12);

        pane.add(btnTilfoejPaafyldning, 1, 13);
        btnTilfoejPaafyldning.setOnAction(event -> tilfoejPaafyldningAction());

        // Liste over valgte påfyldninger
        Label lblValgtePaafyldninger = new Label("Valgte påfyldninger:");
        pane.add(lblValgtePaafyldninger, 0, 15);
        lvwPaafyldninger.setPrefHeight(120);
        lvwPaafyldninger.setPrefWidth(350);
        pane.add(lvwPaafyldninger, 0, 16, 2, 1);

        // Opret-knap
        pane.add(btnOpret, 0, 18);
        btnOpret.setOnAction(event -> opretFadAction());
    }

    private void tilfoejPaafyldningAction() {
        String paafyldningstid = txfPaafyldningstid.getText().trim();
        String maengdeText = txfMaengdeL.getText().trim();
        String alkoholText = txfAlkoholPcVedPaafyldning.getText().trim();
        LocalDate dato = dpDato.getValue();
        LagerMedarbejder medarbejder = cbbMedarbejder.getValue();

        if (paafyldningstid.isEmpty() || maengdeText.isEmpty() || alkoholText.isEmpty() || dato == null || medarbejder == null) {
            showAlert("Fejl", "Udfyld alle felter for påfyldning og vælg medarbejder.");
            return;
        }

        double maengdeL;
        double alkoholPc;
        try {
            maengdeL = Double.parseDouble(maengdeText);
            alkoholPc = Double.parseDouble(alkoholText);
            if (maengdeL <= 0) {
                showAlert("Fejl", "Mængde skal være større end 0.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Fejl", "Mængde og alkohol % skal være tal.");
            return;
        }

        Paafyldning p = new Paafyldning(paafyldningstid, maengdeL, alkoholPc, dato, medarbejder);
        paafyldningerTilFad.add(p);

        opdaterPaafyldningerVisning();

        // ryd felter til næste påfyldning
        txfPaafyldningstid.clear();
        txfMaengdeL.clear();
        txfAlkoholPcVedPaafyldning.clear();
        dpDato.setValue(LocalDate.now());
        cbbMedarbejder.getSelectionModel().clearSelection();
    }

    private void opdaterPaafyldningerVisning() {
        lvwPaafyldninger.getItems().clear();
        for (Paafyldning p : paafyldningerTilFad) {
            String line = String.format(
                    "%s | %.1f L | %.1f%% | %s | %s",
                    p.getPaafyldningstid(),
                    p.getMaengdeL(),
                    p.getAlkoholPcVedPaafyldning(),
                    p.getDato(),
                    p.getUdfoertAf().getNavn()
            );
            lvwPaafyldninger.getItems().add(line);
        }
    }

    private void opretFadAction() {
        String fadId = txfFadNr.getText().trim();
        String stoerrelseText = txfStoerrelseL.getText().trim();
        String traeType = txfTraeType.getText().trim();
        String tidligereIndhold = txfTidligereIndhold.getText().trim();
        String status = txfStatus.getText().trim();

        if (fadId.isEmpty() || stoerrelseText.isEmpty() || traeType.isEmpty()) {
            showAlert("Fejl", "Udfyld mindst Fad Nr, Størrelse og Trætype.");
            return;
        }

        double stoerrelseL;
        try {
            stoerrelseL = Double.parseDouble(stoerrelseText);
        } catch (NumberFormatException e) {
            showAlert("Fejl", "Størrelse (L) skal være et tal.");
            return;
        }

        // Hylde håndterer vi ikke her endnu → send null
        fad = Controller.createFad(
                fadId,
                stoerrelseL,
                traeType,
                tidligereIndhold,
                status,
                paafyldningerTilFad,
                null   // hylde håndteres et andet sted
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

    public Fad getFad() {
        return fad;
    }
}
