package datastructures.trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LogStructuredMergeTree<T extends Comparable<T>> {
        private static final int MEMTABLE_LIMIT = 100; // Example threshold for flushing

    private SortedMap<T, T> memtable;
    private List<SortedMap<T, T>> sstables; // Simulates on-disk storage
    private Lock lock;

    public LogStructuredMergeTree() {
        this.memtable = new TreeMap<>();
        this.sstables = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    // Inserts an element into the LSM-Tree
    public void insert(T key, T value) {
        lock.lock();
        try {
            memtable.put(key, value);

            if (memtable.size() >= MEMTABLE_LIMIT) {
                flush();
            }
        } finally {
            lock.unlock();
        }
    }

    // Searches for an element by key
    public Optional<T> search(T key) {
        lock.lock();
        try {
            // Search in the memtable first
            if (memtable.containsKey(key)) {
                return Optional.of(memtable.get(key));
            }

            // If not found, search in the SSTables (disk)
            for (SortedMap<T, T> sstable : sstables) {
                if (sstable.containsKey(key)) {
                    return Optional.of(sstable.get(key));
                }
            }

            return Optional.empty();
        } finally {
            lock.unlock();
        }
    }

    // Flushes the memtable to an SSTable (simulating on-disk storage)
    private void flush() {
        sstables.add(memtable);
        memtable = new TreeMap<>(); // Reset the memtable
    }

    // Merges smaller SSTables into a larger one (compaction)
    public void compact() {
        // Example of a basic compaction that merges all SSTables into one
        SortedMap<T, T> mergedSSTable = new TreeMap<>();
        for (SortedMap<T, T> sstable : sstables) {
            mergedSSTable.putAll(sstable);
        }
        sstables.clear();
        sstables.add(mergedSSTable);
    }

    // Print the current state of the LSM-Tree (for debugging)
    public void printState() {
        System.out.println("Memtable:");
        for (Map.Entry<T, T> entry : memtable.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }

        System.out.println("\nSSTables:");
        for (SortedMap<T, T> sstable : sstables) {
            for (Map.Entry<T, T> entry : sstable.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
            System.out.println("---");
        }
    }
}
