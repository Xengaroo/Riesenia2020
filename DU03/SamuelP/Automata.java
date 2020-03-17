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
		int index = 0;
		int velkostpola = 0;
		int pohyb = 0;
		Character[] pole = new Character[20];

		for (Transit pom:delta){

			for (int i=0; i < pole.length;i++){
				if (pole[i] == pom.getSymbol()){
					pohyb = 1;

				}

			}

			if (pohyb == 0){
				pole[index] = pom.getSymbol();
				index++;
				velkostpola++;
			}
			pohyb = 0;
		}

																												//while (pole[index] != '\0'){
																												//	velkostpola++;
																												//	index++;
		//
		Character[] vysledok = new Character[velkostpola];
		for (int k = 0; k < velkostpola; k++){
			vysledok[k] = pole[k];
		}

		return vysledok; // toto doprogramujte
	}
	/**
	 * @return - lexikograficky usporiadanu mnozinu (neopakujucich sa) stavov nachadzajucich sa v delta funkcii 
	 */
	public String[] states() {
		int index = 0;
		int velkostpola = 0;
		int pohyb = 0;

		String[] pole = new String[20];

		for (Transit pom:delta){


			for (int i=0; i < pole.length;i++){
				if (pole[i] == pom.getFromState()){
					pohyb = 1;
				}
				if (pole[i] == pom.getToState()){
					pohyb = 1;

				}

			}

			if (pohyb == 0){
				pole[index] = pom.getFromState();
				index++;
				velkostpola++;
			}
			if (pohyb == 0){
				pole[index] = pom.getToState();
				index++;
				velkostpola++;
			}
			pohyb = 0;
		}

		//while (pole[index] != '\0'){
		//	velkostpola++;
		//	index++;
		// asi to nebude fungovat ak su zaciatocne stavy ine ako koncove...
		String[] vysledok = new String[velkostpola];
		for (int k = 0; k < velkostpola; k++){
			vysledok[k] = pole[k];
		}

		return vysledok; // toto doprogramujte
	}
	/**
	 * @return - true, ak zodpoveda definicii konecneho automatu z prednasky UTI
	 * - pociatocny stav a vsetky koncove patria do mnoziny states()
	 * - prechodova funkcia je totalna funkcia, definovana jednoznacne pre kazdu dvojicu states() x alphabet() 
	 */	
	public boolean isCorrectFA() {
		String from = "";
		String to = "";
		int a = 0;
		int koniecjeok = 0;
		int nieco = 0;
		int zaciatokjeok = 0;
		if (initState == null){
			return false;
		}
		if (finalStates == null){
			return false;
		}
		if (delta == null){
			return false;
		}



		String[] pole = states();
		for (Transit pom:delta) {
			from = pom.getFromState();
			to = pom.getToState();
			if (from != ""){
				nieco = 1;
			}

			if (a == 0){
				if (pom.getFromState().equals(initState)){
					zaciatokjeok = 1;
				}
				a++;
			}
			//if ((pom.getToState()).equals(finalStates)){
		//		koniecjeok = 1;
		//	}                                            tento if z nejakeho neznameho dovodu nefunguje...

		}

		for(String prvky:finalStates){
			for(String prvok:pole){
				if(prvky == prvok){
					koniecjeok = 1;
				}
			}
		}
			int index = 0;
			int spolocne = 0;
			int spolu =0;
			int prvkysuok = 0;

			Character[] field = new Character[20];

			for (Transit pomocna:delta){
				field[index] = pomocna.getSymbol();
				index++;
			}

			for (int k = 0; k < index; k++){
				for (int j = 1; j < index-1;j++){
					if (field[k] == field[j]){
						spolocne++;
					}
					if (field[k] != field[j]){
						spolu++;
					}
				}
			}
			if (spolu == spolocne){
				prvkysuok = 1;
			}

		if ((zaciatokjeok == 1) && (nieco == 1) && (koniecjeok == 1) && (prvkysuok == 1)){
			return true;
		}

		return false; // toto doprogramujte
	}
	/**
	 * predpokladajte, ze objekt splna podmienku isCorrectFA()
	 * @param word - vstupne slovo pozostavajuce z postupnosti symbolov
	 * @return - true, ak automat slovo akceptuje, inak false
	 */
	public boolean accepts(String word) {
		if (word == null){
			return false;
		}
		if (word == ""){
			return false;
		}
		int tot = 0;
		int raz = 0;
		String pokial = "";
		String[] pole = states();
		for (int i = 0; i < word.length();i++){
			tot = 0;
			for (Transit pom:delta) {
				if (word.charAt(i) == pom.getSymbol()){
					tot = 1;
				}
			}
			if (tot == 0){
				return false;
			}
		}
		for (int i = 0; i < word.length();i++){
			for (Transit pom:delta) {
				if (raz == 0){
					pokial = initState;
					raz++;
				}
				if ((pom.getFromState().equals(pokial)) && (pom.getSymbol().equals(word.charAt(i)))){
					pokial = pom.getToState();
					break;
				}
			}

		}
		for(String q:finalStates) {
			if (pokial.equals(q)) {
				return true;
			}
		}
		return false; // toto doprogramujte
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
