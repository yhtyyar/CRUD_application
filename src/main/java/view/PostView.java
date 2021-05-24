package view;

import controller.PostController;
import model.Post;

import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PostView extends ViewAbstract{

    private static ViewAbstract viewAbstract;
    private PostController postController;

    public PostView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных записей(постов)");
        postController = new PostController();
    }



    @Override
    void createNewRecording(String[] command) {

        if (command.length > 1) {

            Post post = new Post(getContentString(1,command));
            postController.save(post);
            show("... Запись (пост) создан ...");
            allRecordings();

        } else {
            error("Нет необходимых данных для создания " + CREATE);
        }
    }


    @Override
    void editRecording(String[] command) {

        int id = 1;
        int content = 2;
        if (command.length > 2) {
            show("... Изменение записи(поста) ...");
            try {

                postController.update(new Post(Long.valueOf(command[id]), getContentString(content, command)));
                show("... Запись(пост) изменен ... ");
                allRecordings();
            } catch (NumberFormatException e) {
                error("Введена не правильная команда " + EDIT_BY_ID);
            } catch (NoSuchElementException e) {
                error("Такой записи нет");
            }
        } else {
            error("Нет необходимых данных для выполнения " + EDIT_BY_ID);
        }
    }


    @Override
    void getById(String[] command) {

        if (command.length > 1) {
            show("... Получаем записи(посты) ... ");

            try {

                Long id = Long.valueOf(command[1]);
                Post post = postController.getById(id);
                show(post.toString());

            } catch (NumberFormatException e) {
                error("Неверная команда " + GET_BY_ID);

            } catch (NoSuchElementException e) {
                error("Записи с таким ID нет");

            }

        } else {
            error("Нет необходимых данных для выполнения " + GET_BY_ID);

        }
    }

    @Override
    void allRecordings() {

        show("... Получение всех записей(постов) ...");
        List<Post> postList = postController.getAll();

        if (postList.size() == 0) {
            error("Записей(постов) нет");
            return;
        }
        postList.forEach( (r) -> System.out.println(r.toString()));
    }


    @Override
    void deleteRecording(String[] command) {

        if (command.length > 1) {
            show("... Удаление записей(постов) ...");

            try {

                Long id = Long.valueOf(command[1]);
                postController.deleteById(id);
                show("... Запись(пост) удален ...");
                allRecordings();

            } catch (NumberFormatException e) {
                error("Неправильная команда  " + DELETE);
            } catch (NoSuchElementException e) {
                error("Такой записи нет");
            }
        } else {
            error("Нет необходимых данных для выполнения  " + DELETE);
        }
    }

    @Override
    void helpMe() {

        show("Записи будут выводиться в виде ID | CREATED | UPDATED | CONTENT \n" +
                CREATE + " CONTENT - создание новой записи(поста) \n" +
                DELETE + " ID - удаление записи(поста) по ID \n" +
                GET_BY_ID + " ID - получение  записи(поста) по ID \n" +
                GET_ALL + " - получение всех записей(постов) \n" +
                EDIT_BY_ID + " ID | CONTENT - изменение записи(поста) по ID \n" +
                HELP_ME + " - вывод данных для помощи(справки)\n\t" +
                BACK_TO_BEGINNING + " - вернуться в начало программы\n" +
                EXIT + " - выход из программы");
    }

    @Override
    void exit() {

        show("Выход");
        super.exit();
    }

    private String getContentString(int indexStart, String[] commands) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = indexStart; i < commands.length; i++) {
            stringBuilder.append(commands[i]);

            if (i != commands.length - 1)  {
                stringBuilder.append(" ");
            }
        }
        return stringBuilder.toString();
    }

    @Override
    void backToBeginning() {
        ViewAbstract viewAbstract = Views.getView();
        viewAbstract.start();
    }


    static ViewAbstract getInstance() {

        if (viewAbstract == null) viewAbstract = new PostView();
        return viewAbstract;
    }
}
