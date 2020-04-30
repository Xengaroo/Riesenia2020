import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Message {
    static class FilterAdresara implements FilenameFilter {
        public boolean accept(File dir, String name) {
            File f = new File(dir, name);
            return f.isDirectory(); // zaujímajú ma len adresáre
        }
    }

    static class FilterSuboru implements FilenameFilter {
        public boolean accept(File dir, String name) {
            File f = new File(dir, name);
            return f.isFile(); // zaujímajú ma len subory
        }
    }

    public static List<Character> getAlphabet() {
        try {
            List<Character> res = getAlphabet("tajna-sprava");
            System.out.println(res);
            return res;
        } catch (FileNotFoundException e ) {
            System.out.println("Chybka");
        }
        return null;
    }

    public static List<Character> getAlphabet(String aktualnyAdr) throws FileNotFoundException {
        String[] priecinky;
        File[] subory;
        List<Character> chars = new ArrayList<>();

        File aktDir = new File(aktualnyAdr);
        FilterAdresara FilterAdr = new FilterAdresara();
        FilterSuboru FilterSub = new FilterSuboru();
        priecinky = aktDir.list(FilterAdr);
        subory = aktDir.listFiles(FilterSub);

        if (subory != null) {
            for (File f : subory) {
                Scanner scan = new Scanner(new FileInputStream(f));
                while (scan.hasNextLine()) {
                    String line = "";
                        String scanned = scan.findInLine("[/.-]+");
                        if (scanned != null) {
                            Character morseChar = Morse.decode(scanned);
                            chars.add(morseChar);
                        }
                        if (scan.hasNextLine()) {
                            scan.nextLine();
                        }

                }
            }
        }

        if (priecinky != null) {
            for (int i = 0; i < priecinky.length; i++) {
                if (chars.size() != 10)
                    chars.addAll(getAlphabet(aktualnyAdr + File.separator + priecinky[i]));
                else break;
            }
        }


        return chars.stream().sorted(Character::compareTo).collect(Collectors.toList());
    }

    public static List<String> getMessage() {
        try {
            List<Character> chars = getAlphabet();
            List<String> words = getMessage(chars);

            return words;
        } catch (FileNotFoundException e) {
            System.out.println("Chybka");
        }
        return null;
    }

    public static List<String> getMessage(List<Character> chars) throws FileNotFoundException {
        Scanner scan = new Scanner(new FileInputStream(new File("knuth_words")));

        while (scan.hasNextLine()) {
            String word = scan.nextLine().trim().toUpperCase();
            List<String> wrds = new ArrayList<>();
            if (word.chars().mapToObj(e -> (char)e).allMatch(chars::contains)) { //abeceda obsahuje vsetky znaky slova zo slovnika
                wrds.add(word);

                List<Character> characters = word.chars().mapToObj(e -> (char)e).collect(Collectors.toList());

                List<Character> newCharset = new ArrayList<>(chars);

                for (Character character : characters) { // nasli sme slovo, ktore obsahuje znaky z abecedy, teraz tieto znaky z abecedy zmazeme a rekurzivne budeme kontrolovat dalej
                    newCharset.remove(character);
                }

                if (newCharset.isEmpty()) { //ak su uz zvysne volne znaky prazdne, tak vieme, ze sme nasli slova, ktore obsahuju vsetky pismena
                    return wrds;
                }


                List<String> newWords = getMessage(newCharset); //rekurzivne pre novu abecedu
                if (newWords != null) {
                    wrds.addAll(newWords); //ak sme nasli nove slova, nie null, tak sme nasli vysledne pole slov ktore obsahuju vsetky znaky z abecedy prave raz
                    return wrds;
                }
            }

        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getMessage());
    }
}
