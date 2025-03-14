package ru.lenok.common.commands;

import java.util.ArrayList;
import java.util.List;

public class History extends Command {
    public static List history;

    public History(ArrayList<String> history) {
        super("history", "вывести последние 15 команд (без их аргументов)");
        this.history = history;
    }

    @Override
    public void execute(String arg) {
        int currentHistory;
        if (history.size() >= 15) {
            currentHistory = history.size() - 15;
        } else {
            currentHistory = 0;
        }
        for (int i = currentHistory; i < history.size(); i++) {
            System.out.println(history.get(i) + " " + i);
        }
        System.out.println();
    }
}
