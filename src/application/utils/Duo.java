package application.utils;

public class Duo<K, V> {
    private final K first;
    private final V second;

    public Duo(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Duo{" + "first=" + first + ", second=" + second + '}';
    }
}