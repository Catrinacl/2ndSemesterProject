package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Destillering implements Serializable {
    private String destilleringId;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private String rygemateriale;
    private String kommentar;

    public Destillering(String destilleringId, LocalDate startDato, LocalDate slutDato, String maltBatch,
                        String kornsort, String rygemateriale, String kommentar) {
        this.destilleringId = destilleringId;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.maltBatch = maltBatch;
        this.kornsort = kornsort;
        this.rygemateriale = rygemateriale;
        this.kommentar = kommentar;
    }

    public String getDestilleringId() {
        return destilleringId;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public String getMaltBatch() {
        return maltBatch;
    }

    public String getKornsort() {
        return kornsort;
    }

    public String getRygemateriale() {
        return rygemateriale;
    }

    public String getKommentar() {
        return kommentar;
    }


    @Override
    public String toString() {
        return destilleringId + " – " + kornsort + " (" + startDato + " til " + slutDato + ")";
    }
}
