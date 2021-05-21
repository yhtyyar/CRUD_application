package repository.json;


import model.Writer;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;

import java.lang.reflect.Type;
import java.util.List;


public class JsonWriterRepositoryImpl  implements WriterRepository {

    private PostRepository postRepository;
    private RegionRepository regionRepository;


    public JsonWriterRepositoryImpl (String fileName, Type listType, PostRepository postRepository,
                                     RegionRepository regionRepository) {

        this.postRepository = postRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public Writer getById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public void save(Writer writer) throws Exception {

    }

    @Override
    public void update(Writer writer) throws Exception {

    }

    @Override
    public void deleteById(Long aLong) throws Exception {

    }

    @Override
    public List<Writer> getAll() throws Exception {
        return null;
    }
}
