package view;

import java.io.PrintStream;
import java.util.Scanner;

public abstract class ViewAbstract {


    private Scanner scanner;
    private PrintStream output;
    //прерывание
    private  boolean interrupt;


    final String CREATE = "new";
    final String EDIT_BY_ID = "edit";
    final String GET_BY_ID = "get";
    final String GET_ALL = "get_all";
    final String DELETE = "delete";
    final String HELP_ME = "help_me";
    final String BACK_TO_BEGINNING = "back";
    final String EXIT = "exit";
    final String REPOSITORY_NAME;

    ViewAbstract (Scanner input, PrintStream output, String repositoryName) {
        this.scanner = input;
        this.output = output;
        this.REPOSITORY_NAME = repositoryName;
    }

    public void start() {

        output.print( REPOSITORY_NAME + ". \nНапишите: " + HELP_ME + "  - для получения помощи(справки) \n          " +
                EXIT + "  - для выхода из программы \n");

        while (!interrupt) {
            String cmd = scanner.nextLine();

            String [] command = cmd.split(" ");

            switch (command[0]) {
                case HELP_ME:
                    helpMe();
                    break;

                case CREATE:
                    createNewRecording(command);
                    break;

                case EDIT_BY_ID:
                    editRecording(command);
                    break;

                case DELETE:
                    deleteRecording(command);
                    break;

                case GET_BY_ID:
                    getById(command);
                    break;

                case GET_ALL:
                    allRecordings();
                    break;

                case BACK_TO_BEGINNING:
                    backToBeginning();
                    break;

                case EXIT:
                    exit();
                    break;

                default:
                    error("Вы ввели неизвестный запрос! Напишите " + HELP_ME + " для получения помощи(справки)");
                    break;
            }
        }
    }




    abstract void createNewRecording( String [] command);

    abstract void editRecording(String[] command);

    abstract void deleteRecording(String[] command);

    abstract void getById(String[] command);

    abstract void allRecordings();

    abstract void helpMe();

    abstract void backToBeginning();

    void exit() {

       interrupt = true;
    }

    void error(String str) {

        output.print(" Ошибка! " + str + "\n");
    }

    void show (String cmd) {
        output.print(cmd + "\n");
    }

}
