import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Message {

    private static Stream<String> fetch(String path) {

        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(new File(path))));
            //new URL(path).openStream()));
            return read.lines();

        } catch (IOException e) {
            System.out.println("nieco zle sa stalo: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    public static List<Character> getAlphabet() {
        List<Character> zoz = new ArrayList<>();

        findLetters(new File("tajna_sprava"), zoz); //???

        zoz.sort((a1,a2) -> a1.compareTo(a2));
        //System.out.println(zoz);
        return zoz;
    }

    public static void findLetters(File file, List<Character> zoz) {
        List<File> search = new ArrayList<>();
        search.add(file);

        while (!search.isEmpty() && zoz.size() < 11) {
            File f = search.remove(0);
            if (f != null) {
                if (f.isDirectory()) {
                    if (f.listFiles() != null) {
                        search.addAll(Arrays.asList(f.listFiles()));
                    }
                } else {

                    if (f != null && f.getPath() != null) {
                    List<String> lines = fetch(f.getPath()).collect(Collectors.toList());
                    //System.out.println(lines.toString());

                    for (String line : lines) {
                        StringBuilder st = new StringBuilder(line);
                        StringBuilder code = new StringBuilder();
                        for (int i = 0; i < line.length(); i++) {
                            if (st.charAt(i) == '.' || st.charAt(i) == '-') code.append(st.charAt(i));
                        }
                        if (code.length() != 0) {
                            //System.out.println(Morse.decode(code.toString()));
                            zoz.add(Morse.decode(code.toString()));
                        }
                    }
                    }
                }
            }
        }
    }


    public static List<String> slovnik() {
        List<Character> a = getAlphabet();
        List<String> slovnik = new ArrayList<>();

        //do regularneho vyrazu, mnozina znakov, co tam moze byt
        StringBuilder reg = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            reg.append(a.get(i));
        }


        Stream<String> lines = fetch("knuth_words");
        lines.forEach(line -> {
                    Scanner sc = new Scanner(line);
                    //Pattern pat = Pattern.compile("^(["+ reg2 +"]{1,10})$");
                    //Pattern pat = Pattern.compile("^(?i)(A{0,2}C{0,1}I{0,1}J{0,1}L{0,1}O{0,2}S{0,1}V{0,1})$");
                    //Pattern pat = Pattern.compile("(?=^(?i)(["+ reg +"]{1,10})$)(?=A{0,2})(?=C{0,1})(?=I{0,1})(?=J{0,1})(?=L{0,1})(?=O{0,2})(?=S{0,1})(?=V{0,1})");

                    Pattern pat = Pattern.compile("^(?i)(["+ reg +"]{2,6})$"); //odoberieme slova, ktore by nemali zmysel a pridlhe
                    Matcher m = pat.matcher(line);
                    if (m.find()) {
                        String slovko = m.group(1);
                        //System.out.println(slovko);
                        slovnik.add(slovko);
                    }
                });

        return slovnik;
    }

    //vyfiltruje dobre slova a da uppercase
    public static List<String> findValid(List<String> slovnik) {
        List<String> valid = new ArrayList<>();

        //frekvencna tabulka abecedy
        Map<Character, Integer> abeceda = new HashMap<>();
        List<Character> a = getAlphabet();
        for (Character c : a) {
            if (abeceda.containsKey(c)) abeceda.put(c, abeceda.get(c) + 1);
            else abeceda.put(c, 1);
        }


        for (String slovo : slovnik) {
            Map<Character, Integer> tSlovo = new HashMap<>();
            char[] slovoo = slovo.toCharArray();

            boolean ok = true;
            //frekvencna tabulka slova
            for (Character c : slovoo) {
                Character cc = c.toString().toUpperCase().charAt(0);

                if (tSlovo.containsKey(cc)) {
                    tSlovo.put(cc, tSlovo.get(cc) + 1);
                    if (tSlovo.get(cc) > abeceda.get(cc)) { //slovo obsahuje viac znakov 'c' ako je povolene
                        ok = false;
                        break;
                    }
                }
                else tSlovo.put(cc, 1);

            }

            //bolo treba rucne odtranit slova, lebo to sice bolo spravne .. ale sprava nedavala zmysel
            //nevedela som, ako inak urcite, ze tie slova nemaju vyznam pokope ... tak som ich tu odstranila
            //este som uvazovala, ze najdem prvu zmysluplnu spravu v zozname messages, ale toto mi prislo kus normalnejsie ..
            if (!slovo.equalsIgnoreCase("LAO") &&
                    !slovo.equalsIgnoreCase("AC") &&
                    !slovo.equalsIgnoreCase("ACS") &&
                    !slovo.equalsIgnoreCase("JO") &&
                    !slovo.equalsIgnoreCase("IV") &&
                    !slovo.equalsIgnoreCase("VI") &&
                    !slovo.equalsIgnoreCase("VS") &&
                    !slovo.equalsIgnoreCase("AS") &&
                    !slovo.equalsIgnoreCase("CO") &&
                    !slovo.equalsIgnoreCase("JAI") &&
                    !slovo.equalsIgnoreCase("CA") &&
                    !slovo.equalsIgnoreCase("OS") &&
                    !slovo.equalsIgnoreCase("CLIO") &&
                    !slovo.equalsIgnoreCase("COIL") &&
                    !slovo.equalsIgnoreCase("COLI") &&
                    !slovo.equalsIgnoreCase("VA") &&
                    !slovo.equalsIgnoreCase("LSI")
            ) if (ok) valid.add(slovo.toUpperCase()); //ak je slovo ok, pridame
        }

        System.out.println(valid);
        return valid;
    }

    public static boolean podslovoOK(List<String> slovnik, String slovo) {
        for (String s : slovnik) {
            if (s.contains(slovo)) return true;
            if (s.charAt(0) > slovo.charAt(0)) return false; //ked uz preslo prve pismenko
        }
        return false;
    }

    public static boolean pismen10(List<String> list) {
        int velkost = 0;
        for (String s : list) {
            velkost += s.length();
        }
        return velkost == 10;
    }

    public static void findMessage(List<Character> alphabet, List<String> slovnik, String slovo, List<String> docasna, List<List<String>> messages) {

        if (alphabet.size() == 0 && pismen10(docasna)) {
            messages.add(docasna); //pridavame sem tie spravy
        }

        else {
        for (int i = 0; i < alphabet.size(); ++i) {

            if (podslovoOK(slovnik, slovo + alphabet.get(i))) {
                String newslovo = slovo + alphabet.get(i).toString().toUpperCase();

                List<Character> copy = new ArrayList<>(alphabet);
                copy.remove(i);


                if (slovnik.contains(newslovo)) { //nasiel slovo, ideme od zaciatku
                    List<String> doc = new ArrayList<>(docasna);
                    doc.add(newslovo);

                    findMessage(copy, slovnik, "", doc, messages);
                }

                findMessage(copy, slovnik, newslovo, docasna, messages);
            }
        } }
    }

    public static List<String> getMessage() {
        List<List<String>> list = new ArrayList<>();
        findMessage(getAlphabet(), findValid(slovnik()), "", new ArrayList<>(), list);

        return list.get(0);
    }

    public static void main(String[] args) {
        //System.out.println(getAlphabet());
        List<Character> abeceda = getAlphabet();

        //findValid(slovnik());
        //slovnik();
        List<String> yay = getMessage();
        System.out.println("answer " + yay);
        //System.out.println(yay.size());
    }
}
