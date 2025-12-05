package GUI;

import Controller.Controller;
import Model.Fad;
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


public class FadOversigtPane extends GridPane implements Observer {

    private TableView<Fad> tableView;
    private TextField searchBar = new TextField();

    public FadOversigtPane() {
        Controller.addObserver(this);

        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(15);

        //række 0
        this.add(new Label("Søg efter Fad ID:"), 0, 0);
        this.add(searchBar, 1, 0);

        //række 1
        this.initContent();
        this.add(tableView, 0, 1, 2, 1);

        this.updateFadOversigt(null);
        searchBar.textProperty().addListener((obs, oldText, newText) -> updateFadOversigt(newText));

        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, selectedFad) -> {
            if (selectedFad != null) {
                new FadDetaljeWindow(selectedFad).show();
            }
        });

    }

    private void initContent() {
        tableView = new TableView<>();

        //Kolonne 1
        TableColumn<Fad, String> columnFadId = new TableColumn<>("Fad ID");
        columnFadId.setCellValueFactory(new PropertyValueFactory<>("fadId"));
        columnFadId.setPrefWidth(100);

        //Kolonne 2
        TableColumn<Fad, String> columnDestillatIndhold = new TableColumn<>("Indhold (Destillat ID)");
        columnDestillatIndhold.setCellValueFactory(new PropertyValueFactory<>("destillatID"));
        columnDestillatIndhold.setPrefWidth(150);

        //Kolonne 3
        TableColumn<Fad, Integer> columnStørrelse = new TableColumn<>("Størrelse (Liter)");
        columnStørrelse.setCellValueFactory(new PropertyValueFactory<>("stoerrelseL"));
        columnStørrelse.setPrefWidth(150);

        tableView.getColumns().add(columnFadId);
        tableView.getColumns().add(columnDestillatIndhold);
        tableView.getColumns().add(columnStørrelse);

        tableView.setMaxHeight(Double.MAX_VALUE);
        tableView.setMaxWidth(Double.MAX_VALUE);
        
    }

    public void updateFadOversigt(String searchText) {
        List<Fad> alleFade = Controller.getFade();

        final String filterText = (searchText != null ? searchText : searchBar.getText()).toLowerCase();

        List<Fad> filteredList = alleFade.stream()
                // Filtrer kun hvis søgeteksten er mere end tom
                .filter(fad -> filterText.isEmpty() || fad.getFadId().toLowerCase().contains(filterText))
                .collect(Collectors.toList());

        // Opdater TableView
        tableView.setItems(observableArrayList(filteredList));
    }


    @Override
    public void update() {
        updateFadOversigt(null);
    }
}
