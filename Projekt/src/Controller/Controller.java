package Controller;

import GUI.Observer;

import Model.*;
import Storage.ListStorage;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public abstract class Controller {

    private static Storage storage;

    public static void setStorage(Storage s) {
        storage = s;
    }



    public static Lager createLager(String lagerId, String lagerType, String adresse, ArrayList<Reol> reoler) {
        Lager lager = new Lager(lagerId, lagerType, adresse, reoler);
        storage.addLager(lager);
        return lager;
    }

    public static ArrayList<Lager> getLagre() {
        return storage.getLagre();
    }

    public static Reol createReol(String reolId, String reolType, ArrayList<Hylde> hylder, Lager lager) {
        Reol reol = new Reol(reolId, reolType, hylder, lager);
        storage.addReol(reol);
        return reol;
    }

    public static ArrayList<Reol> getReoler() {
        return storage.getReoler();
    }

    public static Hylde createHylde(String hyldeId, int kapacitet, String placering, String hyldeType,
                             ArrayList<Fad> fade, Reol reol) {
        Hylde hylde = new Hylde(hyldeId, kapacitet, placering, hyldeType, fade, reol);
        storage.addHylde(hylde);
        return hylde;
    }

    public static ArrayList<Hylde> getHylder() {
        return storage.getHylder();
    }

    public static Fad createFad(String fadId, double stoerrelseL, String traeType, String tidligereIndhold,
                         String status, ArrayList<Paafyldning> paafyldninger, Hylde hylde) {
        Fad fad = new Fad(fadId, stoerrelseL, traeType, tidligereIndhold, status, paafyldninger, hylde);
        storage.addFad(fad);
        return fad;
    }

    public static ArrayList<Fad> getFade() {
        return storage.getFade();
    }


    public static Destillering createDestillering(String distilleringsID,
                                           LocalDate startDato,
                                           LocalDate slutDato,
                                           String maltBatch,
                                           String kornsort,
                                           String rygemateriale,
                                           String kommentar,
                                           ArrayList<MaengdeDestilleret> maengder) {
        Destillering d = new Destillering(distilleringsID, startDato, slutDato,
                maltBatch, kornsort, rygemateriale, kommentar, maengder);
        storage.addDestillering(d);
        return d;
    }

    public static ArrayList<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
    }

    public static Destillat createDestillat(String destilatID,
                                     String newMakeID,
                                     double totalmaengeL,
                                     double alkoholPc,
                                     ArrayList<MaengdeDestilleret> maengder) {
        Destillat destillat = new Destillat(destilatID, newMakeID, totalmaengeL, alkoholPc, maengder);
        storage.addDestillat(destillat);
        return destillat;
    }

    public static ArrayList<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    public static MaengdeDestilleret createMaengdeDestilleret(double liter,
                                                       Destillering destillering,
                                                       Destillat destillat) {
        MaengdeDestilleret m = new MaengdeDestilleret(liter, destillering, destillat);
        storage.addMaengdeDestilleret(m);
        return m;
    }

    public static ArrayList<MaengdeDestilleret> getMaengder() {
        return storage.getMaengder();
    }


    public static WhiskyProdukt createWhiskyProdukt(String produktNr,
                                             String navn,
                                             String beskrivelse,
                                             double slutAlkoholProcent,
                                             boolean erSingleCask,
                                             int antalFlasker,
                                             VandTilsaetning vandTilsaetning) {
        WhiskyProdukt wp = new WhiskyProdukt(produktNr, navn, beskrivelse,
                slutAlkoholProcent, erSingleCask,
                antalFlasker, vandTilsaetning);
        storage.addWhiskyProdukt(wp);
        return wp;
    }

    public static ArrayList<WhiskyProdukt> getWhiskyProdukter() {
        return storage.getWhiskyProdukter();
    }

    public static Aftapning createAftapning(String aftapningsNr,
                                     double alkoholProcent,
                                     LocalDate dato,
                                     double volumenILiter,
                                     Destillat destillat,
                                     WhiskyProdukt whiskyProdukt) {
        Aftapning a = new Aftapning(aftapningsNr, alkoholProcent, dato, volumenILiter, destillat, whiskyProdukt);
        storage.addAftapning(a);
        return a;
    }

    public static ArrayList<Aftapning> getAftapninger() {
        return storage.getAftapninger();
    }

    public static VandTilsaetning createVandTilsaetning(String vandTilsaetning,
                                                 double vandMaengde,
                                                 String vandKilde,
                                                 WhiskyProdukt whiskyProdukt) {
        VandTilsaetning v = new VandTilsaetning(vandTilsaetning, vandMaengde, vandKilde, whiskyProdukt);
        storage.addVandTilsaetning(v);
        return v;
    }

    public static ArrayList<VandTilsaetning> getVandTilsaetninger() {
        return storage.getVandTilsaetninger();
    }


    public static LagerMedarbejder createLagerMedarbejder(String medarbejderNr, String navn) {
        LagerMedarbejder m = new LagerMedarbejder(medarbejderNr, navn);
        storage.addLagerMedarbejder(m);
        return m;
    }

    public static ArrayList<LagerMedarbejder> getLagerMedarbejdere() {
        return storage.getLagerMedarbejdere();
    }

    public static Paafyldning createPaafyldning(String paafyldningstid,
                                         double maengdeL,
                                         double alkoholPcVedPaafyldning,
                                         LocalDate dato,
                                         LagerMedarbejder udfoertAf) {
        Paafyldning p = new Paafyldning(paafyldningstid, maengdeL,
                alkoholPcVedPaafyldning, dato, udfoertAf);
        storage.addPaafyldning(p);
        return p;
    }

    public static ArrayList<Paafyldning> getPaafyldninger() {return storage.getPaafyldninger();
    }


    // OBSERVER

    private static final List<Observer> observers = new ArrayList<>();

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    private static void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
