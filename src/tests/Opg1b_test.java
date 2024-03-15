package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import grensesnitt.MengdeADT;
import oppgaver.LenketMengde;

class Opg1b_test {

	private LenketMengde<Integer> mengde;
	private LenketMengde<Integer> tomMengde;
	
	@BeforeEach
	void setUp() {
		mengde = new LenketMengde<>();
		tomMengde = new LenketMengde<>();
		
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
	    }

	    @Test
	    void testDelmengde() {
	        LenketMengde<Integer> annenMengde = new LenketMengde<>();
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
	        mengde.leggTil(4);
	        assertEquals(4, mengde.antallElementer());
	        mengde.leggTil(4);
	        assertEquals(4, mengde.antallElementer());
	    }

	    @Test
	    void testLeggTilAlleFra() {
	        LenketMengde<Integer> annenMengde = new LenketMengde<>();
	        annenMengde.leggTil(4);
	        annenMengde.leggTil(5);

	        mengde.leggTilAlleFra(annenMengde);

	        assertTrue(mengde.inneholder(4));
	        assertTrue(mengde.inneholder(5));
	        assertEquals(5, mengde.antallElementer());
	    }

	    @Test
	    void testFjern() {
	        mengde.fjern(2);
	        assertFalse(mengde.inneholder(2));
	        assertEquals(2, mengde.antallElementer());
	    }

	    @Test
	    void testUnion() {
	        LenketMengde<Integer> annenMengde = new LenketMengde<>();
	        annenMengde.leggTil(4);
	        annenMengde.leggTil(5);

	        MengdeADT<Integer> union = mengde.union(annenMengde);
	        assertEquals(5, union.antallElementer());
	        assertTrue(union.inneholder(1));
	        assertTrue(union.inneholder(5));
	    }

	    @Test
	    void testSnitt() {
	        LenketMengde<Integer> annenMengde = new LenketMengde<>();
	        annenMengde.leggTil(3);
	        annenMengde.leggTil(4);

	        MengdeADT<Integer> snitt = mengde.snitt(annenMengde);
	        assertEquals(1, snitt.antallElementer());
	        assertTrue(snitt.inneholder(3));
	    }

	    @Test
	    void testMinus() {
	        LenketMengde<Integer> annenMengde = new LenketMengde<>();
	        annenMengde.leggTil(2);
	        annenMengde.leggTil(3);

	        MengdeADT<Integer> minus = mengde.minus(annenMengde);
	        assertEquals(1, minus.antallElementer());
	        assertTrue(minus.inneholder(1));
	    }

	    @Test
	    void testErLik() {
	        LenketMengde<Integer> mengde1 = new LenketMengde<>();
	        mengde1.leggTil(1);
	        mengde1.leggTil(2);
	        mengde1.leggTil(3);

	        LenketMengde<Integer> mengde2 = new LenketMengde<>();
	        mengde2.leggTil(3);
	        mengde2.leggTil(1);
	        mengde2.leggTil(2);

	        assertTrue(mengde1.erLik(mengde2));

	        mengde1.leggTil(4);
	        assertFalse(mengde1.erLik(mengde2));
	    }

	    @Test
	    void testErDisjunkt() {
	        LenketMengde<Integer> annenMengde = new LenketMengde<>();
	        annenMengde.leggTil(4);
	        annenMengde.leggTil(5);

	        assertTrue(mengde.erDisjunkt(annenMengde));

	        annenMengde.leggTil(1);
	        assertFalse(mengde.erDisjunkt(annenMengde));
	    }
	}
