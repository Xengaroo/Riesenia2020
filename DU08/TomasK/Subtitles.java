import java.io.*;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Subtitles{

    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads) {
        System.out.println(inFile + "\n" + outFile + "\n" + inEnc + "\n" + outEnc + "\nADS: " + ads.toString());
        System.out.println("Sorted: ");
        Comparator<String> byRanking
                = Comparator.comparing((String sub) -> sub.split(" --> ")[0]);

        Collections.sort(ads, byRanking);

//        System.out.println(ads.toString());

        try (InputStreamReader isr = new InputStreamReader(new FileInputStream(inFile), inEnc)) {

            try (Scanner sc = new Scanner(new File(inFile), inEnc)) {
                StringBuffer inputBuffer = new StringBuffer();

                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    Pattern pat = Pattern.compile(".*-->.*");
                    Matcher m = pat.matcher(line);
                    if (m.find()) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

                        LocalTime newStartSub = LocalTime.parse(line.split(" --> ")[0], formatter);
                        LocalTime newEndSub = LocalTime.parse(line.split(" --> ")[1], formatter);

                        for (String ad : ads) {
                            LocalTime startAd = LocalTime.parse(ad.split(" --> ")[0], formatter);
                            LocalTime endAd = LocalTime.parse(ad.split(" --> ")[1], formatter);

                            if (startAd.isBefore(newStartSub)) {
                                newStartSub = newStartSub.plus(Duration.between(startAd, endAd));
                                newEndSub = newEndSub.plus(Duration.between(startAd, endAd));
                            }
                        }

                        line = newStartSub.format(formatter) + " --> " + newEndSub.format(formatter);
//                        System.out.println(line);

                    }
                    inputBuffer.append(line);
                    inputBuffer.append('\n');
                    isr.close();
                }

                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), outEnc));
                bw.write(inputBuffer.toString());
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        Subtitles.addAds();
    }
}
