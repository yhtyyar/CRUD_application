package view;

import controller.RegionController;
import model.Region;

import java.io.PrintStream;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class RegionView extends ViewAbstract{

    private static ViewAbstract viewAbstract;
    private RegionController regionController;

    public RegionView() {
        super(new Scanner(System.in), new PrintStream(System.out), "База данных регионов");
        regionController = new RegionController();
    }


    @Override
    void createNewRecording(String[] command) {

        if (command.length > 1) {
            String name = command[1];
            Region region = new Region(name);
            regionController.save(region);
            System.out.println("... Страна(регион) добавлен ...");
            allRecordings();

        } else {
            error("Нет необходимых данных для добавления " + CREATE);

        }
    }


    @Override
    void editRecording(String[] command) {

        int id = 1;
        int nameRegion = 2;

        if (command.length > 2) {
            System.out.println("... Изменение записи ... \n");
            try {
                regionController.update(new Region(Long.valueOf(command[id]), command[nameRegion]));
                System.out.println("... Страна(регион) изменен ...");
                allRecordings();

            } catch (NumberFormatException e) {
                error("Введена не правильная команда " + EDIT_BY_ID);

            } catch (NoSuchElementException e) {
                error("Такой страны(региона) нет");

            }
        } else {
            error("Нет необходимых данных для выполнения " + EDIT_BY_ID);

        }
    }


    @Override
    void getById(String[] command) {

        if (command.length > 1) {
            System.out.println("... Получение записи ...\n");

            try {

                Long id = Long.valueOf(command[1]);
                Region region = regionController.getById(id);
                System.out.println(region.toString() + "\n");

            } catch (NumberFormatException e) {
                error("Неверная команда " + GET_BY_ID);

            } catch (NoSuchElementException e) {
                error("Страны(региона) с таким ID нет");

            }
        } else {
            error("Нет необходимых данных для выполнения " + GET_BY_ID);

        }
    }


    @Override
    void allRecordings() {

        System.out.println("... Получение всех записей ... \n");
        List<Region> regionList = regionController.getAll();

        if (regionList.size() == 0) {
            error("Стран(регионов) нет");
            return;
        }
        regionList.forEach( (r) -> System.out.println((r.toString())));
    }


    @Override
    void deleteRecording(String[] command) {

        if (command.length > 1) {
            System.out.println("... Удаление страны(региона) ... \n");

            try {

                Long id = Long.valueOf(command[1]);
                regionController.deleteById(id);
                System.out.println("... Страна(регион) удален ... \n");
                allRecordings();

            } catch (NumberFormatException e) {
                error("Неправильная команда " + DELETE);

            } catch (NoSuchElementException e) {
                error("Такой страны(региона) нет");
            }
        } else {
            error("Нет необходимых данных для выполнения " + DELETE);
        }
    }



    @Override
    void helpMe() {

        show("Записи будут  выводиться в виде  ID | NAME_REGION \n" +
                CREATE + " NAME_REGION - создание новой страны(региона) \n" +
                EDIT_BY_ID + " ID NAME_REGION - изменение существующей страны(региона) \n" +
                GET_BY_ID + " ID - получение страны(региона) по ID \n" +
                GET_ALL + " - получение всех стран(регионов) \n" +
                DELETE + " ID - удаление страны(региона) \n" +
                HELP_ME + " - вывод данных для помощи(справки) \n" +
                EXIT + " - выход из программы");
    }


    static ViewAbstract getInstance() {

        if (viewAbstract == null) viewAbstract = new RegionView();
        return viewAbstract;
    }
}
