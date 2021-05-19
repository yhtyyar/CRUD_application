package repository.json;

import com.google.gson.Gson;
import model.Post;
import model.Region;
import model.Writer;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;
import repository.io.IO;


import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class JsonWriterRepositoryImpl implements WriterRepository {

    private PostRepository postRepository;
    private RegionRepository regionRepository;

    private final static String FILE_NAME = "writers.json";


    public JsonWriterRepositoryImpl(PostRepository postRepository, RegionRepository regionRepository) {

        this.postRepository = postRepository;
        this.regionRepository = regionRepository;
    }


    public Writer getById(Long id) throws Exception {

        List <Writer> writers = stringToData(IO.read(FILE_NAME));

        Writer currentWriter = null;

        for (Writer i : writers) {
            currentWriter = i;
            break;
        }

        if (currentWriter != null) {
            return currentWriter;
        }

        throw new Exception("Нет такого ID: " + id);
    }


    public void save(Writer writer) {

        IO.write(FILE_NAME, dataToString(writer));

    }

    public void update(Writer writer) throws Exception {

        delete(getById(writer.getId()));
        save(writer);

    }

    public void delete(Writer writer) throws Exception {

        List <Writer> writers = stringToData(IO.read(FILE_NAME));

        Writer removeWriter = null;

        for (Writer i : writers) {

            if (i.getId().equals(writer.getId())) {
                removeWriter = i;
                break;
            }
        }

        writers.remove(removeWriter);
        IO.writeList(FILE_NAME, dataToString(writers));

    }

    public List<Writer> getAll() throws Exception {
        return stringToData(IO.read(FILE_NAME));
    }

    public List<Writer> stringToData(List<String> stringList) throws Exception {

        List<Writer> writers = new ArrayList<>();

        for (String str : stringList) {

            String [] items = str.split(";");

            Writer writer = new Writer();

            // добавляем все данные по очередно
            writer.setId(Long.parseLong(items[0]));
            writer.setFirstName(items[1]);
            writer.setLastName(items[2]);

            Long postId = Long.parseLong(items[3]);
            writer.setPostId(postId);
            writer.setPosts((List<Post>) postRepository.getById(postId));

            writers.add(writer);
        }






        Stream <String> writerStream = stringList.stream();

        Spliterator<String> spliterator = writerStream.spliterator();

        spliterator.forEachRemaining((n) -> new Gson().toJson(n));




        return writers;
    }

    public List<String> dataToString(List<Writer> writers) throws Exception {

        List<String> data = new ArrayList<>();

        for (Writer i : writers) {
            data.add(dataToString(i));
        }
        return data;
    }

    @Override
    public String dataToString(Writer writer) {
        return null;
    }

    public Long getLastId() throws Exception {
        return null;
    }

    public boolean isContainPost(Post post) throws Exception {
        return false;
    }

    public boolean isContainRegion(Region region) throws Exception {
        return false;
    }
}
