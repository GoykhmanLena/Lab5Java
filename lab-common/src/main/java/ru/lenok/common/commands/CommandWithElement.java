package ru.lenok.common.commands;

import ru.lenok.common.models.Difficulty;
import ru.lenok.common.models.LabWork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ru.lenok.common.LabWorkCollection.idCounter;

public abstract class CommandWithElement extends Command {
    LabWork.Builder builder;
    int fieldNumber = 1;
    boolean isfin = false;
    final boolean interactive;

    public List<String> allPrompts;

    public CommandWithElement(String name, String description, boolean interactive) {
        super(name, description);
        builder = new LabWork.Builder();
        allPrompts = new ArrayList<>();
        allPrompts.add("Введите координату X");
        allPrompts.add("Введите координату Y");
        allPrompts.add("Введите минимально возможные баллы");
        allPrompts.add("Введите описание лабораторной работы");
        allPrompts.add("Какая сложность лабораторной работы? " + Arrays.toString(Difficulty.values()) + " Введите значение из списка");
        allPrompts.add("Введите название предмета");
        allPrompts.add("Сколько учебных часов предполагается на изучение предмета?");
        allPrompts.add("Спасибо! Все данные введены)))");
        this.interactive = interactive;
    }

    private void prompt(int fieldNumber) {
        if (interactive) {
            System.out.println(allPrompts.get(fieldNumber - 1));
        }
    }

    public void addNextLine(String line) {
        if (line == null || line.equals("")) {
            throw new IllegalArgumentException("Значение поля не может быть пустым, пожалуйста введите хоть что-то");
        }
        switch (fieldNumber) {
            case 1:
                builder.setName(line);
                idCounter++;
                prompt(fieldNumber);
                break;
            case 2:
                try {
                    builder.setCoordinateX(Double.parseDouble(line));
                    prompt(fieldNumber);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Значение должно быть double", e);
                }
                break;
            case 3:
                try {
                    builder.setCoordinateY(Float.parseFloat(line));
                    prompt(fieldNumber);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Значение должно быть float");
                }
                break;
            case 4:
                try {
                    builder.setMinimalPoint(Double.parseDouble(line));
                    prompt(fieldNumber);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Значение должно быть double");
                } catch (IllegalArgumentException e) {
                    throw e;
                }
                break;
            case 5:
                if (line.length() > 2863) {
                    throw new IllegalArgumentException("Слишком много букав, сократи!!!");
                } else {
                    builder.setDescription(line);
                    prompt(fieldNumber);
                }
                break;
            case 6:
                try {
                    builder.setDifficulty(Difficulty.valueOf(line));
                    prompt(fieldNumber);
                } catch (IllegalArgumentException e) {
                    throw new IllegalArgumentException("Это не вариант из списка, повторите ввод");
                }
                break;
            case 7:
                builder.setDisciplineName(line);
                prompt(fieldNumber);
                break;
            case 8:
                try {
                    builder.setDisciplinePracticeHours(Long.parseLong(line));
                    isfin = true;
                    prompt(fieldNumber);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Поле должно быть Long");
                }
                break;
        }
        fieldNumber++;
    }

    public LabWork currentElement() {
        return builder.build();
    }
}
