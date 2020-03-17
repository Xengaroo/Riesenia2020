import javax.swing.*;
import java.util.Arrays;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class Automata {
	private String initState;
	private Transit[] delta;
	private String[] finalStates;
	
	public Automata(String initState, Transit[] delta, String[] finalStates) {
		super();
		this.initState = initState;
		this.finalStates = finalStates;
		this.delta = delta;
	}
	/**
	 * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) symbolov 
	 * 			 nachadzajucich sa v delta funkcii 
	 */
	public Character[] alphabet() { /////////
		if (delta == null) return null;
		Character[] alphabet0 = new Character[delta.length];
		int nova_dlzka = 0;

		for (int i = 0; i < delta.length; i++) {
			if (delta[i] == null) continue;
			if (delta[i].getSymbol() == null) continue;

			for (int a = 0; a < delta.length; a++) {
				if (alphabet0[a] == null) {
					alphabet0[a] = delta[i].getSymbol();
					nova_dlzka++;
					break;
				}
				if (alphabet0[a] == delta[i].getSymbol()) break;
			}
		}
		//Character[] alphabet = new Character[nova_dlzka];
		Character[] alphabet = Arrays.copyOf(alphabet0,nova_dlzka);
		Arrays.sort(alphabet);
		return alphabet;
	}
	/**
	 * @return - lexikograficky usporiadanu mnoinu (neopakujucich sa) stavov nachadzajucich sa v delta funkcii
	 */
	public String[] states() {
		if (delta == null) return null;
		String[] states0 = new String[2*delta.length];
		int nova_dlzka = 0;

		for (int i = 0; i < delta.length; i++) {
			if (delta[i] == null) continue;
			if (delta[i].getFromState() != null) {
				for (int s = 0; s < delta.length; s++) {
					if (states0[s] == null) {
						states0[s] = delta[i].getFromState();
						nova_dlzka++;
						break;
					}
					if (states0[s].equals(delta[i].getFromState())) break;
				}
			}

			if (delta[i].getToState() != null) {
				for (int s2 = 0; s2 < delta.length; s2++) {
					if (states0[s2] == null) {
						states0[s2] = delta[i].getToState();
						nova_dlzka++;
						break;
					}
					if (states0[s2].equals(delta[i].getToState())) break;
				}
			}


		}

		String[] states = Arrays.copyOf(states0,nova_dlzka);
		Arrays.sort(states);
		return states;
	}
	/**
	 * @return - true, ak zodpoveda definicii konecneho automatu z prednasky UTI
	 * - pociatocny stav a vsetky koncove patria do mnoziny states()
	 * - prechodova funkcia je totalna funkcia, definovana jednoznacne pre kazdu dvojicu states() x alphabet() 
	 */	
	public boolean isCorrectFA() {
		if (initState == null) return false;
		if (finalStates == null) return false;
		if (delta == null) return false;
		if (delta.length == 0) return false;
		if (states() == null) return false;

		if (delta.length != states().length * alphabet().length) return false;

		for (int f = 0; f < finalStates.length; f++) {
			boolean nachadza_sa = false;
			for (int i = 0; i < states().length; i++) {
				if (finalStates[f] == null) return false;
				if (finalStates[f].equals(states()[i])) {
					nachadza_sa = true;
					continue;
				}
			}
			if (!nachadza_sa) {
				return false;
			}
		}

		for (int s = 0; s < states().length; s++) {
			if (initState.equals(states()[s])) break;
			if (s == states().length - 1) {
				if (!initState.equals(states()[s])) return false;
			}
		}

		for (int t = 0; t < delta.length; t++) {
			for (int t2 = 0; t2 < delta.length; t2++) {
				if (delta[t].getFromState().equals(delta[t2].getFromState())) {
					if (delta[t].getSymbol() == delta[t2].getSymbol()) {
						if (!delta[t].getToState().equals(delta[t2].getToState())) return false;
						if (delta[t].getToState().equals(delta[t2].getToState()) && t != t2) return false;
					}
				}
			}
		}

		return true;
	}
	/**
	 * predpokladajte, ze objekt splna podmienku isCorrectFA()
	 * @param word - vstupne slovo pozostavajuce z postupnosti symbolov
	 * @return - true, ak automat slovo akceptuje, inak false
	 */
	public boolean accepts(String word) {
		if (word == null) return false;
		if (word.equals("")) return false;

		for (int p = 0; p < word.length(); p++) {
			boolean nachadza_sa = false;
			for (Character a : alphabet()) {
				if (word.charAt(p) == a) {
					nachadza_sa = true;
					break;
				}
			}

			if (!nachadza_sa) return false;
		}

		if (delta == null) return false;
		if (finalStates == null) return false;
		String stav = initState;
		for (int w = 0; w < word.length(); w++) {
			for (int d = 0; d < delta.length; d++) {
				if (stav.equals(delta[d].getFromState()) && word.charAt(w) == delta[d].getSymbol()) {
					stav = delta[d].getToState();
					break;
				}
			}
		}

		for (int f = 0; f < finalStates.length; f++) {
			if (stav.equals(finalStates[f])) return true;
		}
		return false;
	}
	/**
	 * @return konecny automat akceptujuci binarne slova z 0 a 1 predstavujuce binarny zapis (v dvojkovej sustave) prvocisla < 256
	 */
	public static Automata prvocisla256() {
		return new Automata(null,  null,  null); // toto doprogramujte, ak riesite premiu
	}

	public static void main(String[] args) {
		Automata a = new Automata(
				"q0",			// pociatocny stav
				new Transit[]{	// delta
						new Transit("q0", '0', "q1"),
						new Transit("q0", '1', "q0"),
						new Transit("q1", '0', "q1"),
						new Transit("q1", '1', "q2"),
						new Transit("q2", '0', "q3"),
						new Transit("q2", '1', "q0"),
						new Transit("q3", '0', "q3"),
						new Transit("q3", '1', "q3")
				},
				new String[]{"q3"});// koncove stavy
		assertEquals("lavy obrazok v zadani", 4, a.states().length);
		assertEquals("lavy obrazok v zadani", 2, a.alphabet().length);
		assertEquals("lavy obrazok v zadani", true, a.isCorrectFA());
		assertEquals("lavy obrazok v zadani", true, a.accepts("010"));
		assertEquals("lavy obrazok v zadani", true, a.accepts("01010101010"));
		assertEquals("lavy obrazok v zadani", true, a.accepts("1111101010101010"));
		assertEquals("lavy obrazok v zadani", false, a.accepts("0000001111111"));
		assertEquals("lavy obrazok v zadani", true, a.accepts("000000111111100000000000000010000000000001111111111111000000000000000000000111111111111111111110000000000000"));

	}
}
