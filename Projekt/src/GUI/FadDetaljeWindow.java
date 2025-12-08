package GUI;

import Model.Fad;
import Model.Paafyldning;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FadDetaljeWindow extends Stage {

    public FadDetaljeWindow(Fad fad) {
        this.setTitle("Detaljer for Fad " + fad.getFadId());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        pane.add(new Label("Fad ID: " + fad.getFadId()), 0, 0);
        pane.add(new Label("Størrelse i Liter: " + fad.getStoerrelseL()), 0, 1);
        pane.add(new Label("Træ type: " + fad.getTraeType()), 0, 2);
        pane.add(new Label("Tidligere indhold: " + fad.getTidligereIndhold()), 0, 3);
        pane.add(new Label("Status: " + fad.getStatus()), 0, 4);

        Label lblPaafyldningsHistorik = new Label("Påfyldnings historik:");
        lblPaafyldningsHistorik.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        pane.add(lblPaafyldningsHistorik, 0, 6);

        VBox box = new VBox();

        if (fad.getPaafyldninger() != null) {
            for (Paafyldning p : fad.getPaafyldninger()) {
                Label lbl = new Label(p.toString());
                box.getChildren().add(lbl);
            }
        } else {
            box.getChildren().add(new Label("Ingen påfyldninger."));
        }
        pane.add(box, 0, 7);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
}
