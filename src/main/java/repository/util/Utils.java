package repository.util;

import java.util.List;

public interface Utils <T> {

    void inputRepository();
    void rewriteAllRecordings(List<T> records);
    void saveRecording(T record);
    List<T> getAll();

}
