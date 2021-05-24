package repository.json;

import model.Post;
import model.StoredData;
import repository.PostRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonPostRepositoryImpl extends JsonRepository<Post> implements PostRepository {


    public JsonPostRepositoryImpl(String fileName, Type listType) {
        super(fileName, listType);
    }

    @Override
    public Post getById(Long id)  {
        return (Post) super.getById(id);
    }

    @Override
    public Post save(Post post)  {
        super.save(post);

        return post;
    }

    @Override
    public Post update(Post post)  {
        super.update(post);

        return post;
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }


    @Override
    public List<Post> getAll()  {

        List <StoredData> storedList = super.getAllRecordings();

        if (storedList.size() == 0) {
            return  new ArrayList<>();
        }
        return storedList.stream().map((p) -> (Post) p)
                .collect(Collectors.toList());
    }
}
