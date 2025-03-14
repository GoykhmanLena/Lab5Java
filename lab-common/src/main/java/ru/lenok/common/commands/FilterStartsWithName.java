package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;

import java.util.ArrayList;
import java.util.List;


public class FilterStartsWithName extends Command {
    LabWorkCollection collection;
    String name_part;

    public FilterStartsWithName(LabWorkCollection collection) {
        super("filter_starts_with_name name ", "вывести элементы, значение поля name которых начинается с заданной подстроки");
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        List keys = new ArrayList<>(collection.getMap().keySet());
        for (int i = 0; i < keys.size(); i++) {
            if (collection.getMap().get(keys.get(i)).getName().startsWith(name_part)) {
                System.out.println(collection.getMap().get(keys.get(i)));
            }
        }
    }

    @Override
    public void setArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидается ровно один аргумент");
        }
        this.name_part = args[1];
    }
}
