package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;
import ru.lenok.common.models.LabWork;

import java.util.ArrayList;
import java.util.List;

import static ru.lenok.common.LabWorkCollection.idCounter;

public class RemoveGreater extends CommandWithElement {
    LabWorkCollection collection;

    public RemoveGreater(LabWorkCollection collection, boolean interactive) {
        super("remove_greater {element}", "удалить из коллекции все элементы, превышающие заданный", interactive);
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        List keys = new ArrayList<>(collection.getMap().keySet());
        LabWork elem = this.currentElement();
        System.out.println(keys);
        for (int i = 0; i < keys.size(); i++) {
            System.out.println(collection.getMap().get(keys.get(i)).compareTo(elem));
            if (collection.getMap().get(keys.get(i)).compareTo(elem) > 0) {
                collection.remove((String) keys.get(i));
            }
        }
        idCounter--;
    }

    @Override
    public boolean isFinished() {
        return super.isfin;
    }
}
