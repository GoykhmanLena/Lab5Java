package ru.lenok.common;

import ru.lenok.common.commands.Command;
import ru.lenok.common.commands.ExecuteScript;
import ru.lenok.common.commands.History;
import ru.lenok.common.commands.Invoker;
import ru.lenok.common.input.AbstractInput;
import ru.lenok.common.models.LabWork;
import ru.lenok.common.util.HistoryList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Stack;


public class InputProcessor {
    private LabWorkCollection collection;
    private Invoker invoker;
    HistoryList historyList = new HistoryList();
    Hashtable<String, LabWork> map;
    Stack<String> scriptExecutionContext;
    public static boolean debug = false;

    public InputProcessor(Hashtable<String, LabWork> map) {
        this.map = map;
        this.scriptExecutionContext = new Stack<>();
        init();
    }

    public void processInput(AbstractInput input) throws IOException {
        String line;
        Command command = null;

        while ((line = input.readLine()) != null) {
            if (command != null) { // ещё не finished
                try {
                    command.addNextLine(line);
                } catch (Exception e) {
                    displayExecuteScriptError(e);
                    if (!scriptExecutionContext.empty()) {
                        throw e;
                    }
                }
                if (command.isFinished()) {
                    command.execute(null);
                    historyList.addCommand(command.name);
                    command = null;
                }
            } else { // read new command
                try {
                    command = invoker.getCommand(line, isInteractive(), false);
                } catch (Exception e) { // обработаны все несуществующие команды +  с плохим аргументом
                    displayExecuteScriptError(e);
                    if (!scriptExecutionContext.empty()) {
                        throw e;
                    }
//                    String msg = debug ? e.toString(): e.getMessage();
//                    System.err.println(msg);
                    continue;
                }
                if (command.isFinished()) {
                    // oneLiner
                    historyList.addCommand(command.name);
                    try {
                        command.execute(null);
                    } catch (Exception e) {
                        displayCommonError(e);
                    }
                    command = null;
                } else { // начало команды с элементом
                    if (isInteractive()) {
                        System.out.println("Введите наименоввание лабораторной работы");
                    }
                }
            }
        }
        if (command != null) {
            if (command.isFinished()) {
                command.execute(null);
                historyList.addCommand(command.name);
            } else {
                System.err.println("Внимание! У вас в файле есть невыполненная последняя команда - недостаточно полей введено");
            }
        }
    }


    public void setScriptExecutionContext(String path) {
        scriptExecutionContext.push(path);
    }

    public void exitContext() {
        scriptExecutionContext.pop();
    }

    public boolean checkContext(String currentFile) {
        if (scriptExecutionContext.contains(currentFile)) {
            return true;
        }
        return false;
    }

    private void displayExecuteScriptError(Exception e) {
        displayCommonError(e);
        if (!scriptExecutionContext.empty()) {
            System.err.println("Ошибка при обработке ExecuteScript: ");
            for (String item : scriptExecutionContext) {
                System.err.println(item);
            }
        }
    }

    private void displayCommonError(Exception e) {
        if (debug) {
            e.printStackTrace();
        }
        System.err.println(e.getMessage());
    }

    public boolean isInteractive() {
        return scriptExecutionContext.empty();
    }

    void init() {
        this.collection = new LabWorkCollection(map);
        this.invoker = new Invoker(collection);
        invoker.commands.put("execute_script", (interactive) -> new ExecuteScript(this));
        invoker.commands.put("history", (interactive) -> new History((ArrayList<String>) historyList.getHistoryList()));
    }
}
