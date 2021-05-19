package repository;

import model.Post;
import model.Region;
import model.Writer;

public interface WriterRepository extends GenericRepository<Writer, Long>{

    boolean isContainPost(Post post) throws Exception;
    boolean isContainRegion(Region region) throws Exception;



}
