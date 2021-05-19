package service.impl;

import model.Post;
import model.Region;
import model.Writer;
import repository.WriterRepository;
import service.WriterService;

import java.util.List;

public class WriterServiceImpl implements WriterService {

    private WriterRepository writerRepository;

    public WriterServiceImpl (WriterRepository writerRepository) {
        this.writerRepository = writerRepository;
    }


    public Writer getById(Long id) throws Exception {
        return writerRepository.getById(id);
    }

    public void create(String firstName, String lastName, List<Post> posts, Region nameRegion) throws Exception {

        Writer writer = new Writer();

        writer.setId(writerRepository.getLastId() + 1);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(posts);
        writer.setRegion(nameRegion);

        writerRepository.save(writer);
    }

    public void update(Long id, String firstName, String lastName, List<Post> posts, Region nameRegion) throws Exception {

        Writer writer = new Writer();

        writer.setId(id);
        writer.setFirstName(firstName);
        writer.setLastName(lastName);
        writer.setPosts(posts);
        writer.setRegion(nameRegion);

        writerRepository.update(writer);
    }



    public void delete(Long id) throws Exception {

        // реализовать удаление, не совсем правильное удаление.
        writerRepository.delete(getById(id));
    }

    public List<Writer> getAll() throws Exception {

        return writerRepository.getAll();
    }
}
