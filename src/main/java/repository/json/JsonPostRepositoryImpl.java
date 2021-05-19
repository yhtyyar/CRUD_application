package repository.json;

import model.Post;
import repository.PostRepository;

import java.util.List;

public class JsonPostRepositoryImpl implements PostRepository {

    private final static String json_name = "posts.json";

    public JsonPostRepositoryImpl() {

    }

    public Post getById(Long id) throws Exception {


        return null;
    }


    public void create(Post post) throws Exception {

    }

    public void save(Post item) {

    }

    public void update(Post item) throws Exception {

    }

    public void delete(Post item) throws Exception {

    }

    public List<Post> getAll() throws Exception {
        return null;
    }


    public List<Post> stringToData(List<String> items) throws Exception {
        return null;
    }


    public List<String> dataToString(List<Post> items) throws Exception {
        return null;
    }

    @Override
    public String dataToString(Post post) {
        return null;
    }

    @Override
    public Long getLastId() throws Exception {
        return null;
    }
}
