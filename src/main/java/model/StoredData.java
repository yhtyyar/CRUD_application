package model;

public interface StoredData {
    void setId(Long id);
    Long getId();
    void copyFrom(StoredData stored);
}
