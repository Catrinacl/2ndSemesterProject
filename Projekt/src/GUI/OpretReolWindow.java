package GUI;

import Controller.Controller;

import Model.Lager;
import Model.Reol;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpretReolWindow extends Stage {
    private Reol reol;

    private TextField txfReolId = new TextField();
    private TextField txfReolType = new TextField();
    private ComboBox<Lager> cbLager = new ComboBox<>();
    private Button btnOpret = new Button("Opret Reol");

    public OpretReolWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Reol");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    public void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Ny Reol");
        pane.add(lblHeader, 0, 0, 2, 1);

        pane.add(new Label("Reol ID:"), 0, 3);
        pane.add(txfReolId, 1, 3);

        pane.add(new Label("Reol Type:"), 0, 5);
        pane.add(txfReolType, 1, 5);

        pane.add(new Label("Lager:"), 0, 6);
        pane.add(cbLager, 1, 6);
        cbLager.getItems().addAll(Controller.getLagre());
        cbLager.setPromptText("---");

        pane.add(btnOpret, 0, 12);
        btnOpret.setOnAction(event -> this.opretReolAction());
    }

    private void opretReolAction() {

        if (txfReolId.getText().isEmpty() || txfReolType.getText().isEmpty() || cbLager.getValue() == null) {
            showAlert("Udfyld alle felter!");
            return;
        }

        reol = Controller.createReol(
                txfReolId.getText(),
                txfReolType.getText(),
                cbLager.getValue()
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

    public Reol getReol() {
        return reol;
    }
}
