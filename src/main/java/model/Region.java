package model;


public class Region  implements StoredData {

    private Long id;
    private String nameRegion;

    public Region (Long id) {
        this.id = id;
    }

    public Region(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    public Region(Long id, String nameRegion) {
        this.id = id;
        this.nameRegion = nameRegion;
    }


    @Override
    public Long getId() {
        return id;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    @Override
    public void copyFrom(StoredData stored) {
        this.nameRegion = ((Region)stored).getNameRegion();

    }

    @Override
    public String toString() {
        return "Region | " + + id + " | " + nameRegion ;
    }
}
