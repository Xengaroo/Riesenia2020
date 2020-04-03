import java.util.Arrays;


public class MultiMap<K, V> {
    K[] keys;
    V[] values;
    Integer[] valueKeys; //indexy klucov
    int numKeys;
    int numValues;

    public MultiMap(int maxCapacity) {
        keys = (K[]) new Object[maxCapacity];
        values = (V[]) new Object[maxCapacity];
        valueKeys = new Integer[maxCapacity];
    }

    public int numKeys() {
        return numKeys;
    }

    public int numValues() {
        return numValues;
    }

    public V[] get(int idx_k) {
        if(idx_k < 0 || idx_k >= numKeys){
            return null;
        }
        int count = 0; //kolko ich je
        for (int i = 0; i < numValues; i++) {
            if(valueKeys[i] == idx_k){
                count++;
            }
        }
        V[] res = (V[]) new Object[count];
        int resIndex = 0;
        for (int i = 0; i < numValues; i++) {
            if(valueKeys[i] == idx_k){
                res[resIndex++] = values[i];
            }
        }
        return res;
    }

    public V[] get(K k) {
        if(isNewKey(k)){
            return null;
        }

        int count = 0; //kolko ich je
        for (int i = 0; i < numValues; i++) {
            if(keys[valueKeys[i]].equals(k)){
                count++;
            }
        }
        V[] res = (V[]) new Object[count];
        int resIndex = 0;
        for (int i = 0; i < numValues; i++) {
            if(keys[valueKeys[i]].equals(k)){
                res[resIndex++] = values[i];
            }
        }
        return res;
    }

    public void put(K k, V v) {
        if(isNewKey(k)){
            keys[numKeys++] = k;
        }
        int keyIndex = 0;
        for (int i = 0; i < numKeys; i++) {
            if(keys[i].equals(k)){
                keyIndex = i;
                break;
            }
        }
        values[numValues++] = v;
        valueKeys[numValues-1] = keyIndex;
    }

    public V[] removeKey(K k) {
        int keyIndex = 0;
        for (int i = 0; i < numKeys; i++) {
            if(keys[i].equals(k)){
                keyIndex = i;
                break;
            }
        }
        int count = 0; //kolko ich bude
        for (int i = 0; i < numValues; i++) {
            if (valueKeys[i] == keyIndex) {
                count++;
            }
        }
        V[] res = (V[]) new Object[count];
        int resIndex = 0;
        int i = 0;
        while(i < numValues) {
            if(valueKeys[i] == keyIndex){    //ked shift, tak neposun i
                res[resIndex++] = values[i];
                shift(i, values);
                shift(i, valueKeys); //posunie to tam, tak sa aj vymaze ten i-ty prvok
                numValues--;
            }
            else{
                i++;
            }
        }
        shift(keyIndex, keys);
        numKeys--;
        lowerValueKeys(keyIndex);
        return res;
    }

    public void removeValue(V v) {
        int i = 0;
        while(i < numValues) {
            boolean posun = true; //ked shift, tak neposun i
            if(values[i].equals(v)){
                int keyIndex = valueKeys[i];
                shift(i,values);
                shift(i,valueKeys);
                numValues--;

                if(hasValue(keyIndex) == false){
                    shift(keyIndex, keys);
                    numKeys--;
                    lowerValueKeys(keyIndex);
                }
                posun = false;
            }
            if(posun){
                i++;
            }
        }
    }

    private boolean isNewKey(K key){
        for (int i = 0; i < numKeys; i++) {
            if(keys[i].equals(key)){
                return false;
            }
        }
        return true;
    }

    private void shift(int where, Object[] array){
        int i = where;
        while(i < array.length-1 && array[i+1] != null){
            array[i] = array[i+1];
            i++;
        }
        if(i < array.length-1){
            array[i] = null;
        }
    }

    private void lowerValueKeys(int keyIndex){
        for (int i = 0; i < numValues; i++) {
            if(valueKeys[i] >= keyIndex){
                valueKeys[i] -= 1;
            }
        }
    }

    private boolean hasValue(int keyIndex){
        for (int i = 0; i < numKeys; i++) {
            if(valueKeys[i] == keyIndex){
                return true;
            }
        }
        return false;
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
