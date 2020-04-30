import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebCrawler {
    private static Stream<String> fetch(String path) {
        try {
            BufferedReader read = new BufferedReader(
                    new InputStreamReader(
                            new URL(path).openStream()));
            return read.lines();
        } catch (IOException e) {
            return null;
        }
    }

    public static Set<String> crawl(String url, int depth) {

        return recursiveCrawl(url, depth, new HashSet<String>());
    }

    public static Set<String> recursiveCrawl(String url, int depth, Set<String> visited) {
        if (depth == 0) return new HashSet<String>();
        visited.add(url);
        Set<String> resultUrls = new HashSet<>();
        Stream<String> fetchUrls = fetch(url);

        for (String line : fetchUrls != null ? fetchUrls.collect(Collectors.toList()) : new ArrayList<String>()) {
            Scanner scanner = new Scanner(new StringReader(line));

            String found = scanner.findInLine("<a\\shref=\".*\">");

            if (found != null) {
                for (String subLink : Arrays.asList(found.split("\">"))) {
                    if (subLink.matches("<a href.*")) {
                        String urlSubString = subLink.substring(9);

                        if (!urlSubString.matches("http.*")) {
                            urlSubString = url + urlSubString;
                        }
                        //System.out.println(urlSubString);
                        resultUrls.add(urlSubString);
                    }
                }
            }

        }
        Set<String> newUrls = new HashSet<>();
        for (String link : resultUrls) {
            if (!visited.contains(link)) {
                newUrls.addAll(recursiveCrawl(link, depth - 1, visited));
            }
        }
        resultUrls.addAll(newUrls);
        return resultUrls;
    }

}
