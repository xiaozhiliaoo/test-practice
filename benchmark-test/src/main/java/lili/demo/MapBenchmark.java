package lili.demo;

import org.openjdk.jmh.annotations.Benchmark;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * from https://stackoverflow.com/questions/1811782/when-should-i-use-concurrentskiplistmap
 * how ti run :java -jar target/benchmarks.jar -bm avgt -f 1 -wi 5 -i 5 -t 1
 */
public class MapBenchmark {
    //循环次数
    static final int nCycles = 50000;
    //重复次数
    static final int nRep = 10;
    //数据量
    static final int dataSize = nCycles / 4;  //12500
    //数据
    static final List<String> data = new ArrayList<>(nCycles);  //50000
    //
    static final Map<String, String> conhashmap4get = new ConcurrentHashMap<>(3000, 0.5f, 10);//1500
    static final Map<String, String> conskipmap4get = new ConcurrentSkipListMap<>();

    static {
        // prepare data
        List<String> values = new ArrayList<>(dataSize);
        for (int i = 0; i < dataSize; i++) {
            values.add(UUID.randomUUID().toString());
        }
        // rehash data for all cycles
        for (int i = 0; i < nCycles; i++) {
            data.add(values.get((int) (Math.random() * dataSize)));
        }
        // rehash data for all cycles
        for (int i = 0; i < dataSize; i++) {
            String value = data.get((int) (Math.random() * dataSize));
            conhashmap4get.put(value, value);
            conskipmap4get.put(value, value);
        }
    }

    @Benchmark
    public void skipList_put() {
        for (int n = 0; n < nRep; n++) {
            Map<String, String> map = new ConcurrentSkipListMap<>();
            for (int i = 0; i < nCycles; i++) {
                String key = data.get(i);
                map.put(key, key);
            }
        }
    }

    @Benchmark
    public void skipListMap_get() {
        for (int n = 0; n < nRep; n++) {
            for (int i = 0; i < nCycles; i++) {
                String key = data.get(i);
                conskipmap4get.get(key);
            }
        }
    }

    @Benchmark
    public void hashMap_put() {
        for (int n = 0; n < nRep; n++) {
            Map<String, String> map = new ConcurrentHashMap<>(3000, 0.5f, 10);
            for (int i = 0; i < nCycles; i++) {
                String key = data.get(i);
                map.put(key, key);
            }
        }
    }

    @Benchmark
    public void hasMap_get() {
        for (int n = 0; n < nRep; n++) {
            for (int i = 0; i < nCycles; i++) {
                String key = data.get(i);
                conhashmap4get.get(key);
            }
        }
    }

    @Benchmark
    public void skipListMap_put1000_lru() {
        int sizeLimit = 1000;

        for (int n = 0; n < nRep; n++) {
            ConcurrentSkipListMap<String, String> map = new ConcurrentSkipListMap<>();

            for (int i = 0; i < nCycles; i++) {
                String key = data.get(i);
                String oldValue = map.put(key, key);

                if ((oldValue == null) && map.size() > sizeLimit) {
                    // not real lru, but i care only about performance here
                    map.remove(map.firstKey());
                }
            }
        }
    }


    @Benchmark
    public void hashMap_put1000_lru() {
        int sizeLimit = 1000;
        Queue<String> lru = new ArrayBlockingQueue<>(sizeLimit + 50);

        for (int n = 0; n < nRep; n++) {
            Map<String, String> map = new ConcurrentHashMap<>(3000, 0.5f, 10);

            lru.clear();
            for (int i = 0; i < nCycles; i++) {
                String key = data.get(i);
                String oldValue = map.put(key, key);

                if ((oldValue == null) && lru.size() > sizeLimit) {
                    map.remove(lru.poll());
                    lru.add(key);
                }
            }
        }
    }

}
