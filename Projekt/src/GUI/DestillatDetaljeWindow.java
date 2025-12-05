package GUI;

import Model.Destillat;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DestillatDetaljeWindow extends Stage {

    public DestillatDetaljeWindow(Destillat destillat) {
        this.setTitle("Detaljer for destillat: " + destillat.getDestillatID());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        pane.add(new Label("Destillat ID: " + destillat.getDestillatID()), 0, 0);
        pane.add(new Label("New Make ID: " + destillat.getNewMakeID()), 0, 1);
        pane.add(new Label("Total mængde i Liter: " + destillat.getTotalmaengdeL()), 0, 2);
        pane.add(new Label("Alkohol i %: " + destillat.getAlkoholPc()), 0, 3);
        pane.add(new Label("Mængde destilleret: " + destillat.getMaengderToString()), 0, 4);

        Scene scene = new Scene(pane);
        this.setScene(scene);
    }
}
