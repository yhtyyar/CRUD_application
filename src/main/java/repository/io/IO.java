package repository.io;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IO {


    private final static String pathToFile = "\\src\\main\\resources\\json_files\\";

    public static List <String> read (String fileName) {

        List <String> dataList = new ArrayList <String>();

        try(BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(
                new FileInputStream(GetPath(fileName)),  UTF_8))) {

            String string;

            while((string = bufferedReader.readLine()) != null) {

                dataList.add(string);
            }

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }


        return dataList;
    }

    public static void write (String fileName, String data) {

        try (BufferedWriter bufferedWriter = new BufferedWriter(
                               new FileWriter(GetPath(fileName), true))) {

            bufferedWriter.write(data);
            bufferedWriter.newLine();

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }

    public static void writeList (String fileName, List <String> dataList) {

        try(BufferedWriter bufferedWriter = new BufferedWriter(
                              new FileWriter(GetPath(fileName), false))) {

            for (String str : dataList) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }

    private static String GetPath(String fileName) {

        Path path = Paths.get("").toAbsolutePath();

        // выводит полностью абсолютный путь с именем файла
        String pathString = path + pathToFile + fileName;

        return pathString;
    }
}
