package ru.lenok.common.commands;

import lombok.AllArgsConstructor;
import ru.lenok.common.LabWorkCollection;
import ru.lenok.common.models.LabWork;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class PrintAscending extends Command {
    LabWorkCollection collection;

    public PrintAscending(LabWorkCollection collection) {
        super("print_ascending", "вывести элементы коллекции в порядке возрастания");
        this.collection = collection;
    }

    //    @Override
    public void execute2(String arg) {
        List<LabWork> col = new ArrayList<>(collection.getMap().values());
        Collections.sort(col);
        for (LabWork labWork : col) {
            System.out.println(labWork);
        }
    }

    public void execute(String arg) {
        List<LabWorkEntry> entryList = new ArrayList<>();
        for (Map.Entry<String, LabWork> mapEntry : collection.getMap().entrySet()) {
            entryList.add(new LabWorkEntry(mapEntry.getKey(), mapEntry.getValue()));

        }
        Collections.sort(entryList);
        StringBuilder result = new StringBuilder();
        for (LabWorkEntry labWorkEntry : entryList) {
            String key = labWorkEntry.key;
            result.append(key).append(" = ").append(labWorkEntry.labWork).append("\n");
        }
        System.out.println(result);
    }

    @AllArgsConstructor
    private static class LabWorkEntry implements Comparable<LabWorkEntry> {
        String key;
        LabWork labWork;

        @Override
        public int compareTo(LabWorkEntry labWorkEntry) {
            return this.labWork.getName().compareTo(labWorkEntry.labWork.getName());
        }
    }
}
