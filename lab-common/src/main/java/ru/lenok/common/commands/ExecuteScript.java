package ru.lenok.common.commands;

import ru.lenok.common.InputProcessor;
import ru.lenok.common.input.AbstractInput;
import ru.lenok.common.input.FileInput;

import java.io.File;
import java.io.IOException;


public class ExecuteScript extends Command {
    File file;
    private final InputProcessor inpPr;

    public ExecuteScript(InputProcessor inpPr) {
        super("execute_script file_name", "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
        this.inpPr = inpPr;
    }

    @Override
    public void execute(String arg) throws IOException {
        System.out.println("-------------------- Начало выполнения файла: " + file.getCanonicalPath() + " ---------------------------------------------------------------------");
        if (inpPr.checkContext(file.getCanonicalPath())) {
            throw new IllegalArgumentException("Обнаружен ЦИКЛ, файл: " + file + " не будет открыт");
        }
        inpPr.setScriptExecutionContext(file.getCanonicalPath());
        try (AbstractInput fileInput = new FileInput(file)) {
            inpPr.processInput(fileInput);
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла, проверьте что он существует");
        } catch (Exception e) {
            throw new IllegalArgumentException("Принудительное завершение чтения файла", e);
        } finally {
            inpPr.exitContext();
            System.out.println("-------------------- Конец выполнения файла: " + file.getCanonicalPath() + " ---------------------------------------------------------------------");
        }
    }

    @Override
    public void setArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Ожидается ровно один аргумент");
        }
        this.file = new File(args[1]);
    }

    @Override
    public void addNextLine(String line) {
        super.addNextLine(line);
    }

}
