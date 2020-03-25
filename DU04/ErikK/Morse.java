import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Morse {
	private static List<String> ret;
	private static String s;
	static String[] letters = new String[255];
	static {
			letters['A'] = ".-";		
			letters['B'] = "-...";	
			letters['C'] = "-.-.";	
			letters['D'] = "-..";		
			letters['E'] = ".";		  
			letters['F'] = "..-.";	
			letters['G'] = "--.";		
			letters['H'] = "....";	
			letters['I'] = "..";		
			letters['J'] = ".---";	
			letters['K'] = "-.-";		
			letters['L'] = ".-..";	
			letters['M'] = "--";		
			letters['N'] = "-.";		
			letters['O'] = "---";		
			letters['P'] = ".--.";	
			letters['Q'] = "--.-";	
			letters['R'] = ".-.";		
			letters['S'] = "...";		
			letters['T'] = "-";		  
			letters['U'] = "..-";		
			letters['V'] = "...-";	
			letters['W'] = ".--";		
			letters['X'] = "-..-";	
			letters['Y'] = "-.--";	
			letters['Z'] = "--..";
	}      
	//------------------------------------------------------ dopisujte odtialto nizsie

	/**
	 * @param anglickaSprava - retazec pismen anglickej abecedy 'A'-'Z' a medzier
	 * @return - preklad do morseho kodu, jednotlive pismena 'A'-'Z' su oddelene jednou mezerou, vstupne medzery sa ignoruju
	 */
	public static String koduj(String anglickaSprava) {
		if (anglickaSprava == null){
			return "";
		}
		String ret = "";
		for (int i = 0; i < anglickaSprava.length(); i++) {
			if (letters[anglickaSprava.charAt(i)] != null){
				if (ret != ""){
					ret += " ";
				}
				ret += letters[anglickaSprava.charAt(i)];
			}
		}
		return ret;
	}

	/**
	 * dekoduje stream Morseho symbolov oddelenych aspon nejakymi medzerami
	 */
	public static String dekoduj(String sprava) {
		if (sprava == null){
			return "";
		}
		String ret = "";
		String[] spravaN = sprava.split(" ");
		for (String s: spravaN) {
			s = s.trim();
			if (s.equals("")){
				continue;
			}
			boolean is = false;
			for (char i = 'A'; i <= 'Z'; i++) {
				if (letters[i].equals(s)){
					ret += i;
					is = true;
					break;
				}
			}
			if (!is){
				return null;
			}
		}
		return ret;
	}

	/**
	 * inverzny homomorfizmus - dekoduje stream Morseho symbolov neoddelenych medzerami, vsetky moznosti
	 */
	public static String[] dekodujVsetky(String sprava) {
		ret = new ArrayList<>();
		s = sprava;
		rek(new String(), new String(), 0);
		return ret.toArray(String[]::new);
	}

	public static void rek(String s1, String s2, int i){
		if (s1.length() >= 5){
			return;
		}
		if (i == s.length()){
			if (s1.equals("")) {
				ret.add(s2);
			}
			return;
		}
		s1 = s1 + s.charAt(i);
		for (char j = 'A'; j <= 'Z'; j++) {
			if (letters[j].equals(s1)){
				rek("", s2 + j, i + 1);
				break;
			}
		}
		rek(s1, s2,i + 1);
	}
}
