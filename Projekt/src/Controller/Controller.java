package Controller;

import Model.*;
import Storage.Storage;

import java.util.ArrayList;
import java.time.LocalDate;

public class Controller {

    private Storage storage;

    public Controller() {
        storage = new Storage();
    }


    public Lager createLager(String lagerId, String lagerType, String adresse, ArrayList<Reol> reoler) {
        Lager lager = new Lager(lagerId, lagerType, adresse, reoler);
        storage.addLager(lager);
        return lager;
    }

    public ArrayList<Lager> getLagre() {
        return storage.getLagre();
    }

    public Reol createReol(String reolId, String reolType, ArrayList<Hylde> hylder, Lager lager) {
        Reol reol = new Reol(reolId, reolType, hylder, lager);
        storage.addReol(reol);
        return reol;
    }

    public ArrayList<Reol> getReoler() {
        return storage.getReoler();
    }

    public Hylde createHylde(String hyldeId, int kapacitet, String placering, String hyldeType,
                             ArrayList<Fad> fade, Reol reol) {
        Hylde hylde = new Hylde(hyldeId, kapacitet, placering, hyldeType, fade, reol);
        storage.addHylde(hylde);
        return hylde;
    }

    public ArrayList<Hylde> getHylder() {
        return storage.getHylder();
    }

    public Fad createFad(String fadId, double stoerrelseL, String traeType, String tidligereIndhold,
                         String status, ArrayList<Paafyldning> paafyldninger, Hylde hylde) {
        Fad fad = new Fad(fadId, stoerrelseL, traeType, tidligereIndhold, status, paafyldninger, hylde);
        storage.addFad(fad);
        return fad;
    }

    public ArrayList<Fad> getFade() {
        return storage.getFade();
    }


    public Destillering createDestillering(String distilleringsID,
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

    public ArrayList<Destillering> getDestilleringer() {
        return storage.getDestilleringer();
    }

    public Destillat createDestillat(String destilatID,
                                     String newMakeID,
                                     double totalmaengeL,
                                     double alkoholPc,
                                     ArrayList<MaengdeDestilleret> maengder) {
        Destillat destillat = new Destillat(destilatID, newMakeID, totalmaengeL, alkoholPc, maengder);
        storage.addDestillat(destillat);
        return destillat;
    }

    public ArrayList<Destillat> getDestillater() {
        return storage.getDestillater();
    }

    public MaengdeDestilleret createMaengdeDestilleret(double liter,
                                                       Destillering destillering,
                                                       Destillat destillat) {
        MaengdeDestilleret m = new MaengdeDestilleret(liter, destillering, destillat);
        storage.addMaengdeDestilleret(m);
        return m;
    }

    public ArrayList<MaengdeDestilleret> getMaengder() {
        return storage.getMaengder();
    }


    public WhiskyProdukt createWhiskyProdukt(String produktNr,
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

    public ArrayList<WhiskyProdukt> getWhiskyProdukter() {
        return storage.getWhiskyProdukter();
    }

    public Aftapning createAftapning(String aftapningsNr,
                                     double alkoholProcent,
                                     LocalDate dato,
                                     double volumenILiter,
                                     Destillat destillat,
                                     WhiskyProdukt whiskyProdukt) {
        Aftapning a = new Aftapning(aftapningsNr, alkoholProcent, dato, volumenILiter, destillat, whiskyProdukt);
        storage.addAftapning(a);
        return a;
    }

    public ArrayList<Aftapning> getAftapninger() {
        return storage.getAftapninger();
    }

    public VandTilsaetning createVandTilsaetning(String vandTilsaetning,
                                                 double vandMaengde,
                                                 String vandKilde,
                                                 WhiskyProdukt whiskyProdukt) {
        VandTilsaetning v = new VandTilsaetning(vandTilsaetning, vandMaengde, vandKilde, whiskyProdukt);
        storage.addVandTilsaetning(v);
        return v;
    }

    public ArrayList<VandTilsaetning> getVandTilsaetninger() {
        return storage.getVandTilsaetninger();
    }


    public LagerMedarbejder createLagerMedarbejder(String medarbejderNr, String navn) {
        LagerMedarbejder m = new LagerMedarbejder(medarbejderNr, navn);
        storage.addLagerMedarbejder(m);
        return m;
    }

    public ArrayList<LagerMedarbejder> getLagerMedarbejdere() {
        return storage.getLagerMedarbejdere();
    }

    public Paafyldning createPaafyldning(String paafyldningstid,
                                         double maengdeL,
                                         double alkoholPcVedPaafyldning,
                                         LocalDate dato,
                                         LagerMedarbejder udfoertAf) {
        Paafyldning p = new Paafyldning(paafyldningstid, maengdeL,
                alkoholPcVedPaafyldning, dato, udfoertAf);
        storage.addPaafyldning(p);
        return p;
    }

    public ArrayList<Paafyldning> getPaafyldninger() {
        return storage.getPaafyldninger();
    }
}
