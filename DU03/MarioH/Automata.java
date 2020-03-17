import java.util.Arrays;
import java.util.TreeSet;

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
	public Character[] alphabet() {
		if(delta == null) return null;
		TreeSet<Character> s = new TreeSet<Character>();

		for(Transit t : delta) {
			s.add(t.getSymbol());
		}

		Character[] result = new Character[s.toArray().length];
		s.toArray(result);

		return result;
	}
	/**
	 * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) stavov nachadzajucich sa v delta funkcii 
	 */
	public String[] states() {
		if(delta == null) return null;
		TreeSet<String> s = new TreeSet<String>();

		for(Transit t : delta) {
			s.add(t.getFromState());
			s.add(t.getToState());
		}

		String[] result = new String[s.toArray().length];
		s.toArray(result);

		return result;
	}
	/**
	 * @return - true, ak zodpoveda definicii konecneho automatu z prednasky UTI
	 * - pociatocny stav a vsetky koncove patria do mnoziny states()
	 * - prechodova funkcia je totalna funkcia, definovana jednoznacne pre kazdu dvojicu states() x alphabet() 
	 */	
	public boolean isCorrectFA() {
		if(initState == null || finalStates == null || delta == null ||  finalStates.length == 0) return false;

		for(String state: states()) {
			boolean contains = false;
			for(Character c: alphabet()) {
				for(Transit t: delta) {
					if(state == t.getFromState() && c == t.getSymbol()) contains = true;
				}
				if(!contains) return false;
			}
		}

		for(int i = 0; i < finalStates.length; i++) {
			if(finalStates[i] != null) break;
			if(i == finalStates.length - 1) return false;
		}

		TreeSet ts = new TreeSet();

		for(String s : states()) {
			ts.add(s);
		}

		for(int i = 0; i < finalStates.length; i++) {
			if(!ts.contains(finalStates[i])) return false;
		}
		if(!ts.contains(initState)) return false;

		return true;
	}
	/**
	 * predpokladajte, ze objekt splna podmienku isCorrectFA()
	 * @param word - vstupne slovo pozostavajuce z postupnosti symbolov
	 * @return - true, ak automat slovo akceptuje, inak false
	 */
	public boolean accepts(String word) {
		String state = initState;

		if(word != null)
		for(char c : word.toCharArray()) {
			for(int i = 0; i < delta.length; i++) {
				if(delta[i].getSymbol() == c && delta[i].getFromState() == state) {
					state = delta[i].getToState();//da sa efektivnejsie vyriesit
					break;
				}
				if(i == delta.length-1) return false;
			}
		}

		for(int i = 0; i < finalStates.length; i++) {
			if(state.equals(finalStates[i])) return true;
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
							new Transit("q0", 'a', "q1"),
							new Transit("q0", 'b', "q0"),
							new Transit("q1", 'a', "q1"),
							new Transit("q1", 'b', "q1")
							},
					new String[]{"q1"});// koncove stavy
		System.out.println(Arrays.asList(a.alphabet()));
		System.out.println(Arrays.asList(a.states()));
		System.out.println(a.isCorrectFA());
		System.out.println(a.accepts("a"));
	}
}
