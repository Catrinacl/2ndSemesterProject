package GUI;

import Model.Destillat;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class DestillatDetaljeWindow extends Stage {

    public DestillatDetaljeWindow(Destillat destillat) {
        this.setTitle("Detaljer for destillat: " + destillat.getDestilatID());

        GridPane pane = new GridPane();
        pane.setPadding(new Insets(20));
        pane.setVgap(10);

        pane.add(new Label("Destillat ID: " + destillat.getDestilatID()), 0, 0);
        pane.add(new Label("New Make ID: " + destillat.getNewMakeID()), 0, 1);
        pane.add(new Label("Total mængde i Liter: " + destillat.getTotalmaengeL()), 0, 2);
        pane.add(new Label("Alkohol i %: " + destillat.getAlkoholPc()), 0, 3);
        pane.add(new Label("Mængde destilleret: " + destillat.getMaengderToString()), 0, 4);
    }
}
