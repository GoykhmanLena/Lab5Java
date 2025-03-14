package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;
import ru.lenok.common.models.LabWork;

public class Info extends Command {
    LabWorkCollection collection;

    public Info(LabWorkCollection collection) {
        super("info", "вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        System.out.println("Это LabWorkCollection, текущий размер: " + collection.getMap().size() + ", состоит из элементов типа " + LabWork.class);
        System.out.println();
    }
}
