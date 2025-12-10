package Model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FadTest {

    private Hylde opretHyldeH1() {
        return new Hylde("H1", 10, "øverst", "standard", new ArrayList<>(), null);
    }

    private Destillat opretDestillat(String id) {
        return new Destillat(id, "NM-01", 100.0, 63.5);
    }

    private LagerMedarbejder opretMedarbejder() {
        return new LagerMedarbejder("M1", "Test Medarbejder");
    }

    private Paafyldning opretPaafyldning(Fad fad, LocalDate dato, String destillatId) {
        Destillat destillat = opretDestillat(destillatId);
        LagerMedarbejder m = opretMedarbejder();
        return new Paafyldning(
                "p-" + destillatId,
                50.0,
                63.0,
                dato,
                m,
                fad,
                destillat
        );
    }

    private Fad opretStandardFad(String fadId) {
        Hylde h = opretHyldeH1();
        return new Fad(
                fadId,
                200.0,
                "Oak",
                "Bourbon",
                "ledig",
                h
        );
    }

    // følger arrange / act / assert logikken
    @Test
    void getKlarTilAftapningTekst() {
        Fad fad = opretStandardFad("F1");

        assertFalse(fad.erKlarTilAftapning());
        assertEquals("Nej", fad.getKlarTilAftapningTekst());

        Paafyldning p = opretPaafyldning(fad, LocalDate.now().minusYears(4), "D1");
        fad.addPaafyldning(p);

        assertTrue(fad.erKlarTilAftapning());
        assertEquals("Ja", fad.getKlarTilAftapningTekst());
    }

    @Test
    void testToString() {
        // EC1
        Fad fadMedHylde = opretStandardFad("F2");
        fadMedHylde.addPaafyldning(
                opretPaafyldning(fadMedHylde, LocalDate.now().minusYears(3), "D1"));
        fadMedHylde.addPaafyldning(
                opretPaafyldning(fadMedHylde, LocalDate.now().minusYears(2), "D2"));

        String tekst1 = fadMedHylde.toString();
        assertTrue(tekst1.contains("H1"));
        assertTrue(tekst1.contains("2 påfyldnnger"));

        Fad fadUdenHylde = new Fad(
                "F3",
                200.0,
                "Oak",
                "Bourbon",
                "ledig",
                null
        );

        String tekst2 = fadUdenHylde.toString();
        assertTrue(tekst2.contains("ingen hyde"));
        assertTrue(tekst2.contains("0 påfyldniner"));
    }

    @Test
    void getDestillatID() {
        Fad fad = opretStandardFad("F4");

        //TC1
        assertEquals("", fad.getDestillatID());

        //TC2
        fad.addPaafyldning(
                opretPaafyldning(fad, LocalDate.now().minusYears(3), "D1"));
        assertEquals("D1",fad.getDestillatID());

        //TC3
        fad.addPaafyldning(
                opretPaafyldning(fad, LocalDate.now().minusYears(2), "D2"));
        fad.addPaafyldning(
                opretPaafyldning(fad, LocalDate.now().minusYears(1), "D3"));

        assertEquals("D1, D2, D3", fad.getDestillatID());
    }
}