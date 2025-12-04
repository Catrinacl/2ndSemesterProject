package GUI;

import Controller.Controller;
import Model.Destillat;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import java.util.List;
import java.util.stream.Collectors;
import static javafx.collections.FXCollections.observableArrayList;

public class DestillatOversigtPane extends GridPane implements Observer {

    private TableView<Destillat> tableView;
    private TextField searchBar = new TextField();


    public DestillatOversigtPane() {
        Controller.addObserver(this);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        //række 0
        this.add(new Label("Søg efter Destillat ID:"), 0, 0);
        this.add(searchBar, 1, 0);

        //række 1
        this.initContent();
        this.add(tableView, 0, 1, 2, 1);

        this.updateDestillatOversigt(null);
        searchBar.textProperty().addListener((obs, oldText, newText) -> updateDestillatOversigt(newText));

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selectedDestillat) -> {
            if (selectedDestillat != null) {
                new DestillatDetaljeWindow(selectedDestillat).show();
            }
        });
    }

    private void initContent() {
        tableView = new TableView<>();

        //Kolonne 1
        TableColumn<Destillat, String> columnDestillatId = new TableColumn<>("Destillat ID");
        columnDestillatId.setCellValueFactory(new PropertyValueFactory<>("destilatID"));
        columnDestillatId.setPrefWidth(100);

        //Kolonne 2
        TableColumn<Destillat, String> columnNewMakeNr = new TableColumn<>("New Make Nr");
        columnNewMakeNr.setCellValueFactory(new PropertyValueFactory<>("newMakeID"));
        columnNewMakeNr.setPrefWidth(150);

        //Kolonne 3
        TableColumn<Destillat, Integer> columnMængde = new TableColumn<>("Total Mængde(Liter)");
        columnMængde.setCellValueFactory(new PropertyValueFactory<>("totalmaengeL"));
        columnMængde.setPrefWidth(150);

        tableView.getColumns().add(columnDestillatId);
        tableView.getColumns().add(columnNewMakeNr);
        tableView.getColumns().add(columnMængde);

        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
    }

    public void updateDestillatOversigt(String searchText) {
        List<Destillat> alleDestillater = Controller.getDestillater();

        final String filterText = (searchText != null ? searchText : searchBar.getText()).trim().toLowerCase();

        List<Destillat> filteredList = alleDestillater.stream()
                .filter(destillat -> filterText.isEmpty() || destillat.getDestilatID().toLowerCase().contains(filterText))
                .collect(Collectors.toList());

        tableView.setItems(observableArrayList(filteredList));
    }

    @Override
    public void update() {
        updateDestillatOversigt(null);
    }
}
