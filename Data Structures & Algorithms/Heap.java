import java.util.*;

public class Heap<T extends Comparable<T>> {

    private List<T> array;

    public Heap() {
        array = new ArrayList<T>();
    }

    private void swap(int x, int y) {
        T temp = array.get(x);
        array.set(x, array.get(y));
        array.set(y, temp);
    }

    private void sink(int idx) {
        int left = idx*2;
        int right = idx*2+1;
        if (left < array.size() && right < array.size()) {
            int larger = left;
            if (array.get(left).compareTo(array.get(right)) < 0) {
                larger = right;
            }
            if (array.get(idx).compareTo(array.get(larger)) < 0) {
                swap(idx, larger);
            }
            sink(larger);
        } else if (left < array.size() && array.get(idx).compareTo(array.get(left)) < 0) {
            swap(idx, left);
            sink(left);
        } else if (right < array.size() && array.get(idx).compareTo(array.get(right)) < 0) {
            swap(idx, right);
            sink(right);
        }
    }

    private void swim(int idx) {
        int parent = idx / 2;
        if (parent > 0 && array.get(parent).compareTo(array.get(idx)) < 0) {
            swap(parent, idx);
            swim(parent);
        }
    }

    public T peekMax() {
        return array.get(1);
    }

    public T popMax() {
        T maxVal = array.get(1);
        T temp = array.get(array.size()-1);
        array.remove(array.size()-1);
        if (array.size() == 1) {
            array.remove(0);
        } else {
            array.set(1, temp);
            sink(1);
        }
        return maxVal;
    }

    public void insert(T val) {
        if (array.isEmpty()) {
            //fist slot not used, data start from index 1
            array.add(val);
        }
        array.add(val);
        swim(array.size()-1);
    }

    public boolean empty() {
        return array.size() < 2;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(); 
        List<Integer> testInput = Arrays.asList(new Integer[]{1,4,5,2,8,7,10});
        for (Integer val : testInput) {
            heap.insert(val);
        }
        System.out.println("print in order");
        while (!heap.empty()) {
            System.out.printf("%d ", heap.popMax().intValue());
        }
    }

}
