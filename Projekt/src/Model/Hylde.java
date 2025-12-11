package Model;

import java.io.Serializable;
import java.util.List;

public class Hylde implements Serializable {
    private String hyldeId;
    private int kapacitet;
    private String placering;
    private String hyldeType;
    private List<Fad> fade;
    private Reol reol;

    public Hylde(String hyldeId, int kapacitet, String placering, String hyldeType,
                 List<Fad> fade, Reol reol) {
        this.hyldeId = hyldeId;
        this.kapacitet = kapacitet;
        this.placering = placering;
        this.hyldeType = hyldeType;
        this.fade = fade;
        this.reol = reol;
    }

    public String getHyldeId() {
        return hyldeId;
    }

    public List<Fad> getFade() {
        return fade;
    }

    public Reol getReol() {
        return reol;
    }

    @Override
    public String toString() {
        int antalFade = (fade != null ? fade.size() : 0);
        String reolNavn = (reol != null ? reol.getReolId() : "ingen reol");

        return hyldeId + " - " + hyldeType +
                " (" + antalFade + "/" + kapacitet + " fade, placering: " + placering + ", reolnavn: " + reolNavn + ")";
    }
}
