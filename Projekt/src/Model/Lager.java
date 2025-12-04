package Model;

import java.util.ArrayList;
import java.util.List;

public class Lager {
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

    public String getLagerType() {
        return lagerType;
    }

    public String getAdresse() {
        return adresse;
    }

    public List<Reol> getReoler() {
        return reoler;
    }

    public void addReol(Reol reol) {
        this.reoler.add(reol);
    }


    @Override
    public String toString() {
        return "Lager{" +
                "lagerId='" + lagerId + '\'' +
                ", lagerType='" + lagerType + '\'' +
                ", adresse='" + adresse + '\'' +
                ", reoler=" + reoler +
                '}';
    }
}
