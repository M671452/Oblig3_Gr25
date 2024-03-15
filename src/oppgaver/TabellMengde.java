package oppgaver;

import java.util.Arrays;
import grensesnitt.MengdeADT;

public class TabellMengde<T> implements grensesnitt.MengdeADT<T> {
	// Objektvariabler:
	private static final int KAPASITET = 10;
	private T[] tabell;
	private int antall;
	
	// Konstruktører:
	public TabellMengde() {
		this(KAPASITET);
	}
	
	@SuppressWarnings("unchecked")
	public TabellMengde(int kapasitet) {
		tabell = (T[]) new Object[kapasitet];
		antall = 0;
	}
	
	// Implementeringer:
	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		for (int i = 0; i < antall; i++) {
			T verdi = tabell[i];
			if (element == null) {
				return false;
			}
			if (element.equals(verdi)) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (!annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		if (antall != annenMengde.antallElementer()) {
			return false;
		}
		
		for (int i = 0; i < antall; i++) {
			if (!annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for (int i = 0; i < antall; i++) {
			if (annenMengde.inneholder(tabell[i])) {
				return false;
			}
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		TabellMengde<T> resultat =  new TabellMengde<>();
		for (int i = 0; i < tabell.length; i++) {
			if (annenMengde.inneholder(tabell[i]) ) {
				resultat.leggTil(tabell[i]);
			}
		}
		return resultat;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		TabellMengde<T> resultat = new TabellMengde<>();
		for (int i = 0; i < antall; i++) {
			resultat.leggTil(tabell[i]);
		}
		T[] elementer = annenMengde.tilTabell();
		for (T element : elementer) {
			resultat.leggTil(element);
		}
		return resultat;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		TabellMengde<T> resultat = new TabellMengde<>();
		for (int i = 0; i < antall; i++) {
			if (!annenMengde.inneholder(tabell[i])) {
				resultat.leggTil(tabell[i]);
			}
		}
		return resultat;
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			if (antall == tabell.length) {
				tabell = Arrays.copyOf(tabell, tabell.length*2);
			}
			tabell[antall] = element;
			antall++;
		}
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
		T[] elementer = annenMengde.tilTabell();
		for (T element : elementer) {
			leggTil(element);
		}
	}

	@Override
	public T fjern(T element) {
		if (!erTom()) {
			for (int i = 0; i < tabell.length; i++) {
				if (tabell[i].equals(element)) {
					T fjernet = tabell[i];
					tabell[i] = tabell[antall-1];
					tabell[antall-1] = null;
					antall--;
					return fjernet;
				}
			}
		}
		return null;
	}
	
	@Override
	public T[] tilTabell() {
		return Arrays.copyOf(tabell, antall);
	}

	@Override
	public int antallElementer() {
		return antall;
	}
}
