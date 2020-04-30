import java.io.*;
import java.net.URL;
import java.sql.SQLOutput;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Date.*;

public class Subtitles {
    public static void addAds(String inFile, String outFile, String inEnc, String outEnc, List<String> ads) throws FileNotFoundException {

        if(ads == null){
            return;
        }

        if(inEnc == null){
            InputStreamReader in = new InputStreamReader(new FileInputStream(inFile));
            inEnc = in.getEncoding();
            System.out.println(inEnc);
        }


        ArrayList<String> poleReklam = new ArrayList<>(ads);
        poleReklam =  (ArrayList<String>) poleReklam.stream().sorted((s1, s2) -> mojePorovnanie(s1, s2)).collect(Collectors.toList());

        Stream<String> vstupnyStream = fetch(inFile, inEnc);

        ArrayList<String> pole = new ArrayList<>();
        ArrayList<ArrayList<String>> poleTitulky = new ArrayList<>();
        for(String s : vstupnyStream.collect(Collectors.toList())){
            if("".equals(s.trim())){
                poleTitulky.add(pole);
                pole = new ArrayList<>();
            }else{
                pole.add(s);
            }
        }
        poleTitulky.add(pole);


        for (String s2 : poleReklam) {

            for (int i = 0; i < poleTitulky.size(); i++) {
                String startTitulok = poleTitulky.get(i).get(1).split(" --> ")[0];
                String koniecTitulok = poleTitulky.get(i).get(1).split(" --> ")[1];

                String startReklama = s2.split(" --> ")[0];
                String koniecRelama = s2.split(" --> ")[1];

                ArrayList<String> noveCasy = jeVIntervale(startTitulok, koniecTitulok, startReklama, koniecRelama);
                String novyString = noveCasy.get(0) + " --> " + noveCasy.get(1);

                ArrayList<String> novePole = new ArrayList<>();

                novePole.add(poleTitulky.get(i).get(0));
                novePole.add(novyString);
                for (int j = 2; j < poleTitulky.get(i).size(); j++) {
                    novePole.add(poleTitulky.get(i).get(j));
                }
                poleTitulky.set(i, novePole);
            }
        }

        //zapis

        try{
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), outEnc));
//            System.out.println(poleTitulky);
            for(int i = 0; i < poleTitulky.size(); i++){
                for(String s : poleTitulky.get(i)){
                    bw.write(s);bw.newLine();
                }
                if(i != poleTitulky.size()-1){
                    bw.newLine();
                }
            }
            bw.close();
        }catch (UnsupportedEncodingException e){
            System.out.println("Zle kodovanie");
            System.out.println(e.getStackTrace());
            return;
        }
        catch(IOException e){
            System.out.println("Chyba pri zapisovani");
            System.out.println(e.getStackTrace());
            return;
        }
    }

    private static Stream<String> fetch(String path, String inEnc) throws FileNotFoundException {
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(path), inEnc));
            return read.lines();
        } catch (FileNotFoundException e0) {
            System.out.println("Zly subor");
            throw e0;
        } catch (IOException e) {
            System.out.println("nieco zle sa stalo: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList<String> jeVIntervale(String startTitulok, String koniecTitulok, String startReklama, String koniecReklama){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss[,SSS]");
        ArrayList<String> res = new ArrayList<>();
        LocalTime casStartTitulok = LocalTime.parse(startTitulok, dtf);
        LocalTime casKoniecTitulok = LocalTime.parse(koniecTitulok, dtf);
        LocalTime casStartReklama = LocalTime.parse(startReklama, dtf);
        LocalTime casKoniecReklama = LocalTime.parse(koniecReklama, dtf);

//        if(Duration.between(casStartReklama, casStartTitulok).isNegative()){
//            casStartTitulok = casStartTitulok.plus(Duration.between(casStartReklama, casKoniecReklama));
//            casKoniecTitulok = casKoniecTitulok.plus(Duration.between(casStartReklama, casKoniecReklama));
//            String s1 = casStartTitulok.format(dtf);
//            String s2 = casKoniecTitulok.format(dtf);
//            res.add(s1);
//            res.add(s2);
//
//        }

        if(casStartReklama.compareTo(casStartTitulok) < 0){
            casStartTitulok = casStartTitulok.plus(Duration.between(casStartReklama, casKoniecReklama));
            casKoniecTitulok = casKoniecTitulok.plus(Duration.between(casStartReklama, casKoniecReklama));
            String s1 = casStartTitulok.format(dtf);
            String s2 = casKoniecTitulok.format(dtf);
            res.add(s1);
            res.add(s2);
        }
        else{
            String s1 = casStartTitulok.format(dtf);
            String s2 = casKoniecTitulok.format(dtf);
            res.add(s1);
            res.add(s2);
        }
        return res;
    }

    public static int mojePorovnanie(String s1, String s2){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss[,SSS]");
        String start = s1.split(" --> ")[0];
        String koniec = s2.split(" --> ")[0];
        LocalTime casStart = LocalTime.parse(start, dtf);
        LocalTime casKoniec = LocalTime.parse(koniec, dtf);
        return casStart.compareTo(casKoniec);

    }


    public static void main(String[] args) {
        try{

            Subtitles.addAds("input1.srt", "output.srt", null, "UTF8", List.of("00:00:22,123 --> 00:02:27,623"));
        }catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }







    }


}
