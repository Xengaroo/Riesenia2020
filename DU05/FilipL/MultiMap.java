import java.lang.reflect.Array;
import java.util.*;

public class MultiMap<K, V> {
    K[] keys;
    V[][] vals;

    public MultiMap(int maxCapacity) {
        keys = (K[]) new Object[maxCapacity];
        vals = (V[][]) new Object[maxCapacity][];

        Arrays.fill(vals, (V[]) new Object[0]);
    }

    public int numKeys() {
        Set<K> kSet = new HashSet<>();
        for (K k : keys) {
            if (k != null) kSet.add(k);
        }
        return kSet.size();
    }

    public int numValues() {
        int res = 0;
        for (V[] valArray : vals) {
            if (valArray != null)
                res += valArray.length;
        }
        return res;
    }

    public V[] get(int idx_k) {
        if (keys[idx_k] == null) return null;

        return vals[idx_k];
    }

    public V[] get(K k) {
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == k) {
                return vals[i];
            }
        }

        return null;
    }

    public Integer getPosOfK(K k) {
        if (k == null) return null;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == k) {
                return i;
            }
        }
        return null;
    }

    public void put(K k, V v) {
        Integer posOfK = getPosOfK(k);

        V[] values;
        V[] new_values;
        if (posOfK != null) { //k uz bolo pridane v keys
            values = vals[posOfK];
            new_values = (V[]) new Object[values.length + 1];
            for (int i = 0; i < values.length; i ++) {
                new_values[i] = values[i];
            }
        } else {
            new_values = (V[]) new Object[1];
            for (int i = 0; i < keys.length; i ++) {
                if (keys[i] == null) {
                    posOfK = i;
                    break;
                }
            }
            keys[posOfK] = k;
        }

        new_values[new_values.length - 1] = v;
        vals[posOfK] = new_values;
    }

    public V[] removeKey(K k) {
        if (get(k) == null) return null;
        V[] values = (V[]) new Object[get(k).length];

        V[] temp = get(k);

        for (int i = 0; i < temp.length; i++) {
            values[i] = temp[i];
        }

        int pos = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] == k) {
                pos = i;
                break;
            }
        }

        K[] new_keys = (K[]) new Object[keys.length];
        int index = 0;
        for (int i = 0; i < keys.length; i++) {
            if (i != pos) {
                new_keys[index] = keys[i];
                index++;
            }
        }

        keys = new_keys;

        V[][] new_vals = (V[][]) new Object[vals.length][];
        index = 0;
        for (int i = 0; i < vals.length; i++) {
            if (i != pos) {
                new_vals[index] = vals[i];
                index++;
            }
        }
        vals = new_vals;

        return values;
    }

    public void removeValue(V v) {
        for (int valArray = 0; valArray < vals.length; valArray++) {
            if (vals[valArray] == null) break;
            for (int val = 0; val < vals[valArray].length; val++) {
                if (vals[valArray][val] == v) {
                    vals[valArray] = deleteValue(vals[valArray], val);
                    val--;
                }
            }
        }

        List<Integer> posOfKeysToDelete = new ArrayList<>();

        for (int i = 0; i < vals.length; i++) {
            if (vals[i] == null) break;
            if (vals[i].length == 0) {
                posOfKeysToDelete.add(i);
            }
        }

        for (Integer i : posOfKeysToDelete) {
            removeKey(keys[i]);
        }
    }

    public V[] deleteValue(V[] p, int pos) {
        V[] new_vals = (V[]) new Object[p.length - 1];
        int index = 0;
        for (int i = 0; i < p.length; i++) {
            if (i != pos) {
                new_vals[index] = p[i];
                index++;
            }
        }
        return new_vals;
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
