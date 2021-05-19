package controller;

import model.Post;
import model.Region;
import model.Writer;
import service.WriterService;

import java.util.List;

public class WriteController implements WriterService{

    private WriterService writerService;


    public WriteController (WriterService writerService) {
        this.writerService = writerService;
    }


    @Override
    public List<Writer> getAll() throws Exception {
        return  writerService.getAll();
    }

    @Override
    public Writer getById(Long id) throws Exception {
        return  writerService.getById(id);
    }

    @Override
    public void create(String firstName, String lastName,
                              List<Post> posts, Region nameRegion) throws Exception {
        writerService.create(firstName,lastName,posts,nameRegion);
    }

    @Override
    public void update(Long id, String firstName, String lastName,
                            List<Post> posts, Region nameRegion) throws Exception {
        writerService.update(id, firstName, lastName, posts, nameRegion);
    }

    @Override
    public void delete(Long id) throws Exception {
        writerService.delete(id);
    }

}
