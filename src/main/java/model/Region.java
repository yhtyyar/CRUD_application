package model;


public class Region  {

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

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }

    @Override
    public String toString() {
        return "Region | " + + id + " | " + nameRegion + " |";
    }
}
