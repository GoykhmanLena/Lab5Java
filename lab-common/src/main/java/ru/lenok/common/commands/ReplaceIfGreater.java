package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;
import ru.lenok.common.models.LabWork;


public class ReplaceIfGreater extends CommandWithElement {
    LabWorkCollection collection;
    Long id;
    String key;

    public ReplaceIfGreater(LabWorkCollection collection, boolean interactive) {
        super("replace_if_greater null {element}", "заменить значение по ключу, если новое значение больше старого", interactive);
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
        LabWork elem = this.currentElement();
        if (collection.getMap().get(key).compareTo(elem) < 0) {
            collection.put(key, elem);
        }
    }

    public boolean isFinished() {
        return super.isfin;
    }
}
