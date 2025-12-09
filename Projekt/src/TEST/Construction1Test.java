package TEST;

import Model.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class Construction1Test {

    @Test
    void maengdeDestilleretStoresCorrectValues() {
        // Arrange
        Destillering d = new Destillering(
                "D1",
                LocalDate.of(2025, 1, 1),
                LocalDate.of(2025, 1, 3),
                "MB12",
                "Byg",
                "Ingen",
                "Testdestillering"
        );

        MaengdeDestilleret m = new MaengdeDestilleret(42.5, d);

        // Act + Assert
        assertEquals(42.5, m.getLiter());
        assertEquals(d, m.getDestillering());

        // toString bør også indeholde liter og destilleringId
        String tekst = m.toString();
        assertTrue(tekst.contains("42.5"));
        assertTrue(tekst.contains("D1"));
    }

    @Test
    void destilleringStoresBasicData() {
        // Arrange
        LocalDate start = LocalDate.of(2025, 2, 10);
        LocalDate slut = LocalDate.of(2025, 2, 12);

        Destillering d = new Destillering(
                "D2",
                start,
                slut,
                "MB99",
                "Hvede",
                "Tørv",
                "Kommentar"
        );

        // Assert
        assertEquals("D2", d.getDestilleringId());
        assertEquals(start, d.getStartDato());
        assertEquals(slut, d.getSlutDato());
        assertEquals("MB99", d.getMaltBatch());
        assertEquals("Hvede", d.getKornsort());
        assertEquals("Tørv", d.getRygemateriale());
        assertEquals("Kommentar", d.getKommentar());

        String tekst = d.toString();
        assertTrue(tekst.contains("D2"));
        assertTrue(tekst.contains("Hvede"));
    }

    @Test
    void destillatStoresDataAndMaengder() {
        // Arrange
        Destillering d = new Destillering(
                "D3",
                LocalDate.of(2025, 3, 1),
                LocalDate.of(2025, 3, 3),
                "MB50",
                "Byg",
                null,
                "Ingen kommentar"
        );

        MaengdeDestilleret m1 = new MaengdeDestilleret(10.0, d);
        MaengdeDestilleret m2 = new MaengdeDestilleret(15.5, d);

        Destillat dest = new Destillat(
                "DEST1",
                "NM-01",
                25.5,
                63.5
        );

        // Act
        dest.addMaengdeDestilleret(m1);
        dest.addMaengdeDestilleret(m2);

        // Assert basisdata
        assertEquals("DEST1", dest.getDestillatID());
        assertEquals("NM-01", dest.getNewMakeID());
        assertEquals(25.5, dest.getTotalmaengdeL());
        assertEquals(63.5, dest.getAlkoholPc());

        // Assert maengder-liste
        ArrayList<MaengdeDestilleret> liste = dest.getMaengdeDestilleret();
        assertEquals(2, liste.size());
        assertTrue(liste.contains(m1));
        assertTrue(liste.contains(m2));

        // getMaengderToString bør indeholde info om begge mængder
        String maengdeTekst = dest.getMaengderToString();
        assertTrue(maengdeTekst.contains("10.0"));
        assertTrue(maengdeTekst.contains("15.5"));
        assertTrue(maengdeTekst.contains("D3"));
    }

    @Test
    void fadWithoutPaafyldningerIsNotReadyForAftapning() {
        // Arrange
        Fad fad = new Fad(
                "F1",
                250.0,
                "Eg",
                "Rødvin",
                "Tomt",
                null // hylde = null er ok ifølge konstruktoren
        );

        // Act + Assert
        assertFalse(fad.erKlarTilAftapning());

        String tekst = fad.toString();
        assertTrue(tekst.contains("F1"));
        assertTrue(tekst.contains("Eg"));
    }
}
