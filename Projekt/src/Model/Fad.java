package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad implements Serializable {
    private String fadId;
    private double stoerrelseL;
    private String traeType;
    private String tidligereIndhold;
    private String status;
    private List<Paafyldning> paafyldninger;
    private Hylde hylde;

    public Fad(String fadId, double stoerrelseL, String traeType, String tidligereIndhold,
               String status, Hylde hylde) {
        this.fadId = fadId;
        this.stoerrelseL = stoerrelseL;
        this.traeType = traeType;
        this.tidligereIndhold = tidligereIndhold;
        this.status = status;
        this.paafyldninger = new ArrayList<>();
        this.hylde = hylde;
    }

    public String getFadId() {
        return fadId;
    }

    public double getStoerrelseL() {
        return stoerrelseL;
    }

    public String getTraeType() {
        return traeType;
    }

    public String getTidligereIndhold() {
        return tidligereIndhold;
    }

    public String getStatus() {
        return status;
    }

    public List<Paafyldning> getPaafyldninger() {
        return paafyldninger;
    }

    public Hylde getHylde() {
        return hylde;
    }


    public void setStoerrelseL(double stoerrelseL) {
        this.stoerrelseL = stoerrelseL;
    }

    public void setTraeType(String traeType) {
        this.traeType = traeType;
    }

    public void setTidligereIndhold(String tidligereIndhold) {
        this.tidligereIndhold = tidligereIndhold;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addPaafyldning(Paafyldning p) {
        paafyldninger.add(p);
    }

    public boolean erKlarTilAftapning() {
        if (paafyldninger == null || paafyldninger.isEmpty()) {
            return false;
        }
        LocalDate earliest = paafyldninger.get(0).getDato();
        for (Paafyldning p : paafyldninger) {
            if (p.getDato().isBefore(earliest)) {
                earliest = p.getDato();
            }
        }
        LocalDate treAarDato = earliest.plusYears(3);

        return !treAarDato.isAfter(LocalDate.now());
    }

    public String getKlarTilAftapningTekst() {
        return erKlarTilAftapning() ? "Ja" : "Nej";
    }

    @Override
    public String toString() {
       String hyldeNavn = (hylde != null ? hylde.getHyldeId() : "ingen hylde");
       int antalPaafyld = (paafyldninger != null ? paafyldninger.size() : 0);

       return fadId + " - " + traeType + " (" + stoerrelseL + "L, "
               + antalPaafyld + " påfyldninger, " + hyldeNavn + ")";
    }

    public String getDestillatID() {
        if (paafyldninger == null || paafyldninger.isEmpty()) return "";
        String result = "";
        for (Paafyldning p : paafyldninger) {
            if (!result.isEmpty()) result += ", ";
            result += p.getDestillat().getDestillatID();
        }
        return result;
    }


}
