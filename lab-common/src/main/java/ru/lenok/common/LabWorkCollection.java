package ru.lenok.common;

import lombok.Data;
import ru.lenok.common.models.LabWork;

import java.util.Hashtable;

@Data
public class LabWorkCollection {
    public static long idCounter;
    Hashtable<String, LabWork> map;

    public LabWorkCollection(Hashtable<String, LabWork> map) {
        this.map = map;
    }

    public LabWorkCollection() {
        this.map = new Hashtable<>();
    }

    public void put(String key, LabWork lab) {
        map.put(key, lab);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public int length() {
        return map.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String key : map.keySet()) {
            LabWork labWork = map.get(key);
            result.append(key).append(" = ").append(labWork).append("\n");
        }
        return result.toString();
    }

    public void clear_collection() {
        map.clear();
    }
}
