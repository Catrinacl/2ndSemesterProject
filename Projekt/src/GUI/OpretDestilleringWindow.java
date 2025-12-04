package GUI;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpretDestilleringWindow extends Stage {
    private TextField txfDestilleringsId = new TextField();
    private TextField txfStartDato = new TextField();
    private TextField txfSlutDato = new TextField();
    private TextField txfMaltBatch = new TextField();
    private TextField txfKornsort = new TextField();
    private TextField txfRygemateriale = new TextField();
    private TextField txfKommentar = new TextField();

    private Button btnOpret = new Button("Opret Destillering");

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

        Label lblHeader = new Label("Opret Nyt Destillat");
        pane.add(lblHeader, 0, 0, 2, 1);
        lblHeader.setFont(new Font(20));


    }
}
