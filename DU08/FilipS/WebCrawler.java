import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WebCrawler {

    public static Set<String> crawl(String url, int depth){
        Set<Vrchol> visited = new HashSet<>();

        Deque<Vrchol> queue = new ArrayDeque<>();
        queue.add(new Vrchol(url, 0));

        while(!queue.isEmpty()){
            Vrchol vrchol = queue.pollFirst();
            if(vrchol.getUroven() > depth){
                break;
            }

//            System.out.println(vrchol.getUroven());
//            System.out.println(vrchol.getUrl());
            if(!visited.contains(vrchol)){
                visited.add(vrchol);

                try{
                    for(String line : fetch(vrchol.url).collect(Collectors.toList())){
                        //https://stackoverflow.com/questions/15926142/regular-expression-for-finding-href-value-of-a-a-link
                        List<String> zoz = List.of("<a\\s+(?:[^>]*?\\s+)?href=([\"'])(.*?)\\1"); //,"a href=\"(http[s]?.*)\"", "(http[s]?.*\\.com)/");
                        for(String sp : zoz){
                            Pattern p = Pattern.compile(sp);
                            Matcher m = p.matcher(line);
                            if(m.find()){
                                String s = m.group(2);
//                        System.out.println(s);
                                Vrchol sused = new Vrchol(s, vrchol.getUroven()+1);
                                if(!visited.contains(sused)){ //&& (s.contains(".sk") || s.contains(".com"))
                                    queue.addLast(sused);
                                }
                            }
                        }

                    }
                }catch (IOException e){
//                    System.out.println(e.getMessage());
                    //nepodaril sa fetch, skus ist dalej
                    ;
                }

            }
        }

        Set<String> vysl = new HashSet<>();
        visited.stream().forEach(e -> vysl.add(e.getUrl()));
        return vysl;
    }

    private static Stream<String> fetch(String path) throws IOException{
//        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new URL(path).openStream()));
            return read.lines();
//        } catch (IOException e) {
//            System.out.println("nieco zle sa stalo: " + e.getMessage());
//            e.printStackTrace();
////            return null;
//        }
    }

    public static void main(String[] args) {
        System.out.println(crawl("http://dai.fmph.uniba.sk/courses/JAVA/", 2));
//        System.out.println(crawl("https://github.com/Programovanie4",2));
//        Set<Vrchol> mnoz = new HashSet<>();
//        mnoz.add(new Vrchol("http://dai.fmph.uniba.sk/courses/JAVA/",0));
//        mnoz.add(new Vrchol("http://dai.fmph.uniba.sk/courses/JAVA/",0));
//        mnoz.add(new Vrchol("http://dai.fmph.uniba.sk/courses/JAVA/",0));
//        System.out.println(mnoz.size());
    }
}
