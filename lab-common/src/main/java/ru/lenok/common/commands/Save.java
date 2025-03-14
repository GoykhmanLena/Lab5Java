package ru.lenok.common.commands;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.lenok.common.LabWorkCollection;
import ru.lenok.common.util.LocalDateTimeAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import static ru.lenok.common.Application.filename;

public class Save extends Command {
    LabWorkCollection collection;

    public Save(LabWorkCollection collection) {
        super("save", "сохранить коллекцию в файл");
        this.collection = collection;
    }

    @Override
    public void execute(String arg) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();
        String json = gson.toJson(collection.getMap());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
