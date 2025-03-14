package ru.lenok.common.commands;

public class Help extends Command {
    private Invoker invoker;

    public Help(Invoker inv) {
        super("help", "вывести справку по доступным командам");
        this.invoker = inv;
    }

    @Override
    public void execute(String arg) {
        for (String commandName : invoker.getCommandNames()) {
            System.out.println(invoker.getCommandDescription(commandName));
        }
        System.out.println();
    }
}
