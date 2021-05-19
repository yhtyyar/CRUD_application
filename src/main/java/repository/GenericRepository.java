package repository;


import java.util.List;

public interface GenericRepository <T,ID> {

    T getById (ID id) throws Exception;

    void save (T t) ;

    void update (T t) throws Exception;

    void delete (T t) throws Exception;

    List<T> getAll() throws Exception;

    List <T> stringToData (List<String> stringList) throws Exception;

    List <String> dataToString (List <T> tList) throws Exception;

    String dataToString (T t);

    ID getLastId() throws Exception;

}
