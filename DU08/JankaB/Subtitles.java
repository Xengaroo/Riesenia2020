
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Subtitles {

    private static Stream<String> fetch(String path, String inEnc) {
        String enc;
        if (inEnc == null) enc = zisti(path);
        else enc = inEnc;

        System.out.println(enc);

        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(new File(path)), enc));
                            //new URL(path).openStream()));
            return read.lines();
        } catch (IOException e) {
            System.out.println("nieco zle sa stalo: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    //maybe I will surrender
    public static String zisti(String path) {
        List<String> kodovania = List.of("UTF-8", "ISO8859_2", "Cp1250");
        List<String> lines;

        for (String k : kodovania) {
            try {
                BufferedReader read = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(new File(path)), k));

                lines = read.lines().collect(Collectors.toList());

                boolean chyba = false;
                for (String s : lines) {

                    for (int i = 0; i < s.length(); i++) {
                        if (s.charAt(i) == '\uFFFD' || s.charAt(i) == '') {
                            chyba = true;
                            break;
                        }
                    }
                }

                if (!chyba) return k;

            } catch (IOException e) {
                System.out.println("nieco zle sa stalo: " + e.getMessage());
                return null;
            }

        }
        return "Cp1250";
    }

    //toto mozno bude treba prerobit !!!!!!
    private static BufferedWriter write(String path, String outEnc) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(new File(path)), outEnc));
            return writer;

        } catch (IOException e) {
            System.out.println("nieco zle sa stalo: " + e.getMessage());
            return null;
            //e.printStackTrace();
        }
    }

    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads) {
        Stream<String> casyLines = fetch(inFile, inEnc);

        BufferedWriter writer = write(outFile, outEnc);

        //if (ads.size() == 0) System.out.println("som prazdne");
        ads.sort((s, t1) -> {
            Scanner sc1 = new Scanner(s);
            Scanner sc2 = new Scanner(t1);
            Pattern pat = Pattern.compile("(\\d{2}:\\d{2}:\\d{2},\\d{3})(.*)(\\d{2}:\\d{2}:\\d{2},\\d{3})");
            Matcher m1 = pat.matcher(s);
            Matcher m2 = pat.matcher(t1);

            if (m1.find() && m2.find()) {
                String cas = m1.group(1);
                String cas2 = m2.group(1);

                LocalTime cass = LocalTime.parse(cas, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
                LocalTime cass2 = LocalTime.parse(cas2, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
                return cass.compareTo(cass2);
            }
            return 0;
        }); //sortli sme ads

        System.out.println(ads);

        List<LocalTime> start = new ArrayList<>(ads.size());
        List<LocalTime> end = new ArrayList<>(ads.size());
        List<Duration> duration = new ArrayList<>(ads.size());


        for (String time : ads) {
            Scanner sc = new Scanner(time);
            Pattern pat = Pattern.compile("(\\d{2}:\\d{2}:\\d{2},\\d{3})(.*)(\\d{2}:\\d{2}:\\d{2},\\d{3})");
            Matcher m = pat.matcher(time);
            if (m.find()) {
                String cas = m.group(1);
                String cas2 = m.group(3);

                LocalTime cass = LocalTime.parse(cas, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
                LocalTime cass2 = LocalTime.parse(cas2, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));

                start.add(cass);
                end.add(cass2);

                Duration d = Duration.between(cass,cass2);
                duration.add(d);
            }
        }

        casyLines.forEach(line -> {

            String newLine = line;
            Scanner sc = new Scanner(line);
            Pattern pat = Pattern.compile("(\\d{2}:\\d{2}:\\d{2},\\d{3})(.*)(\\d{2}:\\d{2}:\\d{2},\\d{3})");
            Matcher m = pat.matcher(line);

            if (m.find()) {
                String cas = m.group(1);
                String medzi = m.group(2);
                String cas2 = m.group(3);

                for (int i = 0; i < ads.size(); i++) {
                    LocalTime tit = LocalTime.parse(cas, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
                    LocalTime tit2 = LocalTime.parse(cas2, DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));

                    if (start.get(i).compareTo(tit) < 0) {
                        tit = tit.plus(duration.get(i));
                        tit2 = tit2.plus(duration.get(i));

                        cas = tit.format(DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
                        cas2 = tit2.format(DateTimeFormatter.ofPattern("HH:mm:ss,SSS"));
                        //System.out.println(cas + medzi + cas2);
                        newLine = cas + medzi + cas2;
                    }
                    //else if (start.get(i).compareTo(tit) > 0 || )

                }
            }

                    try {
                        writer.write(newLine);
                        writer.newLine();
                    } catch (IOException e) {
                        System.out.println("pri write chyba");
                        e.printStackTrace();
                    }
                    //zapisat riadok .. treba zapisat aj tie bez patternu, pozor na to !!!
        }
        );

        try {
            writer.close();
        } catch (IOException e) {
            System.out.println("pri close chyba");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        List<List<String>> files = Arrays.asList(
                Arrays.asList("UTF8", "UTF8",			// input1 - bez konverzie, jedna reklama
                        "00:00:22,123 --> 00:02:27,623"),
                Arrays.asList("ISO8859_2", "Cp1250"),	// input2 - konverzia, bez reklamy
                Arrays.asList("UTF8", "Cp1250",			// input3 - konverzia, s reklamami
                        "00:13:49,498 --> 00:20:37,648",
                        "00:05:50,086 --> 00:09:12,379",
                        "00:26:33,382 --> 00:31:43,764")
        );

        for(int i = 0; i < files.size(); i++){
            int n = i + 1;
            List<String> file = files.get(i);
            Subtitles.addAds("input"+n+".srt", "output"+n+".srt",
                    null, file.get(1), file.subList(2, file.size()));
            System.out.println("expected"+n+".srt" + "output"+n+".srt"+ file.get(1));
        }
    }
}
