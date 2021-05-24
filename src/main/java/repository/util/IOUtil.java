package repository.util;

import java.io.*;

public class IOUtil {

    public static void createNewFile(File file) {

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }

    public static void writeRecording(File file, String recordings) {

        writeToFile(file, recordings, true);
    }

    public static void rewriteAllRecordings(File file, String recordings) {

        writeToFile(file, recordings, false);
    }

    private static void writeToFile(File file, String recording, boolean append) {

        //Writer это не класс Writer из пакета model а абстрактный класс из пакета java.io.
        try (Writer writer = new FileWriter(file,append)) {
            writer.write(recording);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }

    public static String fileToString(File file) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Reader reader = new FileReader(file)) {

            int i;

            while ((i = reader.read()) != -1) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
        return stringBuilder.toString();
    }
}
