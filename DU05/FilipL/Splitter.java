import java.lang.reflect.Array;
import java.util.*;

public class Splitter<T extends Comparable<T>> {            // ak treba, doplňte správnu hlavičku triedy
    List<T> l;
    public Splitter() {
        l = new ArrayList<>();
    }                    // prázdny konštruktor, iba inicializuje priestor na ukladanie
    public Splitter(Collection<T> items) {
        l = new ArrayList<>();
        l.addAll(items);
    } // konštruktor, ktorý pridá všetky prvky v kolekcii items
    // Collections sú až v prednáške 6, dovtedy si vpohode vystačíte s for-each cyklom

    public void add(T item) {
        l.add(item);
    }       // pridá jeden prvok
    public Collection<T> getAll() {
        return new ArrayList<>(l);
    } // vráti ľubovoľnú kolekciu (ArrayList/TreeSet/...) obsahujúcu všetky prvky v ľubovoľnom poradí

    public ArrayList<ArrayList<T>> split(ArrayList<T> splitPoints) {
        if (splitPoints == null) return null;

        ArrayList<T> splitPointsOrdered = new ArrayList<>(splitPoints);
        Collections.sort(splitPointsOrdered);

        for (int i = 0; i < splitPoints.size(); i++) {
            if (! splitPoints.get(i).equals(splitPointsOrdered.get(i))) {
                return null;
            }
        }

        ArrayList<ArrayList<T>> res = new ArrayList<>();
        for (int i = 0; i < splitPoints.size() + 1; i++) {
            res.add(new ArrayList<>());
        }

        if (splitPoints.size() == 0) {
            res.get(0).addAll(l);
            Collections.sort(res.get(0));
            return res;
        }

        for (T element : l) {
            for (int i = 0; i < splitPoints.size() + 1; i++) {
                if (i == 0) {
                    if (element.compareTo(splitPoints.get(i)) <= 0) {
                        res.get(0).add(element);
                        break;
                    }
                } else if (i != res.size() - 1) {
                    if (element.compareTo(splitPoints.get(i)) <= 0 && element.compareTo(splitPoints.get(i - 1)) > 0) {
                        res.get(i).add(element);
                        break;
                    }
                } else {
                    if (element.compareTo(splitPoints.get(i - 1)) > 0) {
                        res.get(i).add(element);
                    }
                }

            }
        }

        for (List<T> pole : res) {
            Collections.sort(pole);
        }

        return res;
    } // popísané nižšie

    public static void main(String[] args) {
        Splitter<Integer> s = new Splitter<Integer>();
        s.add(10);
        s.add(20);
        s.add(30);
        s.add(40);
        s.add(50);
        s.add(60);
        s.add(80);
        System.out.println(s.split(new ArrayList<>(List.of(25,55))));
    }
}