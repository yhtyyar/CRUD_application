package service;

import model.Post;

public interface PostService extends GenericService <Post, Long>  {

    void create (String content, String created) throws Exception;

    void update (Long id, String content, String created, String updated) throws Exception;
}
