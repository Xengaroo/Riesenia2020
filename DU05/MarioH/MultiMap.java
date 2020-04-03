import java.util.*;

public class MultiMap<K, V> {

    private int maxCapacity;
    private int numberOfElements;
    private Map<K, List<V>> map;

    public MultiMap(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        numberOfElements = 0;
        map = new LinkedHashMap<>();
    }

    public int numKeys() {
        return map.keySet().size();
    }

    public int numValues() {
        return numberOfElements;
    }

    public V[] get(int idx_k) {
        if(idx_k >= numKeys() || idx_k < 0) return null;

        Iterator<K> it = map.keySet().iterator();
        K key = null;

        for(int i = 0; i <= idx_k; i++) {
            key = it.next();
        }

        List<V> elements = map.get(key);

        return (V[]) elements.toArray();
    }

    public V[] get(K k) {
        if(!map.containsKey(k)) return null;

        return (V[]) map.get(k).toArray();
    }

    public void put(K k, V v) {
        if(numberOfElements == maxCapacity) return;
        if(map.containsKey(k)) map.get(k).add(v);
        else {
            List<V> value = new ArrayList<V>();
            value.add(v);
            map.put(k, value);
        }
        numberOfElements++;
    }

    public V[] removeKey(K k) {
        if(!map.containsKey(k)) return null;

        numberOfElements -= map.get(k).size();
        return (V[]) map.remove(k).toArray();
    }

    public void removeValue(V v) {
        for(K k: map.keySet()) {
            List<V> l = map.get(k);
            while(l.remove(v)) numberOfElements--;
            if(map.get(k).size() == 0) map.remove(k);
        }
    }

    public static void main(String[] args) {
        MultiMap<String, Integer> mm = new MultiMap<String, Integer>(10);

        mm.put("kluc1", 1);
        mm.put("kluc1", -1);
        mm.put("kluc2", 2);
        mm.put("kluc2", 222);
        mm.put("kluc2", 22);
        mm.put("kluc3", 3);
        mm.put("kluc3", 2);
        mm.put("kluc3", 3);

        /* mapa obsahuje tieto hodnoty a v tomto poradi
          k: "kluc1", v=[1,-1]
          k: "kluc2", v=[2,222,22]
          k: "kluc3", v=[3,2,3]
        */

        System.out.println(Arrays.toString(mm.get(1))); // [2, 222, 22]
        System.out.println(Arrays.toString(mm.get("kluc1"))); // [1, -1]
        System.out.println(mm.numKeys()); // 3
        System.out.println(mm.numValues()); // 8

        mm.removeValue(2);
        System.out.println(Arrays.toString(mm.get(0))); // [1, -1]
        System.out.println(Arrays.toString(mm.get(1))); // [222, 22]
        System.out.println(Arrays.toString(mm.get(2))); // [3, 3]

        mm.removeKey("kluc1");  // cely riadok mazeme, takze kluce sa poposuvaju
        System.out.println(Arrays.toString(mm.get(0))); // [222, 22]
        System.out.println(Arrays.toString(mm.get(1))); // [3, 3]
        System.out.println(Arrays.toString(mm.get(2))); // null

        System.out.println(Arrays.toString(mm.get("kluc1"))); // null
        System.out.println(Arrays.toString(mm.get("kluc2"))); // [222, 22]
        System.out.println(Arrays.toString(mm.get("kluc3"))); // [3, 3]

        System.out.println(mm.numKeys()); // 2
        System.out.println(mm.numValues()); // 4
    }
}
