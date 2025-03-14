package ru.lenok.common.commands;

import ru.lenok.common.LabWorkCollection;

import java.util.ArrayList;
import java.util.List;

import static ru.lenok.common.LabWorkCollection.idCounter;

public class UpdateId extends CommandWithElement {
    LabWorkCollection collection;
    Long id;
    String key;

    public UpdateId(LabWorkCollection collection, boolean interactive) {
        super("update id {element}", "обновить значение элемента коллекции, id которого равен заданному", interactive);
        this.collection = collection;
    }

    public void setArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидается ровно один аргумент");
        }
        try {
            this.id = Long.parseLong(args[1]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("id имеет формат Long, попробуйте ввести еще раз");
        }

        boolean idExist = false;
        List<String> keysOfLabWorkCollection = new ArrayList<>(collection.getMap().keySet());
        for (int i = 0; i < collection.length(); i++) {
            if (collection.getMap().get(keysOfLabWorkCollection.get(i)).getId().equals(id)) {
                this.key = keysOfLabWorkCollection.get(i);
                idExist = true;
                break;
            }
        }
        if (!idExist) {
            throw new IllegalArgumentException("Нет элемента с таким id");
        }
    }

    @Override
    public void execute(String arg) {
        Long curr_id = idCounter;
        idCounter = id;
        collection.put(key, this.currentElement());
        idCounter = curr_id - 1;
    }

    public boolean isFinished() {
        return super.isfin;
    }
}