package GUI;

import Controller.Controller;
import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RedigerFadWindow extends Stage {
    private final Fad fad;

    private final TextField txfStoerrelseL = new TextField();
    private final TextField txfTraeType = new TextField();
    private final TextField txfTidligereIndhold = new TextField();
    private final TextField txfStatus = new TextField();

    private final Button btnGem = new Button("Gem Ændringer");
    private final Button btnAnnuller = new Button("Annuller");

    public RedigerFadWindow(Fad fad) {
        this.fad = fad;

        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Rediger Fad: " + fad.getFadId());

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Rediger Fad: " + fad.getFadId());
        pane.add(lblHeader, 0, 0, 2, 1);
        lblHeader.setFont(new Font(20));

        pane.add(new Label("Størrelse (Liter):"), 0, 1);
        txfStoerrelseL.setText(String.valueOf(fad.getStoerrelseL()));
        pane.add(txfStoerrelseL, 1, 1);

        pane.add(new Label("Trætype:"), 0, 2);
        txfTraeType.setText(fad.getTraeType());
        pane.add(txfTraeType, 1, 2);

        pane.add(new Label("Tidligere Indhold:"), 0, 3);
        txfTidligereIndhold.setText(fad.getTidligereIndhold());
        pane.add(txfTidligereIndhold, 1, 3);

        pane.add(new Label("Status:"), 0, 4);
        txfStatus.setText(fad.getStatus());
        pane.add(txfStatus, 1, 4);

        pane.add(btnGem, 0, 5);
        pane.add(btnAnnuller, 1, 5);

        btnGem.setOnAction(event -> this.gemAction());
        btnAnnuller.setOnAction(event -> this.close());
    }

    private void gemAction() {
        String stoerrelseText = txfStoerrelseL.getText().trim();
        String traeType = txfTraeType.getText().trim();
        String tidligereIndhold = txfTidligereIndhold.getText().trim();
        String status = txfStatus.getText().trim();

        if (stoerrelseText.isEmpty() || traeType.isEmpty() || status.isEmpty()) {
            showAlert("Udfyld Størrelse, Trætype og Status.");
            return;
        }

        double stoerrelseL;
        try {
            stoerrelseL = Double.parseDouble(stoerrelseText);
        } catch (NumberFormatException e) {
            showAlert("Størrelse (Liter) skal være et gyldigt tal");
            return;
        }

        Controller.updateFad(fad, stoerrelseL, traeType, tidligereIndhold, status);
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
