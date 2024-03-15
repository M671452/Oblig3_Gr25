package oppgaver;

import java.util.HashSet;
import java.util.Set;
import grensesnitt.MengdeADT;

public class JavaSetToMengde<T> implements MengdeADT<T> {

	Set<T> mengde = new HashSet<>();
	
	@Override
	public boolean erTom() {
		return mengde.isEmpty();
	}

	@Override
	public boolean inneholder(T element) {
		return mengde.contains(element);
	}

	@Override
	public boolean erDelmengdeAv(MengdeADT<T> annenMengde) {
		 JavaSetToMengde<T> annen = (JavaSetToMengde<T>)annenMengde;
		if (annen.mengde.containsAll(this.mengde)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean erLik(MengdeADT<T> annenMengde) {
		if (annenMengde == null || annenMengde.antallElementer() != this.antallElementer()) {
	        return false;
	    }

	    for (T element : this.mengde) {
	        if (!annenMengde.inneholder(element)) {
	            return false;
	        }
	    }
	    return true;
	}

	@Override
	public boolean erDisjunkt(MengdeADT<T> annenMengde) {
		for (T element : mengde) {
			if (annenMengde.inneholder(element)) {
				return false;
		    }
		}
		return true;
	}

	@Override
	public MengdeADT<T> snitt(MengdeADT<T> annenMengde) {
		Set<T> resultat = new HashSet<>(mengde);
	    Set<T> annenSet = new HashSet<>();
	    
	    for (T element : annenMengde.tilTabell()) {
	        annenSet.add(element);
	    }
	    resultat.retainAll(annenSet);
	    JavaSetToMengde<T> snitt = new JavaSetToMengde<>();
	    snitt.mengde = resultat;
	    return snitt;
	}

	@Override
	public MengdeADT<T> union(MengdeADT<T> annenMengde) {
		Set<T> resultat = new HashSet<T>();
	    resultat.addAll(mengde);

	    T[] annenTabell = annenMengde.tilTabell();
	    for (T element : annenTabell) {
	        resultat.add(element);
	    }
	    JavaSetToMengde<T> union = new JavaSetToMengde<>();
	    union.mengde = resultat;
	    return union;
	}

	@Override
	public MengdeADT<T> minus(MengdeADT<T> annenMengde) {
		Set<T> resultat = new HashSet<T>(mengde);

	    T[] annenTabell = annenMengde.tilTabell();
	    for (T element : annenTabell) {
	        resultat.remove(element);
	    }
	    JavaSetToMengde<T> minus = new JavaSetToMengde<>();
	    minus.mengde = resultat;
	    return minus;
	}

	@Override
	public void leggTil(T element) {
		mengde.add(element);
	}

	@Override
	public void leggTilAlleFra(MengdeADT<T> annenMengde) {
	    T[] tabell = annenMengde.tilTabell();
	    for (T element : tabell) {
	        mengde.add(element);
	    }
	}

	@Override
	public T fjern(T element) {
		if (!mengde.contains(element)) {
			return null;
		}
		else {
		mengde.remove(element);
		return element;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] tilTabell() {
		T[] tabell = (T[]) new Object[mengde.size()];
		return mengde.toArray(tabell);
	}

	@Override
	public int antallElementer() {
		return mengde.size();
	}
}
