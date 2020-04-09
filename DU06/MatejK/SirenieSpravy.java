import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class SirenieSpravy {
    List<Set<String>> bubliny;
    Set<String> panikari;
    Set<String> informovani; //ti co uz vedia o sprave

    public SirenieSpravy(List<Set<String>> bubliny, Set<String> panikari){
        this.bubliny = bubliny;
        this.panikari = panikari;
        informovani = new HashSet<String>(panikari);
    }

    public int informovani(){
        informovani = new HashSet<>(panikari); //treba resetnut
        if(informovani.size() == 0){
            return 0;
        }
        Set<String> newInformovani = new HashSet<>(informovani);

        while(true){
            for(Set<String> bublina : bubliny){
                for(String informovany : informovani){  //nemozem menit informovani ked ju prechadzam
                    if(bublina.contains(informovany)){
                        newInformovani.addAll(bublina);
                        break; //uz tam taky je, nemusim dalej hladat
                    }
                }
            }
            if(newInformovani.size() == informovani.size()){ //uz sa dalej nesiri
                return informovani.size();
            }
            informovani = new HashSet<>(newInformovani);
        }
    }

    public int trvanie(){
        informovani = new HashSet<>(panikari); //treba resetnut
        if(informovani.size() == 0){
            return 0;
        }
        int cas = 0;

        Set<String> newInformovani = new HashSet<>(informovani);

        while(true){
            for(Set<String> bublina : bubliny){
                for(String informovany : informovani){  //nemozem menit informovani ked ju prechadzam
                    if(bublina.contains(informovany)){
                        newInformovani.addAll(bublina);
                        break; //uz tam taky je, nemusim dalej hladat
                    }
                }
            }
            if(newInformovani.size() == informovani.size()){ //uz sa dalej nesiri
                return cas;
            }
            cas++;
            informovani = new HashSet<>(newInformovani);
        }
    }

    public int neinformovani(){
        return pocetVsetkychLudi() - informovani();
    }

    private int pocetVsetkychLudi(){
        Set<String> ludia = new HashSet<>();
        for(Set<String> bublina : bubliny){
            ludia.addAll(bublina);
        }
        ludia.addAll(panikari);
        return ludia.size();
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
