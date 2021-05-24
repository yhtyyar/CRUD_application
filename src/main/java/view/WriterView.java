package view;

import controller.WriterController;
import model.Post;
import model.Region;
import model.Writer;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class WriterView  extends ViewAbstract{

    private static ViewAbstract viewAbstract;

    private final String WRITER_FIRST_NAME = "first_name";
    private final String WRITER_LAST_NAME = "last_name";
    private final String WRITER_REGION_NAME = "region";
    private final String WRITER_POSTS = "posts";

    private WriterController writerController;

    public WriterView() {
        super(new Scanner(System.in), new PrintStream(System.out), "Список писателей ");
        writerController = new WriterController();
    }



    @Override
    void createNewRecording(String[] command) {

        int firstName = 1;
        int lastName = 2;
        int nameRegion = 3;

        if (command.length > 3) {

            try {
                Writer writer = new Writer(command[firstName], command[lastName],
                        new Region(Long.valueOf(command[nameRegion])));
                writerController.save(writer);
                show("Новый писатель создан!");
                allRecordings();

            } catch (NumberFormatException e) {
                error("Неверная команда создания " + CREATE);
            }
        } else {
            error("Нет необходимых данных для выполнения " + CREATE);
        }

    }


    @Override
    void editRecording(String[] command) {

        if (command.length < 4) {
            error("Нет необходимых данных для выполнения" + EDIT_BY_ID);
        }

        Long id;

        int subCommand = 1;  //подкомманды
         int  idPart = 2;
        int firstArgument = 3;

        try {
            id = Long.valueOf(command[idPart]);

        } catch (NumberFormatException e) {
            error("Неправильный формат команды, не правильно указан ID писателя");
            return;
        }

        Writer writer;

        try {
            writer = writerController.getById(id);

        } catch (NoSuchElementException e) {
            error("Нет писателя с таким ID ");
            return;
        }
        
        switch (command[subCommand]) {
            
            case WRITER_FIRST_NAME:
                writer.setFirstName(command[firstArgument]);
                writerController.update(writer);
                break;

            case WRITER_LAST_NAME:
                writer.setLastName(command[firstArgument]);
                writerController.update(writer);
                break;

            case WRITER_REGION_NAME:
                try {
                    writer.setNameRegion(new Region(Long.valueOf(command[firstArgument])));
                    writerController.update(writer);
                } catch (NumberFormatException e) {
                    error("Неправильный указан ID страны(региона)");
                }
                break;

            case WRITER_POSTS:
                List <Post> postList = new ArrayList<>();

                try {
                    for (int i = 3; i < command.length; i++) {
                        postList.add(new Post(Long.valueOf(command[i])));
                    }
                } catch (NumberFormatException e) {
                    error("Неправильно указан ID постов");
                }

                writer.setPosts(postList);
                writerController.update(writer);
                break;

            default:
                error("Неправильная под комманда " + EDIT_BY_ID);

        }
        allRecordings();

    }


    @Override
    void getById(String[] command) {

        if (command.length > 1) {
            show("... Вывод данных про писателя ...");

            try {
                Long id = Long.valueOf(command[1]);
                Writer writer = writerController.getById(id);
                System.out.println(writer.toString() + "\n");

            } catch (NumberFormatException e) {
                error("Неверная команда " + GET_BY_ID);

            } catch (NoSuchElementException e) {
                error("Писателя с таким ID нет ");
            }

        } else {
            error("Не написано комманда " + GET_BY_ID);
        }
    }


    @Override
    void allRecordings() {

        show("... Получаем все данные ...");

        List <Writer> writers = writerController.getAll();

        if (writers.size() == 0) {
            error("Данных нет");
        }
        writers.forEach((w) -> System.out.println(w.toString() + "\n"));
    }


    @Override
    void deleteRecording(String[] command) {

        if (command.length > 1) {
            show("... Удаление данных про писателя ...");

            try {
                Long id = Long.valueOf(command[1]);
                writerController.deleteById(id);
                show("... Данные удалены ...");
                allRecordings();

            } catch (NumberFormatException e) {
                error( "Неправильная команда " + DELETE);

            } catch (NoSuchElementException e) {
                error("Такого писателя нет(неправильный ID)");
            }

        } else {
            error("Не написано комманда " + DELETE);
        }

    }



    @Override
    void helpMe() {

        show("Данные про писателей будет выводиться в виде:  " +
                "ID | FIRST_NAME | LAST_NAME | REGION | POSTS_ID \n" +
                CREATE + " FIRST_NAME | FIRST_NAME | REGION_ID - создание нового пользователя \n" +
                DELETE + " ID - удаление пользователя \n" +
                GET_ALL + " - получение данных про всех писателей \n" +
                GET_BY_ID + " ID - получение одного из писателей по ID \n" +
                EDIT_BY_ID + " " + WRITER_FIRST_NAME + "  ID | FIRST_NAME - изменить имя писателя по ID \n" +
                EDIT_BY_ID + " " + WRITER_LAST_NAME + " ID | LAST_NAME - изменить фамилию писателя по ID \n" +
                EDIT_BY_ID + " " + WRITER_REGION_NAME + " ID | REGION - изменить страну(регион) писателя по ID \n" +
                EDIT_BY_ID + " " + WRITER_POSTS + " ID | POST1_ID  POST2_ID - изменить список записей(постов) писателей" +
                " (вводите ID записей(постов) разделенными пробелом) \n" +
                HELP_ME + " - вывод данных для помощи(справки)" +
                EXIT + " - выход из программы");
    }

    static ViewAbstract getInstance() {
        if (viewAbstract == null) {
            viewAbstract = new WriterView();
        }
        return viewAbstract;
    }
}
