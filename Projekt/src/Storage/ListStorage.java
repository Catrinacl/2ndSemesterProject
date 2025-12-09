package Storage;

import Controller.Storage;
import Model.*;

import java.util.ArrayList;

public class ListStorage implements Storage {

    private ArrayList<Lager> lagre = new ArrayList<>();
    private ArrayList<Reol> reoler = new ArrayList<>();
    private ArrayList<Hylde> hylder = new ArrayList<>();
    private ArrayList<Fad> fade = new ArrayList<>();
    private ArrayList<Destillering> destilleringer = new ArrayList<>();
    private ArrayList<Destillat> destillater = new ArrayList<>();
    private ArrayList<MaengdeDestilleret> maengder = new ArrayList<>();
    private ArrayList<WhiskyProdukt> whiskyProdukter = new ArrayList<>();
    private ArrayList<Aftapning> aftapninger = new ArrayList<>();
    private ArrayList<VandTilsaetning> vandTilsaetninger = new ArrayList<>();
    private ArrayList<Paafyldning> paafyldninger = new ArrayList<>();
    private ArrayList<LagerMedarbejder> lagerMedarbejdere = new ArrayList<>();

    public void addLager(Lager lager) {
        lagre.add(lager);
    }

    public ArrayList<Lager> getLagre() {
        return lagre;
    }

    public void addReol(Reol reol) {
        reoler.add(reol);
    }

    public ArrayList<Reol> getReoler() {
        return reoler;
    }

    public void addHylde(Hylde hylde) {
        hylder.add(hylde);
    }

    public ArrayList<Hylde> getHylder() {
        return hylder;
    }

    public void addFad(Fad fad) {
        fade.add(fad);
    }

    public ArrayList<Fad> getFade() {
        return fade;
    }

    public void addDestillering(Destillering d) {
        destilleringer.add(d);
    }

    public ArrayList<Destillering> getDestilleringer() {
        return destilleringer;
    }

    public void addDestillat(Destillat d) {
        destillater.add(d);
    }

    public ArrayList<Destillat> getDestillater() {
        return destillater;
    }

    public void addMaengdeDestilleret(MaengdeDestilleret m) {
        maengder.add(m);
    }

    public ArrayList<MaengdeDestilleret> getMaengder() {
        return maengder;
    }

    public void addWhiskyProdukt(WhiskyProdukt w) {
        whiskyProdukter.add(w);
    }

    public ArrayList<WhiskyProdukt> getWhiskyProdukter() {
        return whiskyProdukter;
    }

    public void addAftapning(Aftapning a) {
        aftapninger.add(a);
    }

    public ArrayList<Aftapning> getAftapninger() {
        return aftapninger;
    }

    public void addVandTilsaetning(VandTilsaetning v) {
        vandTilsaetninger.add(v);
    }

    public ArrayList<VandTilsaetning> getVandTilsaetninger() {
        return vandTilsaetninger;
    }

    public void addPaafyldning(Paafyldning p) {
        paafyldninger.add(p);
    }

    public ArrayList<Paafyldning> getPaafyldninger() {
        return paafyldninger;
    }

    public void addLagerMedarbejder(LagerMedarbejder m) {
        lagerMedarbejdere.add(m);
    }

    public ArrayList<LagerMedarbejder> getLagerMedarbejdere() {
        return lagerMedarbejdere;
    }

    @Override
    public void deleteFad(Fad fad) {
        fade.remove(fad);
    }

    @Override
    public void deleteProdukt(WhiskyProdukt whiskyProdukt) {
        whiskyProdukter.remove(whiskyProdukt);
    }
}
