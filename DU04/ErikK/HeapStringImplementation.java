import java.util.Arrays;

public class HeapStringImplementation implements HeapStringInterface {
    String[] heap;
    private int len;

    public HeapStringImplementation() {
        heap = new String[10];
        len = 0;
    }

    public void realocate() {
        if (len >= heap.length) {
            String[] newHeap = new String[2 * heap.length];
            for(int i = 0; i < heap.length; i++)
                newHeap[i] = heap[i];
            heap = newHeap;
        }
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public String first() {
        if (len == 0){
            return null;
        }
        return heap[0];
    }

    @Override
    public String remove() {
        String ret = first();
        if (ret != null){
            heap[0] = heap[len - 1];
            heap[len - 1] = null;
            len--;
            if (len > 0) {
                heapDown(0);
            }
        }
        return ret;
    }

    @Override
    public void insert(String str) {
        heap[len] = str;
        heapUp(len);
        len++;
        realocate();
    }

    private void heapDown(int index){
        if (index < len){
            Integer vacsi = null;
            if ((index * 2 + 1 < len) && (heap[index].compareTo(heap[index * 2 + 1]) < 0)){
                vacsi = index * 2 + 1;
            }
            if (index * 2 + 2 < len && vacsi == null && heap[index].compareTo(heap[index * 2 + 2]) < 0){
                vacsi = index * 2 + 2;
            }
            if (index * 2 + 2 < len && vacsi != null && heap[index * 2 + 2].compareTo(heap[index * 2 + 1]) > 0){
                vacsi = index * 2 + 2;
            }
            if (vacsi != null){
                String pom = heap[index];
                heap[index] = heap[vacsi];
                heap[vacsi] = pom;
                heapDown(vacsi);
            }
        }
    }

    private void heapUp(int index){
        if (index > 0){
            Integer ind = (index - 1) / 2;
            if (ind >= 0 && heap[index].compareTo(heap[ind]) > 0){
                String pom = heap[index];
                heap[index] = heap[ind];
                heap[ind] = pom;
                heapUp(ind);
            }
        }
    }
}
