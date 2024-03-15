package oppgaver;

import grensesnitt.MengdeADT;

public class LenketMengde<T> implements MengdeADT<T> {
	private Node<T> start;
	private int antall;
	
	private static class Node<T> {
		private T element;
		private Node<T> neste;
		
		public Node(T element, Node<T> neste) {
			this.element = element;
			this.neste = neste;
		}
	}
	
	public LenketMengde() {
		start = null;
		antall = 0;
	}
	
	@Override
	public boolean erTom() {
		return antall == 0;
	}

	@Override
	public boolean inneholder(T element) {
		Node<T> aktuell = start;
		while (aktuell != null) {
			if(aktuell.element.equals(element)) {
				return true;
			}
			aktuell = aktuell.neste;
		}
		return false;
	}
	
	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (!annenMengde.inneholder(aktuell.element)) {
				return false;
			}
			aktuell = aktuell.neste;
		}
		return true;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		if (antallElementer() != annenMengde.antallElementer()) {
			return false;
		}
		boolean svar = this.erDelmengdeAv(annenMengde) && annenMengde.erDelmengdeAv(this);
		return svar;
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (annenMengde.inneholder(aktuell.element)) {
				return false;
			}
			aktuell = aktuell.neste;
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		LenketMengde<T> resultat = new LenketMengde<>();
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (annenMengde.inneholder(aktuell.element)) {
				resultat.leggTil(aktuell.element);
			}
			aktuell = aktuell.neste;
		}
		return resultat;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		LenketMengde<T> resultat = new LenketMengde<>();
		Node<T> aktuell = start;
		while (aktuell != null) {
			resultat.leggTil(aktuell.element);
			aktuell = aktuell.neste;
		}
		T[] elementer = annenMengde.tilTabell();
		for(T element : elementer) {
			resultat.leggTil(element);
		}
		return resultat;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		LenketMengde<T> resultat = new LenketMengde<>();
		Node<T> aktuell = start;
		while (aktuell != null) {
			if (!annenMengde.inneholder(aktuell.element)) {
				resultat.leggTil(aktuell.element);
			}
			aktuell = aktuell.neste;
		}
		return resultat;
	}

	@Override
	public void leggTil(T element) {
		if (!inneholder(element)) {
			Node<T> node = new Node<>(element, start);
			start = node;
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
		Node<T> forrige = null;
		Node<T> aktuell = start;
		
		while (aktuell != null) {
			if(aktuell.element.equals(element)) {
				if (forrige == null) {
					start = aktuell.neste;
				}
				else {
					forrige.neste = aktuell.neste;
				}
				antall--;
				return aktuell.element;
			}
			forrige = aktuell;
			aktuell = aktuell.neste;
		}
		return null;
	}
	
	@Override
	public T[] tilTabell() {
		@SuppressWarnings("unchecked")
		T[] tabell = (T[]) new Object[antall];
		Node<T> aktuell = start;
		int i = 0;
		
		while (aktuell != null) {
			tabell[i++] = aktuell.element;
			aktuell = aktuell.neste;
		}
		return tabell;
	}

	@Override
	public int antallElementer() {
		return antall;
	}
}
