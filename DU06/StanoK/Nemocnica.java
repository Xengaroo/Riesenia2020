import java.util.*;

public class Nemocnica {

    public static Comparator<Doktor> comparatorDoktorov = (fst, snd) -> {
        if(fst.getPrax() != snd.getPrax()) return Integer.compare(snd.getPrax(), fst.getPrax());
        if(fst.getVek() != snd.getVek()) return Integer.compare(fst.getVek(), snd.getVek());
        return fst.getMeno().compareTo(snd.getMeno());
        };

    public static Map<String, Set<Doktor>> doktoriNaOddeleniach(Map<Doktor, String> doktori) {
        Map<String, Set<Doktor>> vysl = new HashMap<>();
        for(Map.Entry<Doktor, String> es : doktori.entrySet()) {
            if(!vysl.containsKey(es.getValue())) {
                Set<Doktor> temp = new HashSet<>();
                temp.add(es.getKey());
                vysl.put(es.getValue(), temp);
            } else vysl.get(es.getValue()).add(es.getKey());
        }
        return vysl;
    }

    public static Map<Set<Doktor>, List<Doktor>> rozpisyOddeleni(Map<String, Set<Doktor>> oddelenia){
        Map<Set<Doktor>, List<Doktor>> vysl = new HashMap<>();
        for(Set<Doktor> val : oddelenia.values()) {
            List<Doktor> list = new ArrayList<>(val);
            list.sort(comparatorDoktorov);
            vysl.put(val, list);
        }
        return vysl;
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
