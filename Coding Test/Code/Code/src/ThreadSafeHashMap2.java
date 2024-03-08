import java.util.HashMap;
import java.util.Map;

public class ThreadSafeHashMap2<K, V>{

    private final Map<K, V> map;

    public ThreadSafeHashMap2(){
        this.map = new HashMap<>();
    }

    public synchronized void put(K key, V value){
        map.put(key, value);
    }

    public synchronized V get(K key){
        return map.get(key);
    }

    public synchronized void remove(K key){
        map.remove(key);
    }

}
