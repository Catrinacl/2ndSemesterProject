package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainPane extends Application{

    @Override
    public void start(Stage stage) {
        stage.setTitle("2. Semester Projekt");
        BorderPane pane = new BorderPane();
        this.initContent(pane);

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab tabFad = new Tab("Oversigt over fade");
        tabPane.getTabs().add(tabFad);
        FadOversigtPane fadPane = new FadOversigtPane();
        tabFad.setContent(fadPane);

        Tab tabDestilat = new Tab("Oversigt over destillater");
        tabPane.getTabs().add(tabDestilat);
        DestillatOversigtPane destilatPane = new DestillatOversigtPane();
        tabDestilat.setContent(destilatPane);

    }
}
