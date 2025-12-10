package Controller;

import GUI.Observer;

import Model.*;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public abstract class Controller {

    private static Storage storage;

    public static Storage getStorage() {
        return storage;
    }

    public static void setStorage(Storage s) {
        storage = s;
    }



    public static Lager createLager(String lagerId, String lagerType, String adresse) {
        Lager lager = new Lager(lagerId, lagerType, adresse);
        storage.addLager(lager);
        notifyObservers();
        return lager;
    }

    public static ArrayList<Lager> getLagre() {
        return storage.getLagre();
    }

    public static Reol createReol(String reolId, String reolType, Lager lager) {
        Reol reol = new Reol(reolId, reolType, lager);
        storage.addReol(reol);
        notifyObservers();
        return reol;
    }

    public static ArrayList<Reol> getReoler() {
        return storage.getReoler();
    }

    public static Hylde createHylde(String hyldeId, int kapacitet, String placering, String hyldeType,
                             ArrayList<Fad> fade, Reol reol) {
        Hylde hylde = new Hylde(hyldeId, kapacitet, placering, hyldeType, fade, reol);
        storage.addHylde(hylde);
        notifyObservers();
        return hylde;
    }

    public static ArrayList<Hylde> getHylder() {
        return storage.getHylder();
    }

    public static Fad createFad(String fadId, double stoerrelseL, String traeType, String tidligereIndhold,
                         String status, Hylde hylde) {
        Fad fad = new Fad(fadId, stoerrelseL, traeType, tidligereIndhold, status, hylde);
        storage.addFad(fad);
        notifyObservers();
        return fad;
    }

    public static ArrayList<Fad> getFade() {
        return storage.getFade();
    }

    public static ArrayList<Fad> findFadeKlarTilAftapning() {
        ArrayList<Fad> result = new ArrayList<>();
        for (Fad f : storage.getFade()) {
            if (f.erKlarTilAftapning()) {
                result.add(f);
            }
        }
        return result;
    }


    public static Destillering createDestillering(String destilleringId,
                                           LocalDate startDato,
                                           LocalDate slutDato,
                                           String maltBatch,
                                           String kornsort,
                                           String rygemateriale,
                                           String kommentar) {
        Destillering d = new Destillering(destilleringId, startDato, slutDato,
                maltBatch, kornsort, rygemateriale, kommentar);
        storage.addDestillering(d);
        notifyObservers();
        return d;
    }

    public static ArrayList<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
    }

    public static Destillat createDestillat(String destillatID,
                                            String newMakeID,
                                            double alkoholPc,
                                            ArrayList<MaengdeDestilleret> maengder) {


        double totalmaengdeL = 0;
        for (MaengdeDestilleret m : maengder) {
            totalmaengdeL += m.getLiter();
        }

        Destillat destillat = new Destillat(destillatID, newMakeID, totalmaengdeL, alkoholPc);

        // tilføj alle maengder til destillatet
        for (MaengdeDestilleret m : maengder) {
            destillat.addMaengdeDestilleret(m);
            storage.addMaengdeDestilleret(m);
        }

        storage.addDestillat(destillat);
        notifyObservers();
        return destillat;
    }


    public static ArrayList<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    public static MaengdeDestilleret createMaengdeDestilleret(double liter,
                                                       Destillering destillering) {
        MaengdeDestilleret m = new MaengdeDestilleret(liter, destillering);
        storage.addMaengdeDestilleret(m);
        notifyObservers();
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
        notifyObservers();
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

        Aftapning a = new Aftapning(
                aftapningsNr,
                alkoholProcent,
                dato,
                volumenILiter,
                destillat,
                whiskyProdukt
        );

        // dobbeltrettet kobling: produktet skal kende sine aftapninger
        whiskyProdukt.addAftapning(a);
        storage.addAftapning(a);
        notifyObservers();
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
        notifyObservers();
        return v;
    }

    public static ArrayList<VandTilsaetning> getVandTilsaetninger() {
        return storage.getVandTilsaetninger();
    }


    public static LagerMedarbejder createLagerMedarbejder(String medarbejderNr, String navn) {
        LagerMedarbejder m = new LagerMedarbejder(medarbejderNr, navn);
        storage.addLagerMedarbejder(m);
        notifyObservers();
        return m;
    }

    public static ArrayList<LagerMedarbejder> getLagerMedarbejdere() {
        return storage.getLagerMedarbejdere();
    }

    public static Paafyldning createPaafyldning(String paafyldningsId,
                                         double maengdeL,
                                         double alkoholPcVedPaafyldning,
                                         LocalDate dato,
                                         LagerMedarbejder udfoertAf, Fad fad, Destillat destillat) {
        Paafyldning p = new Paafyldning(paafyldningsId, maengdeL,
                alkoholPcVedPaafyldning, dato, udfoertAf, fad, destillat);
        storage.addPaafyldning(p);
        fad.addPaafyldning(p);
        notifyObservers();
        return p;
    }

    public static ArrayList<Paafyldning> getPaafyldninger() {return storage.getPaafyldninger();
    }

    // DELETE
    public static void deleteFad(Fad fad) {
        if (fad.getHylde() != null) {
            fad.getHylde().getFade().remove(fad);
        }
        storage.deleteFad(fad);
        notifyObservers();
    }

    public static void deleteProdukt(WhiskyProdukt whiskyProdukt) {
        storage.deleteProdukt(whiskyProdukt);
        notifyObservers();
    }

    // UPDATE
    public static Fad updateFad(Fad fad, double stoerrelseL, String traeType, String tidligereIndhold, String status) {
        fad.setStoerrelseL(stoerrelseL);
        fad.setTraeType(traeType);
        fad.setTidligereIndhold(tidligereIndhold);
        fad.setStatus(status);
        notifyObservers();
        return fad;
    }

    public static WhiskyProdukt updateWhiskyProdukt(WhiskyProdukt whiskyProdukt, String navn, String beskrivelse, double slutAlkoholProcent, int antalFlasker) {
        whiskyProdukt.setNavn(navn);
        whiskyProdukt.setBeskrivelse(beskrivelse);
        whiskyProdukt.setSlutAlkoholProcent(slutAlkoholProcent);
        whiskyProdukt.setAntalFlasker(antalFlasker);
        notifyObservers();
        return whiskyProdukt;
    }


    // OBSERVER

    private static final List<Observer> observers = new ArrayList<>();

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
