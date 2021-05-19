package controller;

import model.Post;
import repository.PostRepository;
import service.PostService;

import java.util.List;

public class PostController  implements PostService {


    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @Override
    public void create(String content, String created) throws Exception {

    }

    @Override
    public void update(Long id, String content, String created, String updated) throws Exception {

    }

    @Override
    public Post getById(Long aLong) throws Exception {
        return null;
    }

    @Override
    public void delete(Long aLong) throws Exception {

    }

    @Override
    public List<Post> getAll() throws Exception {
        return null;
    }
}
