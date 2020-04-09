import java.util.*;

public class Nemocnica {
    public static Comparator<Doktor> comparatorDoktorov = (d1, d2) -> {
        if (d1.getPrax() > d2.getPrax()) return -1;
        
        else if (d1.getPrax() == d2.getPrax() && d1.getVek() < d2.getVek()) return -1;

        else if (d1.getPrax() == d2.getPrax() && d1.getVek() == d2.getVek())
            return d1.getMeno().compareTo(d2.getMeno());

        return 1;
    };

    public static Map<String, Set<Doktor>> doktoriNaOddeleniach(Map<Doktor, String> doktori) {
        Map<String, Set<Doktor>> hm = new HashMap<>();

        for(Doktor d: doktori.keySet()) {
            if(!hm.containsKey(doktori.get(d))) {
                Set<Doktor> s = new HashSet<>();
                s.add(d);
                hm.put(doktori.get(d), s);
            }
            else hm.get(doktori.get(d)).add(d);
        }

        return hm;
    }

    public static Map<Set<Doktor>, List<Doktor>> rozpisyOddeleni(Map<String, Set<Doktor>> oddelenia) {
        Map<Set<Doktor>, List<Doktor>> hm = new HashMap<>();

        for(String k: oddelenia.keySet()) {
            List<Doktor> l = new ArrayList<>();
            l.addAll(oddelenia.get(k));
            l.sort(comparatorDoktorov);
            hm.put(oddelenia.get(k), l);
        }

        return hm;
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