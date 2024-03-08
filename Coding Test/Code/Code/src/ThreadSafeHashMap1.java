import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeHashMap1<K,V> {
    private final Map<K, V> map;

    public ThreadSafeHashMap1(){
        this.map = new ConcurrentHashMap<>();
    }

    public void put(K key, V value){
        map.put(key, value);
    }

    public V get(K key){
        return map.get(key);
    }

    public void remove(K key){
        map.remove(key);
    }

}
