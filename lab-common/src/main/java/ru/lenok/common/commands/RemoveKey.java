package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;

public class RemoveKey extends Command {
    LabWorkCollection collection;
    String key;

    public RemoveKey(LabWorkCollection collection) {
        super("remove_key null", "удалить элемент из коллекции по его ключу");
        this.collection = collection;
    }

    public void setArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидается ровно один аргумент");
        }
        this.key = args[1];
        if (!collection.getMap().containsKey(key)) {
            throw new IllegalArgumentException("Нет элемента с заданным ключом");
        }
    }

    @Override
    public void execute(String arg) {
        collection.remove(key);
    }
}
