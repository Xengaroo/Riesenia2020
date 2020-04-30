import java.net.URL;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class WebCrawler {
    private static Set<String> visited;
    private static int dep;

    /*

    regex inspirovany regexom z https://stackoverflow.com/questions/3809401/what-is-a-good-regular-expression-to-match-a-url

     */

    private static Stream<String> fetch2(String path) {
        try {
            Scanner read = new Scanner(new URL(path).openStream());
            return read.findAll(Pattern
                    .compile("(http[s]?\\:)?\\/\\/(www\\.)?[-a-zA-Z0-9\\@\\:\\%\\._\\+~#=]+\\.[a-zA-Z0-9\\(\\)]{1,6}\\b([-a-zA-Z0-9\\(\\)@\\:\\%_\\+.~#?&\\/\\/\\=]*)"))
                    .map(r -> r.group(0));
        } catch (Exception e) {
            return null;
        }
    }

    public static Set<String> crawl(String url, int depth){
        visited = new HashSet<>();
        dep = depth;
        rek(url, 0);
        return visited;
    }

    private static void rek(String url, int depth){
        if (depth > dep){
            return;
        }
        var r = fetch2(url);
        if (r != null) {
            r.filter(u -> !visited.contains(u)).forEach(u -> {visited.add(u); rek(u, depth + 1);});
        }
    }

    public static void main(String[] args) {
        System.out.println(crawl("http://dai.fmph.uniba.sk/courses/JAVA/", 2).size());
    }

}
