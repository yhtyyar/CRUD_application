package model;


import java.util.List;

public class Writer  implements StoredData {

    private Long id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Region nameRegion;


    public Writer(Long id, String firstName, String lastName, List<Post> posts, Region nameRegion ) {

        // При создании Id дается автоматически а записи или посты пишуться потом
        // а данные как  имя фамилия и регион должны вводиться сразу
        this(firstName, lastName, nameRegion);
        this.id = id;
        this.posts = posts;

    }

    public Writer (String firstName, String lastName, Region region) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nameRegion = region;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setNameRegion(Region nameRegion) {
        this.nameRegion = nameRegion;
    }



    @Override
    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public Region getNameRegion() {
        return nameRegion;
    }


    @Override
    public void copyFrom(StoredData stored) {
        Writer storedWriter = ((Writer) stored);

        this.firstName = storedWriter.getFirstName();
        this.lastName = storedWriter.getLastName();
        this.posts = storedWriter.getPosts();
        this.nameRegion = storedWriter.getNameRegion();
    }

    @Override
    public String toString() {

        return "Writer | " +  + id + " | " + firstName +   " | " + lastName +
                " | " + posts +  " | " + nameRegion + " |";
    }
}
