import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Message {
    public static Set<String> files = new HashSet<>();

    static class FilterAdresara implements FilenameFilter {
        public boolean accept(File dir, String name) {
            File f = new File(dir, name);
            return f.isDirectory() || f.isFile();
        }
    }

    public static void vypisRek(String f) {
        String[] mena;
        File subor = new File(f);
        FilterAdresara FilterAdr = new FilterAdresara();
        mena = subor.list(FilterAdr);
        if (mena != null) {
            for (int i = 0; i < mena.length; i++) {
                String podadr = new String (f + File.separator + mena[i]);
                if (new File(podadr).isFile()) {
                    files.add(podadr);
                }
                vypisRek(podadr);
            }
        }
    }

    public static List<Character> getAlphabet() {
        vypisRek("tajna-sprava");
        List<Character> res = new ArrayList<>();
        for (String f : files) {
            try {
                try (Scanner sc = new Scanner(new File(f))) {
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        Pattern pat = Pattern.compile(".*(\\.|\\-)+.*");
                        Matcher m = pat.matcher(line);
                        if (m.find()) {
                            String word = "";
                            for (int i = 0; i < line.length(); i++) {
                                if (line.charAt(i) == '.' || line.charAt(i) == '-') {
                                    word += line.charAt(i);
                                }
                            }
                            res.add(Morse.decode(word).toUpperCase().trim().charAt(0));
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(res);
        return res;
    }
    static int MAX_CHAR = 256;
    static boolean isPresent(String s, String q) {
        int []freq = new int[MAX_CHAR];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }

        for (int i = 0; i < q.length(); i++) {
            freq[q.charAt(i)]--;
            if (freq[q.charAt(i)] < 0)
                return false;
        }
        return true;
    }

    public static List<String> getMessage() {
        String slovoTAJNE = "";
        for (Character c : getAlphabet()) {
            slovoTAJNE += c;
        }
        System.out.println(slovoTAJNE);
        List<String> res = new ArrayList<>();
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("knuth_words"));
            BufferedReader br = new BufferedReader(isr);
            String finalSlovoTAJNE = slovoTAJNE;
            br.lines().forEach(r -> {
                if (isPresent(finalSlovoTAJNE,r.toUpperCase())) {
                    res.add(r.toUpperCase());
                }
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(getAlphabet().toString());
        System.out.println(getMessage().toString());
    }
}
