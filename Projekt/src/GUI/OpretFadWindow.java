package GUI;

import Controller.Controller;
import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpretFadWindow extends Stage {

    // Fad-felter
    private final TextField txfFadNr = new TextField();
    private final TextField txfStoerrelseL = new TextField();
    private final TextField txfTraeType = new TextField();
    private final TextField txfTidligereIndhold = new TextField();
    private final TextField txfStatus = new TextField();

    private final Button btnOpret = new Button("Opret Fad");

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

        // Opret-knap
        pane.add(btnOpret, 0, 18);
        btnOpret.setOnAction(event -> opretFadAction());
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
        Fad fad = Controller.createFad(
                fadId,
                stoerrelseL,
                traeType,
                tidligereIndhold,
                status,
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


}
