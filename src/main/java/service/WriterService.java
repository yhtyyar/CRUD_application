package service;

import model.Post;
import model.Region;
import model.Writer;

import java.util.List;

public interface WriterService extends GenericService<Writer, Long>{

    void create (String firstName, String lastName, List<Post> posts, Region nameRegion) throws Exception;

    void update (Long id, String firstName, String lastName, List<Post> posts, Region nameRegion) throws Exception;


}
