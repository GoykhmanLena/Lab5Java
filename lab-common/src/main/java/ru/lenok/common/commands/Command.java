package ru.lenok.common.commands;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.IOException;

@Data
@AllArgsConstructor
public abstract class Command {
    public String name;
    public String description;

    public abstract void execute(String arg) throws IOException;

    public void addNextLine(String line) {
    }

    public void setArgs(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Не нужно передавать аргументы!!!");
        }
    }

    public boolean isFinished() {
        return true;
    }
}
