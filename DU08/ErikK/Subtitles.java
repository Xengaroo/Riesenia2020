import java.io.*;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Subtitles {

    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(new File(inFile)), inEnc));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(outFile)), outEnc));
            List<List<LocalTime>> adds = ads.stream()
                    .map(s1 -> Stream
                            .of(s1.split(" --> "))
                            .map(s2 -> s2.replaceAll(",", "."))
                            .map(s2 -> LocalTime.parse(s2))
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
            adds.sort(Comparator.comparing(s -> s.get(0)));
            LocalTime posun = LocalTime.parse("00:00:00.00");
            List<LocalTime> prvy = adds.size() != 0 ? adds.get(0) : null;
            while (true){
                List<String> titulok = new ArrayList<>();
                String line = in.readLine();
                if (line == null){
                    break;
                }
                while (line != null && line.length() != 0){
                    titulok.add(line);
                    line = in.readLine();
                }
                String[] cas = titulok.get(1).split(" --> ");
                LocalTime l1 = LocalTime.parse(cas[0].replaceAll(",", "."))
                        .plus(Duration.between(LocalTime.parse("00:00:00.00"), posun));
                LocalTime l2 = LocalTime.parse(cas[1].replaceAll(",", "."))
                        .plus(Duration.between(LocalTime.parse("00:00:00.00"), posun));
                if (prvy != null && l1.compareTo(prvy.get(0)) > 0){
                    l1 = l1.plus(Duration.between(prvy.get(0), prvy.get(1)));
                    l2 = l2.plus(Duration.between(prvy.get(0), prvy.get(1)));
                    posun = posun.plus(Duration.between(prvy.get(0), prvy.get(1)));
                    adds.remove(prvy);
                    if (adds.size() != 0){
                        prvy = adds.get(0);
                    } else {
                        prvy = null;
                    }
                }
                for (int i = 0; i < titulok.size(); i++) {
                    if (i == 1){
                        out.write(l1.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")).replaceAll("\\.", ",") + " --> " + l2.format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")).replaceAll("\\.", ","));
                        out.newLine();
                    } else {
                        out.write(titulok.get(i));
                        out.newLine();
                    }
                }
                if (line != null) {
                    out.newLine();
                }
            }
            out.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
