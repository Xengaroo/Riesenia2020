import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Subtitles {
    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads) {
        if (inEnc == null) inEnc = "UTF8";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile), inEnc));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), outEnc));
            Duration dur = Duration.ofSeconds(0);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");

            List<String> adsCopy = new ArrayList<>(ads);
            while (true) {
                //----------------- READ --------------------
                String orderNum = br.readLine();
                if (orderNum == null) break;
                String time = br.readLine();

                //--------------- TIME HANDLING ------------
                String[] timeSplit = time.split(" --> ");

                LocalTime timeSince = LocalTime.parse(timeSplit[0], dtf);
                LocalTime timeUntil = LocalTime.parse(timeSplit[1], dtf);

                timeSince = timeSince.plus(dur);
                timeUntil = timeUntil.plus(dur);
                boolean timeChanged = false;

                do {
                    timeChanged = false;

                    List<String> adsToRemove = new ArrayList<>();
                    for (String ad : adsCopy) {
                        String[] adTimeSplit = ad.split(" --> ");
                        LocalTime adTimeSince = LocalTime.parse(adTimeSplit[0], dtf);
                        LocalTime adTimeUntil = LocalTime.parse(adTimeSplit[1], dtf);


                        if (adTimeSince.compareTo(timeSince) <= 0) {//reklama zacala pred dialogom
                            timeChanged = true;
                            dur = dur.plus(Duration.between(adTimeSince, adTimeUntil));
                            timeSince = timeSince.plus(Duration.between(adTimeSince, adTimeUntil));
                            timeUntil = timeUntil.plus(Duration.between(adTimeSince, adTimeUntil));
                            adsToRemove.add(ad);
                        }
                    }
                    adsCopy.removeAll(adsToRemove);
                } while (timeChanged);


                time = timeSince.format(dtf) + " --> " + timeUntil.format(dtf);
                //------------------------------------------

                StringBuffer sb = new StringBuffer();
                String srts = br.readLine();
                sb.append(srts);

                while (!(srts == null) && !srts.trim().equals("")) {
                    srts = br.readLine();

                    if (srts != null) {
                        sb.append(System.lineSeparator());
                        sb.append(srts);
                    }
                }
                srts = sb.toString();
                //---------------- END READ -----------------
                //---------------- WRITE -----------------
                bw.write(orderNum);
                bw.newLine();
                bw.write(time);
                bw.newLine();
                bw.write(srts);
                bw.newLine();

            }
            br.close();
            bw.close();
        } catch (IOException e) {
            System.out.println("Chybka :(");
        }
    }

    public static void main(String[] args) {

    }
}
