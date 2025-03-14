package ru.lenok.common.util;

import java.util.ArrayList;
import java.util.List;


public final class HistoryList {
    public List<String> historyList = new ArrayList<>();

    public void addCommand(String command) {
        historyList.add(command);
    }
    public List<String> getHistoryList() {
        return historyList;
    }
}
