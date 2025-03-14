package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;

public class Show extends Command {
    LabWorkCollection collection;

    public Show(LabWorkCollection collection) {
        super("show", "вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        System.out.println(collection);
    }

}
