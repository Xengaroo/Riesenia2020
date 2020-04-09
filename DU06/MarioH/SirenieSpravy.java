import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SirenieSpravy {
    public List<Set<String>> bubliny;
    public Set<String> panikari;

    public SirenieSpravy(List<Set<String>> bubliny, Set<String> panikari) {
        this.bubliny = new ArrayList<>();
        for(Set<String> s: bubliny) {
            Set<String> set = new HashSet<>();
            for(String str: s) {
                set.add(str);
            }
            this.bubliny.add(set);
        }

        this.panikari = new HashSet<>();
        this.panikari.addAll(panikari);
    }


    public int informovani() {
        boolean pridany = true;

        Set<String> panikaria = new HashSet<>();
        panikaria.addAll(panikari);

        while(pridany) {
            pridany = false;

            for(Set<String> c: bubliny) {
                boolean jePanikar = false;
                boolean jeNepanikar = false;

                for(String i: c) {
                    if(panikaria.contains(i)) jePanikar = true;
                    else jeNepanikar = true;
                }

                if(jeNepanikar && jePanikar) {
                    panikaria.addAll(c);
                    pridany = true;
                }
            }

        }
        return panikaria.size();
    }

    public int trvanie() {
        boolean pridany = true;
        int pocetDni = 0;

        Set<String> panikaria = new HashSet<>();
        Set<String> novy = new HashSet<>();
        panikaria.addAll(panikari);

        while(pridany) {
            pridany = false;

            for(Set<String> c: bubliny) {
                boolean jePanikar = false;
                boolean jeNepanikar = false;

                for(String i: c) {
                    if(panikaria.contains(i)) jePanikar = true;
                    else jeNepanikar = true;

                }

                if(jeNepanikar && jePanikar) {
                    novy.addAll(c);
                    pridany = true;
                }
            }

            panikaria.addAll(novy);
            if(pridany) pocetDni++;
        }
        return pocetDni;
    }

    public int neinformovani() {
        boolean pridany = true;

        Set<String> panikaria = new HashSet<>();
        panikaria.addAll(panikari);

        while(pridany) {
            pridany = false;

            for(Set<String> c: bubliny) {
                boolean jePanikar = false;
                boolean jeNepanikar = false;

                for(String i: c) {
                    if(panikaria.contains(i)) jePanikar = true;
                    else jeNepanikar = true;
                }

                if(jeNepanikar && jePanikar) {
                    panikaria.addAll(c);
                    pridany = true;
                }
            }

        }

        Set<String> vsetci = new HashSet<>();
        for(Set<String> s: bubliny) {
            vsetci.addAll(s);
        }

        int pocet = 0;
        for(String s: vsetci) {
            if(!panikaria.contains(s)) pocet++;
        }

        return pocet;
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
