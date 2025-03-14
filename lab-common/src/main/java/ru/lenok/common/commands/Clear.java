package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;

import static ru.lenok.common.LabWorkCollection.idCounter;

public class Clear extends Command {
    LabWorkCollection collection;

    public Clear(LabWorkCollection collection) {
        super("clear", "очистить коллекцию");
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        collection.clear_collection();
        idCounter = 0;
    }
}
