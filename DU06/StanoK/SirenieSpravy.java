import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SirenieSpravy {
    List<Set<String>> bubliny;
    Set<String> panikari;
    Set<String> informovani;
    Set<String> vsetci;
    int cas;

    public SirenieSpravy(List<Set<String>> bubliny, Set<String> panikari) {
        this.bubliny = new ArrayList<>(bubliny);
        this.panikari = new HashSet<>(panikari);
        this.informovani = new HashSet<>(panikari);
        this.cas = 0;
        this.vsetci = new HashSet<>();
        for(Set<String> mnoz : bubliny) {
            vsetci.addAll(mnoz);
        }
        while(true) {
            Set<String> novy_informovany = new HashSet<>(informovani);
            for(Set<String> mnoz : bubliny) {
                Set<String> prienik = mnoz.stream().filter(informovani::contains).collect(Collectors.toSet());
                if(prienik.size() > 0 && prienik.size() < mnoz.size()) {
                    novy_informovany.addAll(mnoz);
                }
            }
            if(novy_informovany.size() == informovani.size()) break;    //nebola ziadna zmena
            informovani = novy_informovany;
            cas += 1;
            //System.out.println(informovani);
        }
    }

    public int informovani() {
        return informovani.size();
    }

    public int trvanie() {
        return cas;
    }

    public int neinformovani() {
//        System.out.println(vsetci);
//        System.out.println(informovani);
        return  vsetci.size() - vsetci.stream().filter(informovani::contains).collect(Collectors.toSet()).size();
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
