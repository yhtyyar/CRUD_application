package repository.json;


import model.Post;
import model.StoredData;
import model.Writer;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class JsonWriterRepositoryImpl extends JsonRepository<Writer>  implements WriterRepository {

    private PostRepository postRepository;
    private RegionRepository regionRepository;


    public JsonWriterRepositoryImpl (String fileName, Type listType, PostRepository postRepository,
                                     RegionRepository regionRepository) {

        super(fileName, listType);
        this.postRepository = postRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public Writer getById(Long id) {
        return (Writer) super.getById(id);
    }

    @Override
    public Writer save(Writer writer) {
        super.save(writer);

        return  writer;
    }

    @Override
    public Writer update(Writer writer) {
        super.update(writer);

        return writer;
    }

    @Override
    public void deleteById(Long id) {

        super.deleteById(id);
    }

    @Override
    public List<Writer> getAll() {
        List <StoredData> storedList = getAllRecordings();

        if (storedList.size() == 0) {
            return new ArrayList<>();
        }

        return storedList.stream()
                .map((w) -> (Writer) w)
                .peek(writer -> {
                    writer.setNameRegion(regionRepository.getById(writer.getNameRegion().getId()));

                    List <Post> writerPosts = writer.getPosts();

                    if (writerPosts != null && writerPosts.size() > 0) {
                        for (int i = 0; i < writerPosts.size(); i++) {
                            writerPosts.set(i, postRepository.getById(writerPosts.get(i).getId()));
                        }
                    }
                }).collect(Collectors.toList());
    }
}
