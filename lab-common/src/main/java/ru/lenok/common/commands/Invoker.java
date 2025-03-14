package ru.lenok.common.commands;


import ru.lenok.common.LabWorkCollection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Invoker {
    private final LabWorkCollection collection;
    public Map<String, Function<Boolean, Command>> commands = new HashMap<>();

    public Invoker(LabWorkCollection collection) {
        this.collection = collection;
        commands.put("insert", (interactive) -> new Insert(collection, interactive));
        commands.put("exit", (interactive) -> new Exit());
        commands.put("show", (interactive) -> new Show(collection));
        commands.put("save", (interactive) -> new Save(collection));
        commands.put("remove_key", (interactive) -> new RemoveKey(collection));
        commands.put("update_id", (interactive) -> new UpdateId(collection, interactive));
        commands.put("print_ascending", (interactive) -> new PrintAscending(collection));
        commands.put("remove_greater", (interactive) -> new RemoveGreater(collection, interactive));
        commands.put("replace_if_greater", (interactive) -> new ReplaceIfGreater(collection, interactive));
        commands.put("filter_contains_description", (interactive) -> new FilterContainsDescription(collection));
        commands.put("filter_starts_with_name", (interactive) -> new FilterStartsWithName(collection));
        commands.put("help", (interactive) -> new Help(this));
        commands.put("info", (interactive) -> new Info(collection));
        commands.put("clear", (interactive) -> new Clear(collection));
    }

    public Command getCommand(String command, boolean interactive, boolean stub) throws IllegalArgumentException {
        String[] split = command.trim().split("\\s+");
        Function<Boolean, Command> commandSupplier = commands.get(split[0]);
        if (commandSupplier == null) {
            throw new IllegalArgumentException("Такая команда не существует: " + command);
        }
        Command currentCommand = commandSupplier.apply(interactive);
        if (!stub) {
            currentCommand.setArgs(split);
        }
        return currentCommand;
    }

    public String getCommandDescription(String commandName) {
        Command command = getCommand(commandName, false, true);
        return command.getName() + ": " + command.getDescription();
    }

    public Collection<String> getCommandNames() {
        return commands.keySet();
    }
}
