package repository;


import java.util.List;

public interface GenericRepository <T,ID> {

    T getById (ID id) throws Exception;

    void save (T t) throws Exception;

    void update (T t) throws Exception;

    void deleteById (ID id) throws Exception;

    List<T> getAll() throws Exception;



}
