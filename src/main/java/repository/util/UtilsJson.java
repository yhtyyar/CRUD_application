package repository.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.StoredData;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UtilsJson <T extends StoredData>  extends UtilsAbstract<StoredData>{

    private Type listType;


    public UtilsJson(File fileRepository, Type listType) {
        super(fileRepository);
        this.listType = listType;
    }


    @Override
    public void saveRecording(StoredData recording) {

        List<StoredData> recordings = getAll();

        recordings.add(recording);

        try (Writer writer = new FileWriter(getFileRepository(), false)){
            new Gson().toJson(recordings, writer);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }


    @Override
    public void rewriteAllRecordings(List<StoredData> recordings) {

        try(Writer writer = new FileWriter(getFileRepository(), false) ) {
            new Gson().toJson(recordings, writer);

        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
    }


    @Override
    public List<StoredData> getAll() {

        Gson gson = new Gson();

        try (JsonReader jsonReader = new JsonReader(new FileReader(getFileRepository())) ) {
            List<StoredData>  resultList = gson.fromJson(jsonReader, listType);

            if (resultList != null) {
                return resultList;
            }
        } catch (IOException e) {
            System.out.println("Ошибка ввода-вывода: " + e);
        }
        return new ArrayList<StoredData>();
    }
}
