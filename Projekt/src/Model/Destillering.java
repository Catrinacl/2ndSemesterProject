package Model;

import java.time.LocalDate;
import java.util.List;

public class Destillering {
    private String destilleringId;
    private LocalDate startDato;
    private LocalDate slutDato;
    private String maltBatch;
    private String kornsort;
    private String rygemateriale; //nullable?
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
        return "Destillering{" +
                "destilleringId='" + destilleringId + '\'' +
                ", startDato=" + startDato +
                ", slutDato=" + slutDato +
                ", maltBatch='" + maltBatch + '\'' +
                ", kornsort='" + kornsort + '\'' +
                ", rygemateriale='" + rygemateriale + '\'' +
                ", kommentar='" + kommentar +
                '}';
    }
}
