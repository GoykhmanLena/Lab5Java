package ru.lenok.common.commands;

public class Exit extends Command {
    public Exit() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    public void execute(String arg) {
        System.out.println("Exiting the application.");
        System.exit(0);
    }
}
