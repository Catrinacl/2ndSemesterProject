package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Lager implements Serializable {
    private String lagerId;
    private String lagerType;
    private String adresse;
    private List<Reol> reoler;

    public Lager(String lagerId, String lagerType, String adresse) {
        this.lagerId = lagerId;
        this.lagerType = lagerType;
        this.adresse = adresse;
        this.reoler = new ArrayList<>();
    }

    public String getLagerId() {
        return lagerId;
    }

    public List<Reol> getReoler() {
        return reoler;
    }

    public void addReol(Reol reol) {
        this.reoler.add(reol);
    }

    @Override
    public String toString() {
        int antalReoler = (reoler != null ? reoler.size() : 0);
        return lagerId + " - " + lagerType + " - " + adresse +
                " (" + antalReoler + " reoler) ";
    }
}
