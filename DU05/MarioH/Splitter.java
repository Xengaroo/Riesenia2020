import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Splitter<T extends Comparable<T>>{            // ak treba, doplňte správnu hlavičku triedy
    private List<T> l;

    public Splitter() {
        l = new LinkedList<>();
    }                    // prázdny konštruktor, iba inicializuje priestor na ukladanie

    public Splitter(Collection<T> items) {
        l = new LinkedList<>();
        for(T item: items) {
            add(item);
        }
    } // konštruktor, ktorý pridá všetky prvky v kolekcii items

    // Collections sú až v prednáške 6, dovtedy si vpohode vystačíte s for-each cyklom

    public void add(T item) {
        int i = 0;
        while(i < l.size() && l.get(i).compareTo(item) < 0) i++;
        l.add(i, item);
    }       // pridá jeden prvok

    public Collection<T> getAll() {
        ArrayList<T> res =  new ArrayList<>(l);
        return res;
    } // vráti ľubovoľnú kolekciu (ArrayList/TreeSet/...) obsahujúcu všetky prvky v ľubovoľnom poradí

    public ArrayList<ArrayList<T>> split(ArrayList<T> splitPoints) {
        ArrayList<ArrayList<T>> result = new ArrayList<>();
        if(splitPoints == null) {
            return null;
        }
        for(int i = 0; i < splitPoints.size() - 1; i++) {
            if(splitPoints.get(i).compareTo(splitPoints.get(i+1)) > 0) return null;
        }

        if(splitPoints.size() == 0) {
            ArrayList<T> interval = new ArrayList<>();
            for(T e: l) {
                interval.add(e);
            }
            result.add(interval);
            return result;
        }

        for(int i = 0; i <= splitPoints.size(); i++) {
            if(i == 0) {
                ArrayList<T> interval = new ArrayList<>();
                for(T e: l) {
                    if(e.compareTo(splitPoints.get(i)) <= 0) {
                        interval.add(e);
                    }
                }
                result.add(interval);
            }
            else if(i == splitPoints.size()) {
                ArrayList<T> interval = new ArrayList<>();
                for(T e: l) {
                    if(e.compareTo(splitPoints.get(i-1)) > 0) {
                        interval.add(e);
                    }
                }
                result.add(interval);
            }
            else {
                ArrayList<T> interval = new ArrayList<>();
                for(T e: l) {
                    if(e.compareTo(splitPoints.get(i)) <= 0 && e.compareTo(splitPoints.get(i-1)) > 0) {
                        interval.add(e);
                    }
                }
                result.add(interval);
            }
        }


        return result;
    } // popísané nižšie

    public static void main(String[] args) {
        Splitter<Integer> s = new Splitter<>(List.of(30, 20, 10, 60, 50, 40));
        System.out.println(s.getAll());
        // [10, 20, 30, 40, 50, 60]   (môžu byť v ľubovoľnom poradí)

        s.add(80);
        s.add(60);  // vloženie prvku druhý krát
        System.out.println(s.getAll());
        // [10, 20, 30, 40, 50, 60, 60, 80]   (môžu byť v ľubovoľnom poradí)

        System.out.println(s.split(new ArrayList<>(List.of(25, 55))));
        // [[10, 20], [30, 40, 50], [60, 60, 80]]   (musia byt usporiadané)

        System.out.println(s.split(new ArrayList<>(List.of(20, 42, 43, 44, 75))));  // oddeľovač 20 je rovnaký ako jeden z prvkov
        // [[10, 20], [30, 40], [], [], [50, 60, 60], [80]]   (tretí a štvrtý sú prázdne zoznamy, nie null!)

        System.out.println(s.split(new ArrayList<>(List.of(-15, -5, 15, 75, 85, 85))));  // dva oddeľovače 85 a 85 sú rovnaké
        // [[], [], [10], [20, 30, 40, 50, 60, 60], [80], [], []]   (prázdne zoznamy vedia byť aj na začiatku či konci)

        System.out.println(s.split(null));  // nesprávny vstup (oddeľovače nie sú správne usporiadané), vráti null
    }
}