import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Splitter <T extends Comparable<T>>{
    ArrayList<T> list;

    public Splitter() { // prázdny konštruktor, iba inicializuje priestor na ukladanie
        list = new ArrayList<T>();
    }

    public Splitter(Collection<T> items) {// konštruktor, ktorý pridá všetky prvky v kolekcii items
        list = new ArrayList<T>();
        for(T item: items){
            list.add(item);
        }
    }

    public void add(T item) { // pridá jeden prvok
        list.add(item);
    }
    public Collection<T> getAll() {// vráti ľubovoľnú kolekciu (ArrayList/TreeSet/...) obsahujúcu všetky prvky v ľubovoľnom poradí
        ArrayList<T> res = new ArrayList<>();
        res.addAll(list);
        return res;
    }
    public ArrayList<ArrayList<T>> split(ArrayList<T> splitPoints) {
        if(splitPoints == null || isCorrect(splitPoints) == false){
            return null;
        }
        ArrayList<T> current = new ArrayList<T>();
        ArrayList<ArrayList<T>> res = new ArrayList<ArrayList<T>>();
        if(splitPoints.size() == 0){
            insertSorted(getAll(), res);
            return res;
        }

        for(T item : list){
            if(item.compareTo(splitPoints.get(0)) <= 0){
                current.add(item);
            }
        }
        insertSorted(current, res);
        for (int i = 0; i < splitPoints.size()-1; i++) {
            for (T item : list) {
                if (item.compareTo(splitPoints.get(i)) > 0 && item.compareTo(splitPoints.get(i+1)) <= 0) {
                    current.add(item);
                }
            }
            insertSorted(current, res);
        }
        for(T item:list){
            if(item.compareTo(splitPoints.get(splitPoints.size()-1)) > 0){
                current.add(item);
            }
        }
        insertSorted(current, res);
        return res;
    }

    private void insertSorted(Collection<T> from, ArrayList<ArrayList<T>> to){
        ArrayList<T> tmp = new ArrayList<>();
        tmp.addAll(from);
        Collections.sort(tmp);
        to.add(tmp);

        from.clear();
    }

    private boolean isCorrect(ArrayList<T> a){
        for (int i = 0; i < a.size() - 1; i++) {
            if(a.get(i).compareTo(a.get(i+1)) > 0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Splitter<Integer> s = new Splitter<>(List.of(30, 20, 10, 60, 50, 40));
        System.out.println(s.getAll());
        s.add(80);
        s.add(60);  // vloženie prvku druhý krát
        System.out.println(s.getAll());
        System.out.println(s.split(new ArrayList<>(List.of(25, 55))));
        System.out.println(s.split(new ArrayList<>(List.of(20, 42, 43, 44, 75))));  // oddeľovač 20 je rovnaký ako jeden z prvkov
        System.out.println(s.split(new ArrayList<>(List.of(-15, -5, 15, 75, 85, 85))));  // dva oddeľovače 85 a 85 sú rovnaké
        System.out.println(s.split(new ArrayList<>(List.of(20, 42, 43, 44, 75))));
    }
}
