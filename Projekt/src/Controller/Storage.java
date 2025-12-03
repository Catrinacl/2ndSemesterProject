package Controller;

import Model.*;
import java.util.ArrayList;

public interface Storage {

    ArrayList<Lager> getLagre();
    void addLager(Lager lager);

    ArrayList<Reol> getReoler();
    void addReol(Reol reol);

    ArrayList<Hylde> getHylder();
    void addHylde(Hylde hylde);

    ArrayList<Fad> getFade();
    void addFad(Fad fad);


    ArrayList<Destillering> getDestilleringer();
    void addDestillering(Destillering destillering);

    ArrayList<Destillat> getDestillater();
    void addDestillat(Destillat destillat);

    ArrayList<MaengdeDestilleret> getMaengder();
    void addMaengdeDestilleret(MaengdeDestilleret m);


    ArrayList<WhiskyProdukt> getWhiskyProdukter();
    void addWhiskyProdukt(WhiskyProdukt produkt);

    ArrayList<Aftapning> getAftapninger();
    void addAftapning(Aftapning a);

    ArrayList<VandTilsaetning> getVandTilsaetninger();
    void addVandTilsaetning(VandTilsaetning v);


    ArrayList<Paafyldning> getPaafyldninger();
    void addPaafyldning(Paafyldning p);

    ArrayList<LagerMedarbejder> getLagerMedarbejdere();
    void addLagerMedarbejder(LagerMedarbejder m);
}
