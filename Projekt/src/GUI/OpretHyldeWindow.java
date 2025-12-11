package GUI;

import Controller.Controller;
import Model.Fad;
import Model.Hylde;
import Model.Reol;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

public class OpretHyldeWindow extends Stage {

    private Hylde hylde;

    private TextField txfHyldeId;
    private TextField txfKapacitet;
    private TextField txfPlacering;
    private TextField txfHyldeType;

    private final ListView<Reol> lvwReoler = new ListView<>();
    private final Button btnOpret = new Button("Opret Hylde");

    public OpretHyldeWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Hylde");

        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        txfHyldeId = new TextField();
        txfKapacitet = new TextField();
        txfPlacering = new TextField();
        txfHyldeType = new TextField();

        Label lblHeader = new Label("Opret Ny Hylde");
        pane.add(lblHeader, 0, 0, 2, 1);

        Label lblReol = new Label("Vælg reol:");
        pane.add(lblReol, 0, 1);
        pane.add(lvwReoler, 0, 2, 2, 1);
        lvwReoler.setPrefHeight(100);
        lvwReoler.setPrefWidth(250);
        lvwReoler.getItems().setAll(Controller.getReoler());

        pane.add(new Label("Hylde ID:"), 0, 3);
        pane.add(txfHyldeId, 1, 3);

        pane.add(new Label("Kapacitet (antal fade):"), 0, 4);
        pane.add(txfKapacitet, 1, 4);

        pane.add(new Label("Placering:"), 0, 5);
        pane.add(txfPlacering, 1, 5);

        pane.add(new Label("Hylde-type:"), 0, 6);
        pane.add(txfHyldeType, 1, 6);

        pane.add(btnOpret, 0, 8);
        btnOpret.setOnAction(event -> opretHyldeAction());
    }

    private void opretHyldeAction() {
        String hyldeId = txfHyldeId.getText().trim();
        String kapText = txfKapacitet.getText().trim();
        String placering = txfPlacering.getText().trim();
        String hyldeType = txfHyldeType.getText().trim();
        Reol valgtReol = lvwReoler.getSelectionModel().getSelectedItem();

        if (hyldeId.isEmpty() || kapText.isEmpty() || placering.isEmpty() || hyldeType.isEmpty() || valgtReol == null) {
            showAlert("Udfyld alle felter og vælg en reol.");
            return;
        }

        int kapacitet;
        try {
            kapacitet = Integer.parseInt(kapText);
        } catch (NumberFormatException e) {
            showAlert("Kapacitet skal være et helt tal.");
            return;
        }

        ArrayList<Fad> fade = new ArrayList<>();

        hylde = Controller.createHylde(
                hyldeId,
                kapacitet,
                placering,
                hyldeType,
                fade,
                valgtReol
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

    public Hylde getHylde() {
        return hylde;
    }
}
