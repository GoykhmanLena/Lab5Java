package ru.lenok.common.commands;


import ru.lenok.common.LabWorkCollection;


public class Insert extends CommandWithElement {
    String key;
    LabWorkCollection collection;


    public Insert(LabWorkCollection collection, boolean interactive) {
        super("insert null {element}", "добавить новый элемент с заданным ключом", interactive);
        this.collection = collection;
    }

    @Override
    public void setArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидается ровно один аргумент");
        }
        this.key = args[1];
        if (collection.getMap().containsKey(key)) {
            throw new IllegalArgumentException("Элемент с таким ключем уже существует");
        }
    }

    @Override
    public void execute(String argument) {
        collection.put(key, this.currentElement());
    }

    @Override
    public boolean isFinished() {
        return super.isfin;
    }
}
