import java.util.*;
import java.util.zip.DeflaterOutputStream;

public class Nemocnica {

    public static Comparator<Doktor> comparatorDoktorov = (d1, d2) -> compare(d1,d2);

    public static int compare(Doktor o1, Doktor o2) {
        if(o1.getPrax() > o2.getPrax()){
            return -1;
        }
        if(o2.getPrax() > o1.getPrax()){
            return 1;
        }
        if(o1.getVek() < o2.getVek()){
            return -1;
        }
        if(o2.getVek() < o1.getVek()){
            return 1;
        }
        return o1.getMeno().compareTo(o2.getMeno());
    }

    public static Map<String, Set<Doktor>> doktoriNaOddeleniach(Map<Doktor, String> doktori){
        Map<String, Set<Doktor>> res = new HashMap<>();
        for(Doktor doktor : doktori.keySet()){
            String oddelenie = doktori.get(doktor);
            if(res.get(oddelenie) == null){
                res.put(oddelenie, new HashSet<Doktor>());
            }
            res.get(oddelenie).add(doktor);
        }
        return res;
    }

    public static Map<Set<Doktor>, List<Doktor>> rozpisyOddeleni(Map<String, Set<Doktor>> oddelenia){
        Map<Set<Doktor>, List<Doktor>> res = new HashMap<>();
        for(Set<Doktor> mnozDoktorov : oddelenia.values()){
            List<Doktor> listDoktorov = new ArrayList<Doktor>(mnozDoktorov);
            listDoktorov.sort(comparatorDoktorov);
            res.put(mnozDoktorov, listDoktorov);
        }
        return res;
    }

    public static void main(String[] args) {
        Map<Doktor, String> doktori = Map.of(  // mapa doktor -> oddelenie
                new Doktor("Rudolf Uzasny", 42, 18),   "urgent",
                new Doktor("Richard Zdravy", 42, 18),  "urgent",
                new Doktor("Anna Sikovna", 32, 8),     "urgent",
                new Doktor("Maria Injekciova", 36, 8), "urgent",
                new Doktor("Marek Koronovic", 76, 39), "imunologia",
                new Doktor("Alojz Cistic", 26, 1),     "imunologia",
                new Doktor("Martina Bozska", 34, 9),   "imunologia"
        );

        Map<String, Set<Doktor>> oddelenia = doktoriNaOddeleniach(doktori);
        System.out.println(oddelenia);
/*
{
 imunologia=[(Alojz Cistic,v=26,p=1),
             (Martina Bozska,v=34,p=9),
             (Marek Koronovic,v=76,p=39)
            ],
 urgent=[(Richard Zdravy,v=42,p=18),
         (Maria Injekciova,v=36,p=8),
         (Anna Sikovna,v=32,p=8),
         (Rudolf Uzasny,v=42,p=18)
        ]
}
*/

        Map<Set<Doktor>, List<Doktor>> rozpisy = rozpisyOddeleni(oddelenia);
        System.out.println(rozpisy);
/*
{
 {(Alojz Cistic,v=26,p=1), (Martina Bozska,v=34,p=9), (Marek Koronovic,v=76,p=39)}:      // mnozina doktorov na imunologii
     [(Marek Koronovic,v=76,p=39), (Martina Bozska,v=34,p=9), (Alojz Cistic,v=26,p=1)],  // rozpis sluzieb na imunologii
 {(Richard Zdravy,v=42,p=18), (Maria Injekciova,v=36,p=8), (Anna Sikovna,v=32,p=8), (Rudolf Uzasny,v=42,p=18)}:     // mnozina doktorov na urgente
     [(Richard Zdravy,v=42,p=18), (Rudolf Uzasny,v=42,p=18), (Anna Sikovna,v=32,p=8), (Maria Injekciova,v=36,p=8)]  // rozpis sluzieb na urgente
}
*/
    }

}

