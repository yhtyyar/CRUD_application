package service.impl;

import model.Post;
import repository.PostRepository;
import service.PostService;

import java.util.List;

public class PostServiceImpl implements PostService {

    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {

        this.postRepository = postRepository;

    }

    public Post getById(Long id) throws Exception {

        return postRepository.getById(id);
    }

    public void create(String content, String created) throws Exception {

        Post post = new Post();

        post.setId(postRepository.getLastId() + 1);
        post.setContent(content);
        post.setCreated(created);

        postRepository.save(post);

    }

    public void update(Long id, String content, String created, String updated) throws Exception {

        Post post = new Post();

        post.setId(id);
        post.setContent(content);
        post.setCreated(created);
        post.setUpdated(updated);

        postRepository.update(post);

    }


    public void delete(Long id) throws Exception {

        // Нужно реализовать правильное удлание файлов
        postRepository.delete(getById(id));

    }

    public List<Post> getAll() throws Exception {

        return postRepository.getAll();
    }
}
