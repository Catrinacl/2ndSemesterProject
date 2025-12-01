package GUI;

import Model.Fad;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;



public class FadOversigtPane extends GridPane {

    public FadOversigtPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        BorderPane root = new BorderPane();

        TableView tableView = new TableView<Fad>();
        TableColumn columnFadId = new TableColumn<Fad, String>("Fad ID");
        columnFadId.setCellValueFactory(new PropertyValueFactory<Fad, String>("fadId"));

        TableColumn columnStørrelse = new TableColumn<Fad, String>("Størrelse(Liter)");
        columnStørrelse.setCellValueFactory(new PropertyValueFactory<Fad, String>("stoerrelseL"));

        tableView.getColumns().add(columnFadId);
        tableView.getColumns().add(columnStørrelse);

        root.setCenter(tableView);
    }
}
