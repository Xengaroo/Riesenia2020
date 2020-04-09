import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SirenieSpravy {
    List<Set<String>> bubliny;
    Set<String> panikari;
    public SirenieSpravy(List<Set<String>> bubliny, Set<String> panikari){
        this.bubliny = bubliny;
        this.panikari = panikari;
    }
    public int informovani(){
        Set<String> result = new HashSet<>(panikari);
        for(String c : panikari){
            result.addAll(informovani_rek(c, result));
        }
        return result.size();
    }
    public Set<String> informovani_rek(String clovek, Set<String> res){
        for(Set<String> b : bubliny){
            if(b.contains(clovek)){
                for(String c : b){
                    if(!res.contains(c)){
                        res.add(c);
                        res = informovani_rek(c, res);
                    }
                }
            }
        }
        return res;

    }

    public int trvanie(){
        int inf = informovani();
        int t = 0;
        Set<String> l = new HashSet<>(panikari);
        while(l.size() < inf){
            Set<String> pom = new HashSet<>(l);
            for(String p : pom){
                for(Set<String> b : bubliny){
                    if(b.contains(p)){
                        l.addAll(b);
                    }
                }
            }
            t++;
        }
        return t;
    }

    public int neinformovani(){
        Set<String> ludia = new HashSet<>(panikari);
        for(Set<String> b : bubliny){
            ludia.addAll(b);
        }
        return Math.max(ludia.size() - informovani(), 0);
    }

    public static void main(String[] args) {
        var bubliny = List.of(
                Set.of("Peter", "Xaver", "Jano", "Rebeka", "Sabina"),
                Set.of("Katka", "Marienka", "Jano", "Nata"),
                Set.of("Peter", "Katka", "Filomena", "Olga"),
                Set.of("Bubo", "Eva", "Helga", "Terka"),
                Set.of("Lukas", "Gabika"),
                Set.of("Ivan", "Adel", "Danka", "Quasimodo"),
                Set.of("Ulrika", "Vierka", "Yasmina", "Zuzka"),
                Set.of("Wilson", "Chuck"));
        var s1 = new SirenieSpravy(bubliny, Set.of("Peter"));
        var s2 = new SirenieSpravy(bubliny, Set.of("Zuzka"));
        var s3 = new SirenieSpravy(bubliny, Set.of("Zuzka", "Peter"));
        var s4 = new SirenieSpravy(bubliny, Set.of("Peter", "Jano"));
        var s5 = new SirenieSpravy(bubliny, Set.of());
        for (var s : List.of(s1, s2, s3, s4, s5))
            System.out.println(
                    "informovani: " + s.informovani()
                            + ", \ttrvanie: " + s.trvanie()
                            + ", \tneinformovani: " + s.neinformovani());
    }
}
