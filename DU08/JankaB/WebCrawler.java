import java.io.*;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WebCrawler {
    static Set<String> stranky = new HashSet<>();
    static Set<String> spracuj = new HashSet<>();

    private static Stream<String> fetch(String path) {
        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            //new FileInputStream(path)));
                            new URL(path).openStream()));
            return read.lines();
        } catch (IOException e) {
            //System.out.println("nieco zle sa stalo: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    public static Set<String> crawl(String url, int depth) {
        Stream<String> strankaLines = fetch(url);

        if (strankaLines != null) {
            strankaLines.forEach(line -> {
                //System.out.println(line);
                Scanner sc = new Scanner(line);
                //Pattern pat = Pattern.compile(".*(href=\")([\\/:a-zA-Z0-9\\._#?=]+[\\/]+[\\/:a-zA-Z0-9\\._#?=]+)(\").*"); //hmmmmmmmmmmmm
                Pattern pat = Pattern.compile(".*(href=\")([\\/:a-zA-Z0-9\\._#?=]+)(\").*");
                Matcher m = pat.matcher(line);
                if (m.find()) {
                    //System.out.println(m.group(0));
                    String stranka = m.group(2);

                    if (!stranka.contains("http")) {
                        if (url.charAt(url.length() - 1) == '/') stranka = url + stranka;
                        else stranka = url + '/' + stranka;
                    }


                    if (!stranky.contains(stranka)) {
                        spracuj.add(stranka);
                        stranky.add(stranka);
                        if (depth > 0) crawl(stranka, depth - 1);
                    }

                    //System.out.println("str: " + stranka);
                }
            });
        }

        return stranky;
    }

    public static void main(String[] args) {
        crawl("http://dai.fmph.uniba.sk/courses/JAVA/", 2);
    }
}
