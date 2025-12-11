package GUI;

import Controller.Controller;
import Model.Destillat;
import Model.Destillering;
import Model.MaengdeDestilleret;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class OpretDestillatWindow extends Stage {

    private TextField txfDestillatId = new TextField();
    private TextField txfNewMakeId = new TextField();
    private TextField txfMængde = new TextField();
    private TextField txfAlkoholPc = new TextField();
    private TextField txfTotalMængde = new TextField();

    private final ListView<Destillering> lvwDestillering = new ListView<>();
    private final ListView<String> lvwIndhold = new ListView<>();

    private final Button btnTilfoej = new Button("Tilføj destillering");
    private final Button btnOpret = new Button("Opret Destillat");

    private final ArrayList<MaengdeDestilleret> sammensaetning = new ArrayList<>();

    public OpretDestillatWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Destillat");

        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Nyt Destillat");
        lblHeader.setFont(new Font(20));
        pane.add(lblHeader, 0, 0, 2, 1);

        Label lblDestillering = new Label("Vælg destillering(er):");
        pane.add(lblDestillering, 0, 1);
        pane.add(lvwDestillering, 0, 2);
        lvwDestillering.setPrefHeight(80);
        lvwDestillering.setPrefWidth(250);
        lvwDestillering.getItems().setAll(Controller.getDestilleringer());
        lvwDestillering.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        pane.add(new Label("Vælg Mængde (L):"), 1, 1);
        pane.add(txfMængde, 1, 2);

        pane.add(btnTilfoej, 1, 3);
        btnTilfoej.setOnAction(event -> tilfoejDestilleringAction());

        pane.add(new Label("Destillat ID:"), 0, 5);
        pane.add(new Label("New Make Nr:"), 0, 6);
        pane.add(new Label("Alkohol %:"), 0, 7);

        pane.add(txfDestillatId, 1, 5);
        pane.add(txfNewMakeId, 1, 6);
        pane.add(txfAlkoholPc, 1, 7);

        Label lblIndhold = new Label("Valgt indhold:");
        pane.add(lblIndhold, 0, 9);
        pane.add(lvwIndhold, 0, 10);
        lvwIndhold.setPrefHeight(80);
        lvwIndhold.setPrefWidth(250);
        lvwIndhold.setDisable(true);

        txfTotalMængde.setEditable(false);
        txfTotalMængde.setDisable(true);
        pane.add(txfTotalMængde, 1, 10);
        pane.add(new Label("Liter"), 2, 10);

        pane.add(btnOpret, 0, 12);
        btnOpret.setOnAction(event -> opretDestillatAction());
    }

    private void tilfoejDestilleringAction() {
        Destillering valgt = lvwDestillering.getSelectionModel().getSelectedItem();
        if (valgt == null) {
            showAlert("Du skal vælge en destillering.");
            return;
        }

        double liter;
        try {
            liter = Double.parseDouble(txfMængde.getText());
            if (liter <= 0) {
                showAlert("Mængden skal være større end 0.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Mængde skal være et tal.");
            return;
        }

        MaengdeDestilleret m = new MaengdeDestilleret(liter, valgt);
        sammensaetning.add(m);

        opdaterIndholdOgTotal();

        txfMængde.clear();
    }

    private void opdaterIndholdOgTotal() {
        lvwIndhold.getItems().clear();
        double total = 0;

        for (MaengdeDestilleret m : sammensaetning) {
            lvwIndhold.getItems().add(
                    m.getLiter() + " L fra destillering " + m.getDestillering().getDestilleringId()
            );
            total += m.getLiter();
        }

        txfTotalMængde.setText(String.valueOf(total));
    }


    private void opretDestillatAction() {
        if (sammensaetning.isEmpty()) {
            showAlert("Du skal tilføje mindst én destillering til destillatet.");
            return;
        }

        String destillatId = txfDestillatId.getText().trim();
        String newMakeId = txfNewMakeId.getText().trim();
        String alkoholText = txfAlkoholPc.getText().trim();

        if (destillatId.isEmpty() || newMakeId.isEmpty() || alkoholText.isEmpty()) {
            showAlert("Udfyld Destillat ID, New Make Nr og Alkohol %.");
            return;
        }

        double alkoholPct;
        try {
            alkoholPct = Double.parseDouble(alkoholText);
        } catch (NumberFormatException e) {
            showAlert("Alkohol % skal være et tal.");
            return;
        }

        Destillat destillat = Controller.createDestillat(
                destillatId,
                newMakeId,
                alkoholPct,
                sammensaetning
        );

        this.close();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fejl");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
