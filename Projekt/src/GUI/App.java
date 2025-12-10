package GUI;

import Controller.Controller;
import Controller.Storage;
import Model.*;
import Storage.ListStorage;
import javafx.application.Application;

import java.time.LocalDate;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // initialiser storage
        Storage storage = new ListStorage();

        // giv den til controllerlaget
        Controller.setStorage(storage);

        // test data
        initStorage();

        String fileName = "Projekt\\src\\Model\\Storage.ser";
        Storage storageSerializable = ListStorage.loadStorage(fileName);
        if (storageSerializable == null) {
            storageSerializable = new ListStorage();
            System.out.println("Empty ListStorage is created");
            Controller.setStorage(storageSerializable);
            initStorage();
            System.out.println("Storage is initialized");
        } else {
            Controller.setStorage(storageSerializable);
        }

        Application.launch(MainPane.class);

        ListStorage.saveStorage(fileName, Controller.getStorage());
    }

    private static void initStorage() {
        // 1) Lager-medarbejder (bruges til påfyldninger)
        LagerMedarbejder m1 = Controller.createLagerMedarbejder("LM001", "Hans Hansen");
        LagerMedarbejder m2 = Controller.createLagerMedarbejder("LM002", "Lise Larsen");

        // 2) Nogle destilleringer
        Destillering d1 = Controller.createDestillering(
                "D001",
                LocalDate.of(2020, 3, 1),
                LocalDate.of(2020, 3, 10),
                "MB-100",
                "Byg",
                "Tørv",
                "Første testbatch"
        );

        Destillering d2 = Controller.createDestillering(
                "D002",
                LocalDate.of(2020, 4, 5),
                LocalDate.of(2020, 4, 12),
                "MB-101",
                "Byg",
                null,
                "Uryget batch"
        );

        // 3) Sammensætning til et destillat (MaengdeDestilleret)
        ArrayList<MaengdeDestilleret> maengder1 = new ArrayList<>();
        maengder1.add(new MaengdeDestilleret(200.0, d1));
        maengder1.add(new MaengdeDestilleret(150.0, d2));

        Destillat dest1 = Controller.createDestillat(
                "DEST001",
                "NM-001",
                63.5,
                maengder1
        );

        ArrayList<MaengdeDestilleret> maengder2 = new ArrayList<>();
        maengder2.add(new MaengdeDestilleret(300.0, d2));

        Destillat dest2 = Controller.createDestillat(
                "DEST002",
                "NM-002",
                68.0,
                maengder2
        );

        // 4) Lager / reoler / hylder
        Lager lager1 = Controller.createLager(
                "LAG01",
                "Hovedlager",
                "Destilleri Allé 1"
        );

        Reol reol1 = Controller.createReol(
                "R1",
                "Standard",
                lager1
        );

        Reol reol2 = Controller.createReol(
                "R2",
                "Kølig",
                lager1
        );

        Hylde hylde1 = Controller.createHylde(
                "H1",
                10,
                "R1-1",
                "Standard",
                new ArrayList<>(),   // fade sættes på om lidt
                reol1
        );

        Hylde hylde2 = Controller.createHylde(
                "H2",
                8,
                "R1-2",
                "Standard",
                new ArrayList<>(),
                reol1
        );

        Hylde hylde3 = Controller.createHylde(
                "H3",
                6,
                "R2-1",
                "Kølig",
                new ArrayList<>(),
                reol2
        );

        Fad fad1 = Controller.createFad(
                "FAD001",
                200.0,
                "Ex-Bourbon",
                "Bourbon",
                "Fyldt",
                hylde1
        );

        Fad fad2 = Controller.createFad(
                "FAD002",
                250.0,
                "Sherry",
                "Oloroso Sherry",
                "Fyldt",
                hylde2
        );

        // 4) Nogle påfyldninger (Paafyldning) til senere fade
        Paafyldning p1 = Controller.createPaafyldning(
                "Første fyldning",
                100.0,
                63.5,
                LocalDate.of(2021, 6, 1),
                m1,
                fad1,
                dest1
        );

        Paafyldning p2 = Controller.createPaafyldning(
                "Anden fyldning",
                80.0,
                63.5,
                LocalDate.of(2021, 8, 15),
                m2,
                fad2,
                dest2
        );

        Paafyldning p3 = Controller.createPaafyldning(
                "Single cask fyldning",
                120.0,
                68.0,
                LocalDate.of(2022, 2, 10),
                m1,
                fad1,
                dest1
        );

        // 7) Whiskyprodukt + aftapninger
        WhiskyProdukt wp1 = Controller.createWhiskyProdukt(
                "WP001",
                "Single Malt 10Y",
                "Testprodukt fra FAD001 og FAD002",
                46.0,
                false,
                500,
                null  // ingen vandtilsætning endnu
        );

        Aftapning a1 = Controller.createAftapning(
                "AFT001",
                46.0,
                LocalDate.of(2024, 5, 1),
                200.0,
                dest1,
                wp1
        );

        Aftapning a2 = Controller.createAftapning(
                "AFT002",
                48.0,
                LocalDate.of(2024, 10, 10),
                150.0,
                dest2,
                wp1
        );
    }
}