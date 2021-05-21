package model;


import java.util.List;

public class Writer  {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Region nameRegion;

    public Writer(Long id, String firstName, String lastName, List<Post> posts, Region nameRegion ) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.posts = posts;
        this.nameRegion = nameRegion;
    }

    public Writer (String firstName, String lastName, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameRegion = region;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public Region getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(Region nameRegion) {
        this.nameRegion = nameRegion;
    }

    @Override
    public String toString() {

        return "Writer | " +  + id + " | " + firstName +   " | " + lastName +
                " | " + posts +  " | " + nameRegion + " |";
    }
}
