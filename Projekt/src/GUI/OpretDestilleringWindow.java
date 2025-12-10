package GUI;

import Controller.Controller;
import Model.Destillering;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;

public class OpretDestilleringWindow extends Stage {
    private TextField txfDestilleringId = new TextField();
    private TextField txfMaltBatch = new TextField();
    private TextField txfKornsort = new TextField();
    private TextField txfRygemateriale = new TextField();
    private TextField txfKommentar = new TextField();
    private Button btnOpret = new Button("Opret Destillering");
    private ComboBox<String> cbKornsort = new ComboBox<>();
    private DatePicker dpStartDato = new DatePicker(LocalDate.now());
    private DatePicker dpSlutDato = new DatePicker(LocalDate.now());

    public OpretDestilleringWindow() {
        this.initModality(Modality.APPLICATION_MODAL);
        this.setTitle("Opret Destillering");

        GridPane pane = new GridPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        Label lblHeader = new Label("Opret Ny Destillering");
        pane.add(lblHeader, 0, 0, 2, 1);
        lblHeader.setFont(new Font(20));

        pane.add(new Label("Destillering ID:"), 0, 1);
        pane.add(txfDestilleringId, 0, 2);
        pane.add(new Label("Startdato:"), 0, 3);
        pane.add(dpStartDato, 0, 4);
        pane.add(new Label("Slutdato:"), 0, 5);
        pane.add(dpSlutDato, 0, 6);
        pane.add(new Label("Malt Batch:"), 0, 7);
        pane.add(txfMaltBatch, 0, 8);
        pane.add(new Label("Kornsort:"), 0, 9);
        cbKornsort.getItems().addAll("Byg", "Majs", "Hvede", "Rug");
        cbKornsort.setPromptText("---");
        pane.add(cbKornsort, 0, 10);
        pane.add(new Label("Rygemateriale:"), 0, 11);
        pane.add(txfRygemateriale, 0, 12);
        pane.add(new Label("Evt. kommentar:"), 0, 13);
        pane.add(txfKommentar, 0, 14);
        pane.add(btnOpret, 0, 15);
        btnOpret.setOnAction(event -> this.opretDestilleringAction());


    }

    private void opretDestilleringAction() {
        LocalDate startDato = dpStartDato.getValue();
        LocalDate slutDato = dpSlutDato.getValue();
        if (txfDestilleringId.getText().isEmpty() || startDato == null ||
                slutDato== null || txfMaltBatch.getText().isEmpty() ||
                cbKornsort.getValue() == null) {
            System.out.println("Alle felter skal udfyldes!");
            return;
        }

        Destillering destillering = Controller.createDestillering(
                txfDestilleringId.getText(),
                startDato,
                slutDato,
                txfMaltBatch.getText(),
                cbKornsort.getValue(),
                txfRygemateriale.getText(),
                txfKommentar.getText()
        );

        this.close();
    }
}
