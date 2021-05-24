package repository.io;

import model.Post;
import repository.PostRepository;
import repository.util.IOUtil;

import java.io.File;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class IOPostRepository implements PostRepository {

    private final String FILE_NAME = "src/main/resources/json_files/posts.json";
    private final File repositoryFile = new File(FILE_NAME);

    public IOPostRepository() {
        if (!repositoryFile.exists()) {
            IOUtil.createNewFile(repositoryFile);
        }
    }


    @Override
    public Post getById(Long id) {
        List<Post> postsList = getAll();

        Optional<Post> post = postsList.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        return post.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id));
    }


    @Override
    public Post save(Post post) {
        updatePostId(post);

        String recording = post.getId() + " | " + post.getContent()  + " | "
                + post.getCreated() + " | " + post.getUpdated();
        IOUtil.writeRecording(repositoryFile, recording);

        return post;
    }


    @Override
    public Post update(Post post) {
        List<Post> postsList = getAll();

        Optional<Post> resultPostOptional = postsList.stream()
                .filter(p -> p.getId() == post.getId())
                .findFirst();
        Post resultPost = resultPostOptional.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит обновленного элемента"));
        resultPost.setContent(post.getContent());

        saveAll(postsList);

        return  resultPost;
    }


    @Override
    public void deleteById(Long id) {
        List<Post> postsList = getAll();

        Optional<Post> post = postsList.stream()
                .filter(p -> p.getId() == id)
                .findFirst();
        postsList.remove(post.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id)));
        saveAll(postsList);
    }


    @Override
    public List<Post> getAll() {
        String fileContentString = IOUtil.fileToString(repositoryFile);

        if (fileContentString.length() == 0 || !fileContentString.contains(".")) {
            return new ArrayList<>();
        }
        String[] encodedPosts = fileContentString.split(".");
        return getPostsFromStrings(encodedPosts);
    }


    private void saveAll(List<Post> list) {
        StringBuilder stringBuilder = new StringBuilder();
        list.forEach(post -> stringBuilder.append(post.getId()).append(" | ")
                                          .append(post.getContent()).append(" | ")
                                          .append(post.getCreated()).append(" | ")
                                          .append(post.getUpdated()).append("."));

        IOUtil.rewriteAllRecordings(repositoryFile, stringBuilder.toString());
    }


    private List<Post> getPostsFromStrings(String[] encodedPosts) {
        int id = 0;
        int content = 1;
        int created = 2;
        int updated = 3;

        return Arrays.stream(encodedPosts).map(s -> {
            String[] parts = s.split(" | ");
            return new Post(Long.valueOf(parts[id]),
                                         parts[content],
                                         LocalDate.parse(parts[created]),
                                         LocalDate.parse(parts[updated]));
        }).collect(Collectors.toList());
    }


    private void updatePostId(Post post) {
        List<Post> postsList = getAll();

        Long id = postsList.size() == 0 ? 1 : postsList.get(postsList.size() - 1).getId() + 1;
        post.setId(id);
    }
}
