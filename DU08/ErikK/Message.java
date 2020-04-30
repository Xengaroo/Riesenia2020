import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*

    podla svojho skromneho nazoru
    ma tajna sprava byt
    JAVA IS COOL
    ale vzhladom na to, ze zadanie
    nepovedalo, ze je len jedna dobra
    moznost a taktiez moj program
    je toho isteho nazoru,
    vracia iba nejake nesuvisle
    blabotanie...
    (typu JAVA IS LOCO,
    alebo YO AI SLAV CO)
    dufam, ze to ale nevadi

 */

public class Message {
    private static List<String> znaky;
    private static List<String> slova;
    private static List<String> l;
    private static List<String> res;

    public static List<String> getMessage(){
        getAlphabet();
        try {
            String vsetky = znaky.stream()
                    .distinct()
                    .collect(Collectors.joining());
            l = Files.lines(Paths.get("knuth_words"))
                    .map(s -> s.toUpperCase())
                    .filter(s -> s.length() != 1)
                    .filter(s -> s.matches("^[" + vsetky + "]+$"))
                    .collect(Collectors.toList());
            slova = new ArrayList<>();
            findSlova();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return res;
    }

    public static void findSlova(){
        if (res != null){
            return;
        }
        if (znaky.isEmpty()){
            res = new ArrayList<>(slova);
            return;
        }
        for (String slovo: l) {
            if (!slova.contains(slovo) && podmnozina(slovo, znaky.stream().collect(Collectors.joining()))){
                for (int i = 0; i < slovo.length(); i++) {
                    znaky.remove(String.valueOf(slovo.charAt(i)));
                }
                slova.add(slovo);
                findSlova();
                slova.remove(slovo);
                for (int i = 0; i < slovo.length(); i++) {
                    znaky.add(String.valueOf(slovo.charAt(i)));
                }
            }
        }
    }

    public static boolean podmnozina(String s1, String s2){
        Map<Integer, List<Integer>> m1 = s1.chars().boxed().collect(Collectors.groupingBy(s -> s));
        Map<Integer, List<Integer>> m2 = s2.chars().boxed().collect(Collectors.groupingBy(s -> s));
        for (Integer k1: m1.keySet()){
            if (!(m2.containsKey(k1) && m1.get(k1).size() <= m2.get(k1).size())){
                return false;
            }
        }
        return true;
    }

    public static List<Character> getAlphabet(){
        znaky = new ArrayList<>();
        File dir = new File("tajna-sprava");
        rek(dir);
        znaky.sort(String::compareTo);
        return znaky.stream().map(s -> s.charAt(0)).collect(Collectors.toList());
    }

    private static void rek(File dir){
        if (!dir.exists()){
            return;
        }
        if (dir.isFile()){
            try (Scanner read = new Scanner(new FileInputStream(dir))) {
                read.findAll("[\\.\\-]*")
                        .map(c -> Morse.decode(c.group()))
                        .filter(c -> !c.equals(""))
                        .forEach(c -> znaky.add(c));
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        } else if (dir.isDirectory()) {
            String[] sub = dir.list();
            Stream.of(sub).forEach(n -> rek(new File(dir + File.separator +n)));
        }
    }

    public static void main(String[] args) {
        System.out.println(getAlphabet());
        System.out.println(getMessage());
    }

}
