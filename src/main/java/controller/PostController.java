package controller;


import model.Post;
import repository.PostRepository;
import repository.json.JsonRepositoryDeserialization;

import java.util.List;

public class PostController   {

    private PostRepository postRepository;


    public PostController() {

        this.postRepository = JsonRepositoryDeserialization.getPostRepository();
    }


    public Post save (Post post) {

        return postRepository.save(post);
    }


    public Post update (Post post) {

        return postRepository.update(post);
    }


    public void deleteById(Long id) {

        postRepository.deleteById(id);
    }


    public List<Post> getAll() {

        return postRepository.getAll();
    }

    public Post getById(Long id) {
        return postRepository.getById(id);
    }



}
