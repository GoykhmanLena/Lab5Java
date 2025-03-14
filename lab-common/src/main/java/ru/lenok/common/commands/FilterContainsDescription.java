package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;
import ru.lenok.common.models.LabWork;

import java.util.Hashtable;


public class FilterContainsDescription extends Command {
    LabWorkCollection collection;
    String descript_part;

    public FilterContainsDescription(LabWorkCollection collection) {
        super("filter_contains_description description", "вывести элементы, значение поля description которых содержит заданную подстроку");
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        Hashtable<String, LabWork> map = collection.getMap();
        for (String key : map.keySet()) {
            LabWork labWork = map.get(key);
            if (labWork.getDescription().contains(descript_part)) {
                System.out.println(key + " = " + labWork);
            }
        }
    }

    @Override
    public void setArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидается ровно один аргумент");
        }
        this.descript_part = args[1];
    }
}
