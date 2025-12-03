package GUI;

import Controller.Controller;
import Controller.Storage;
import Storage.ListStorage;
import javafx.application.Application;

public class App {

    public static void main(String[] args) {
       // initialiser storage
        Storage storage = new ListStorage();

        // giv den til controllerlaget
        Controller.setStorage(storage);

        //  test data
        initStorage();

        Application.launch(MainPane.class);
    }

    private static void initStorage() {

        // Controller.create...

    }
}
