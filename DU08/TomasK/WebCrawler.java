import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WebCrawler {
    static Set<String> visited = new HashSet<>();

    public static Set<String> crawl(String url, int depth) {
        visited.add(url);
        if (depth == 0) {
            return visited;
        }
        System.out.println("v hlbke: " + depth + " stranka " + url);
        Stream<String> stranka = fetch(url);
        if (stranka != null) {
            stranka.forEach(lajn -> {
                Pattern pat = Pattern.compile(".*href=\"(http:.*</a>).*");
                Matcher m = pat.matcher(lajn);
                if (m.find()) {
                    System.out.println(lajn);
                    String pageFound = m.group(1).substring(0, m.group(1).indexOf("\""));
//                    System.out.println("Na tejto stranke som nasiel: " + pageFound);
                    if (depth > 0 && !visited.contains(pageFound)) {
//                        visited.add(m.group(1));
                        int depth2 = depth - 1;
                        crawl(pageFound, depth2);
                    }
                } else {
//                    System.out.println("Na stranke " + url + " som nic dalsie nenaniel");
                }
            });
        }
        return visited;
    }

    private static Stream<String> fetch(String path) {
        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            new URL(path).openStream()));
            return read.lines();
        } catch (IOException e) {
            //System.out.println(path);
            //System.out.println("nieco zle sa stalo: " + e.getMessage());
            //e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(crawl("http://dai.fmph.uniba.sk/courses/JAVA/", 2));
    }
}
