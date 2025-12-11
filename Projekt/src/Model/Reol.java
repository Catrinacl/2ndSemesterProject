package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Reol implements Serializable {
    private String reolId;
    private String reolType;
    private List<Hylde> hylder;
    private Lager lager;

    public Reol(String reolId, String reolType, Lager lager) {
        this.reolId = reolId;
        this.reolType = reolType;
        this.hylder = new ArrayList<>();
        this.lager = lager;
    }

    public String getReolId() {
        return reolId;
    }

    public List<Hylde> getHylder() {
        return hylder;
    }

    public Lager getLager() {
        return lager;
    }

    @Override
    public String toString() {
        int antalHylder = (hylder != null ? hylder.size() : 0);
        String lagerNavn = (lager != null ? lager.getLagerId() : "ingen lager");

        return reolId + " - " + reolType + " (" + antalHylder + " hylder, " + lagerNavn + ")";
    }
}
