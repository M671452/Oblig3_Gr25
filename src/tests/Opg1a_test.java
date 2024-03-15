package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import grensesnitt.MengdeADT;
import oppgaver.TabellMengde;

public class Opg1a_test {

    private TabellMengde<Integer> mengde;
    private TabellMengde<Integer> tomMengde;

    @BeforeEach
    void setUp() {
        mengde = new TabellMengde<>();
        tomMengde = new TabellMengde<>();

        mengde.leggTil(1);
        mengde.leggTil(2);
        mengde.leggTil(3);
    }

    @Test
    void testErTom() {
        assertTrue(tomMengde.erTom());
        assertFalse(mengde.erTom());
    }

    @Test
    void testInneholder() {
        assertTrue(mengde.inneholder(1));
        assertFalse(mengde.inneholder(4));
        assertFalse(mengde.inneholder(null));
    }
    
	@Test
	void testDelmengde() {

	    TabellMengde<Integer> annenMengde = new TabellMengde<>();
	    annenMengde.leggTil(1);
	    annenMengde.leggTil(2);
	    annenMengde.leggTil(3);
	    annenMengde.leggTil(4);
	    annenMengde.leggTil(5);

	    assertTrue(mengde.erDelmengdeAv(annenMengde));
	}

    @Test
    void testAntallElementer() {
        assertEquals(3, mengde.antallElementer());
    }

    @Test
    void testLeggTil() {
        mengde.leggTil(2);
        assertEquals(3, mengde.antallElementer());
        mengde.leggTil(5);
    }
    
    @Test
    void testLeggTilAlleFra() {
        TabellMengde<Integer> annenMengde = new TabellMengde<>();
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);

        mengde.leggTilAlleFra(annenMengde);

        assertTrue(mengde.inneholder(1));
        assertTrue(mengde.inneholder(2));
        assertTrue(mengde.inneholder(3));
        assertEquals(3, mengde.antallElementer());
    }

    @Test
    void testFjern() {
        mengde.fjern(2);
        assertFalse(mengde.inneholder(2));
        assertEquals(2, mengde.antallElementer());
    }

    @Test
    void testUnion() {
        TabellMengde<Integer> mengde1 = new TabellMengde<>();
        mengde1.leggTil(3);
        mengde1.leggTil(4);

        MengdeADT<Integer> union = mengde.union(mengde1);
        assertEquals(4, union.antallElementer());
        assertTrue(union.inneholder(1));
        assertTrue(union.inneholder(2));
        assertTrue(union.inneholder(3));
        assertTrue(union.inneholder(4));
    }

    @Test
    void testSnitt() {
        TabellMengde<Integer> annenMengde = new TabellMengde<>();
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);
        annenMengde.leggTil(4);

        MengdeADT<Integer> snittMengde = mengde.snitt(annenMengde);
        assertEquals(2, snittMengde.antallElementer());
        assertTrue(snittMengde.inneholder(2));
        assertTrue(snittMengde.inneholder(3));
    }

    @Test
    void testMinus() {
        TabellMengde<Integer> annenMengde = new TabellMengde<>();
        annenMengde.leggTil(2);
        annenMengde.leggTil(3);

        MengdeADT<Integer> minusMengde = mengde.minus(annenMengde);
        assertEquals(1, minusMengde.antallElementer());
        assertTrue(minusMengde.inneholder(1));
    }

    @Test
    void testErLik() {
        TabellMengde<Integer> mengde1 = new TabellMengde<>();
        mengde1.leggTil(1);
        mengde1.leggTil(2);
        mengde1.leggTil(3);
        
        TabellMengde<Integer> mengde2 = new TabellMengde<>();
        mengde2.leggTil(1);
        mengde2.leggTil(2);
        mengde2.leggTil(3);
        
        assertTrue(mengde1.erLik(mengde2));
        mengde1.leggTil(5);
        assertFalse(mengde1.erLik(mengde2));
        
        TabellMengde<Integer> tom1 = new TabellMengde<>();
        TabellMengde<Integer> tom2 = new TabellMengde<>();
        assertTrue(tom1.erLik(tom2));
    }

    @Test
    void testErDisjunkt() {
        TabellMengde<Integer> annenMengde = new TabellMengde<>();
        annenMengde.leggTil(4);
        annenMengde.leggTil(5);
        assertTrue(mengde.erDisjunkt(annenMengde));

        annenMengde.leggTil(1);
        assertFalse(mengde.erDisjunkt(annenMengde));
    }
}
