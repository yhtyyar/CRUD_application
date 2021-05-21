package model;


import java.time.LocalDate;

public class Post  {

    private Long id;
    private String content;
    private LocalDate created;
    private LocalDate updated;

    public Post (Long id) {
        this.id = id;
    }

    public Post(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Post (Long id, String content, LocalDate created, LocalDate updated) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
        updated = LocalDate.now();
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Post | " + id + " | " + content + " | " + created + " | " + updated + " |";
    }
}
