package repository.json;

import model.StoredData;
import repository.util.Utils;
import repository.util.UtilsJson;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.NoSuchElementException;

public class JsonRepository <T extends StoredData> {

    private Utils<StoredData> utils;


    public JsonRepository(String fileName, Type listType) {

        utils = new UtilsJson<T>(new File(fileName), listType);
        utils.inputRepository();
    }


    public StoredData getById(Long id) {

        List<StoredData> storedList = getAllRecordings();

        return storedList.stream().filter((s) -> s.getId() == id )
                .findAny()
                .orElseThrow( () -> new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id));
    }


    public void save (StoredData storedObject) {

        List<StoredData> storedList = getAllRecordings();

        storedObject.setId((long) (storedList.size() + 1));
        utils.saveRecording(storedObject);
    }


    public void update (StoredData storedObj) {
        List <StoredData> storedList = getAllRecordings();

        StoredData stored = storedList.stream().filter( (s) -> s.getId() == storedObj.getId())
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Репозиторий не содержит запись:" + storedObj));

        stored.copyFrom(storedObj);
        utils.rewriteAllRecordings(storedList);
    }


    public void deleteById(Long id) {
        List<StoredData> storedList = getAllRecordings();

        StoredData stored = storedList.stream().filter((s) -> s.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id));
        storedList.remove(stored);
        utils.rewriteAllRecordings(storedList);
    }


    public List <StoredData> getAllRecordings() {
        return utils.getAll();
    }


}
