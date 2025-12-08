package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Fad {
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

    public void addPaafyldning(Paafyldning p) {
        paafyldninger.add(p);
    }

    public boolean erKlarTilAftapning() {
        if (paafyldninger == null || paafyldninger.isEmpty()) {
            return false;
        }

        //finder den tidligste dato
        LocalDate earliest = paafyldninger.get(0).getDato();
        for (Paafyldning p : paafyldninger) {
            if (p.getDato().isBefore(earliest)) {
                earliest = p.getDato();
            }
        }

        // beregner datoen hvor fader er 3år gammel
        LocalDate treAarDato = earliest.plusYears(3);

        //klar til aftapning hvis treAarDato er før || samme dag som i dag
        return !treAarDato.isAfter(LocalDate.now());
    }

    @Override
    public String toString() {
       String hyldeNavn = (hylde != null ? hylde.getHyldeId() : "ingen hylde");
       int antalPaafyld = (paafyldninger != null ? paafyldninger.size() : 0);

       return fadId + " - " + traeType + " (" + stoerrelseL + "L, "
               + antalPaafyld + " påfyldninger, " + hyldeNavn + ")";
    }

    public String getDestillatID() {
        if (paafyldninger == null || paafyldninger.isEmpty()) {
            return "Ingen destillat";
        }

        String destId = "";
        for (int i = 0; i < paafyldninger.size(); i++) {
            destId += paafyldninger.get(i).getPaafyldningsId(); // Eller getDestillat().getDestillatID()
            if (i < paafyldninger.size() - 1) {
                destId += ", ";
            }
        }
        return destId;
    }
}
