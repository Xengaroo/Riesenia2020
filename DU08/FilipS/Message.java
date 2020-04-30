import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Message {
    public static List<Character> getAlphabet(){
        ArrayList<Character> vysl = new ArrayList<>();
        ArrayList<String> ziskanePismena = new ArrayList<>();

        File startSubor = new File("tajna-sprava");
        File[] subory = startSubor.listFiles();
//        System.out.println(subory);
        try{
            ziskanePismena = rek(subory);
        }catch (FileNotFoundException e0){
            System.out.println(e0.getMessage());
            return vysl;
        }

        for(String s : ziskanePismena){
            Character c = Morse.decode(s).charAt(0);
            vysl.add(c);
        }

//        System.out.println(ziskanePismena);
        return vysl.stream().sorted(Character::compareTo).collect(Collectors.toList());
    }

    public static List<String> vysl = new ArrayList<>();

    public static List<String> getMessage() {
        List<Character> znaky = new ArrayList<>();
        List<String> slova = new ArrayList<>();
        znaky = getAlphabet();

        File startSubor  = new File("knuth_words");

        try{
            slova = spracujSlovnik(fetch(startSubor.getPath()), znaky);
        }catch (FileNotFoundException e1){
            System.out.println(e1.getMessage());
            return  slova;
        }

//        vysl = new ArrayList<>();
        back(slova, znaky, new ArrayList<>(), znaky.size());
//        return slova;
        return vysl;
    }

    public static List<String> spracujSlovnik(Stream<String> vstupnyStream, List<Character> znaky){
        List<String> slova = new ArrayList<>();
        for(String s : vstupnyStream.collect(Collectors.toList())){
            if(skontrolujPovoleneZnaky(s.toUpperCase(), znaky) == true){
                slova.add(s);
            }
        }
        return slova;
    }

    public static boolean skontrolujPovoleneZnaky(String slovo, List<Character> znaky){
        for(Character c : slovo.toCharArray()){
            if(znaky.contains(c) == false){
                return  false;
            }
            if(slovo.chars().mapToObj(z -> (char) z).filter(q -> q == c).count() > znaky.stream().filter(a -> a == c).count()){
                return false;
            }
        }
        return true;
    }

    //https://stackoverflow.com/questions/3154488/how-do-i-iterate-through-the-files-in-a-directory-in-java
    public static ArrayList<String> rek(File[] files) throws FileNotFoundException {
        ArrayList<String> res = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                res.addAll(rek(file.listFiles())); // Calls same method again.
            } else {
                //tu spracujem subor
                res.addAll(spracujSubor(file));
            }
        }
        return res;
    }

    public static List<String> spracujSubor(File subor) throws FileNotFoundException {
        ArrayList<String> res = new ArrayList<>();
        List<String> zoz = fetch(subor.getPath()).collect(Collectors.toList());
        Pattern p = Pattern.compile("([.-]+)");
        for(String line : zoz){
            Matcher m = p.matcher(line);
            if(m.find()){
                res.add(m.group(1));
            }
        }
        return res;
    }

    private static Stream<String> fetch(String path) throws FileNotFoundException {
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
            return read.lines();
        } catch (FileNotFoundException e0) {
            System.out.println("Zly subor");
            throw e0;
        }
    }


    public static  void back(List<String> vstupneSlova, List<Character> vstupneZnaky, ArrayList<String> sprava, int dlzka){
        if(sprava.stream().reduce(0, (acc, s) -> acc+s.length(), (a,b)->a+b) == dlzka && sprava.stream().allMatch(a -> a.length() > 1) ){
//            System.out.println(sprava);
            vysl = sprava;
            return;
        }
        if(vstupneSlova.isEmpty() || vstupneZnaky.isEmpty()){
            return;
        }

        for(String s : vstupneSlova){
            if (skontrolujPovoleneZnaky(s.toUpperCase(), vstupneZnaky)) {

                ArrayList<Character> kopiaVstupneZnaky = new ArrayList<>(vstupneZnaky);
                ArrayList<Character> pouziteZnaky = (ArrayList<Character>) s.toUpperCase().chars().mapToObj(z -> (char) z).collect(Collectors.toList());
                kopiaVstupneZnaky.removeAll(pouziteZnaky);

                ArrayList<String> kopiaSprava = new ArrayList<>(sprava);
                kopiaSprava.add(s.toUpperCase());

                ArrayList<String> kopiaVstupneSlova = new ArrayList<>();
                for(String slovo : vstupneSlova){
                    if(!slovo.toUpperCase().equals(s.toUpperCase())){
                        if(skontrolujPovoleneZnaky(slovo.toUpperCase(), kopiaVstupneZnaky) == true){
                            kopiaVstupneSlova.add(slovo.toUpperCase());
                        }
                    }
                }
//                System.out.println(kopiaVstupneSlova.remove(s));
                back(kopiaVstupneSlova, kopiaVstupneZnaky, kopiaSprava, dlzka);
            }
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Povolene znaky: " + getAlphabet());
        System.out.println(getMessage());
//        ArrayList<String> zoz = new ArrayList<>();
//        zoz.addAll(List.of("AC", "ACS", "AI", "AL", "CA", "CIA", "CO", "IA", "ICL", "IL", "LSI", "OS", "SC", "VA", "VLSI"));
    }
}
