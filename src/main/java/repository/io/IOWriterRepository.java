package repository.io;


import model.Post;
import model.Region;
import model.Writer;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;
import repository.util.IOUtil;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class IOWriterRepository implements WriterRepository {

    private final String FILE_NAME = "src/main/resources/json_files/writers.json";
    private final File file = new File(FILE_NAME);

    private PostRepository postRepository;
    private RegionRepository regionRepository;

    public IOWriterRepository() {
        this.postRepository = new IOPostRepository();
        this.regionRepository = new IORegionRepository();

        if(!file.exists()) {
            IOUtil.createNewFile(file);
        }
    }


    @Override
    public Writer getById(Long id) {
        List <Writer> writers = getAll();
        Optional <Writer> writer = writers.stream()
                .filter((w) -> w.getId() == id)
                .findFirst();
        return writer.orElseThrow( () ->
                new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id));
    }


    @Override
    public Writer save(Writer writer) {
        updateWriterId(writer);

        String recording = writerToStringRecording(writer);
        IOUtil.writeRecording(file, recording);

        return writer;
    }


    @Override
    public Writer update(Writer writer) {
        List <Writer> writers = getAll();

        Optional<Writer> resultUserOptional = writers.stream()
                                                     .filter(u -> u.getId() == writer.getId())
                                                     .findFirst();

        Writer resultWriter = resultUserOptional.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит обновленного элемента"));

        resultWriter.setFirstName(writer.getFirstName());
        resultWriter.setLastName(writer.getLastName());
        resultWriter.setNameRegion(writer.getNameRegion());
        resultWriter.setPosts(writer.getPosts());

        saveAll(writers);

        return (Writer) writers;
    }


    @Override
    public void deleteById(Long id) {
        List<Writer> writers = getAll();

        Optional<Writer> writer = writers.stream()
                                     .filter(u -> u.getId() == id)
                                     .findFirst();

        writers.remove(writer.orElseThrow(() ->
                new NoSuchElementException("Репозиторий не содержит записи с таким ID: " + id)));
        saveAll(writers);
    }


    @Override
    public List<Writer> getAll() {
        String fileString = IOUtil.fileToString(file);

        if (fileString.length() == 0 || !fileString.contains(".")) {
            return new ArrayList<>();
        }

        String[] writers = fileString.split(".");


        return Arrays.stream(writers).map(s -> {

            String[] writerParts = s.split(" | ");

            return new Writer(Long.valueOf(writerParts[0]),
                                           writerParts[1],
                                           writerParts[2],
                                           postListFromString(writerParts[3]),
                                           getRegion(Long.valueOf(writerParts[4])));
        }).collect(Collectors.toList());
    }


    private String writerToStringRecording(Writer writer) {
        return writer.getId() + " | " + writer.getFirstName() + " | " + writer.getLastName()
                + " | " + writer.getNameRegion().getId() + " | " + postListToString(writer.getPosts()) + ".";
    }


    private String postListToString(List<Post> postList) {
        if (postList == null || postList.size() == 0) {
            return "0";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < postList.size(); i++) {
            stringBuilder.append(postList.get(i).getId());

            if (i != postList.size() - 1) {
                stringBuilder.append(" | ");
            }
        }

        return stringBuilder.toString();
    }


    private List<Post> postListFromString(String encodedPostList) {
        String[] postsId = encodedPostList.split(" | ");

        if (postsId.length == 1 && postsId[0].equals("0")) {
            return new ArrayList<>();
        }
        List<Post> postList = new ArrayList<>();

        for (String p : postsId) {
            postList.add(postRepository.getById(Long.valueOf(p)));
        }
        return postList;
    }


    private Region getRegion(Long id) {
        return regionRepository.getById(id);
    }


    private void updateWriterId(Writer writer) {
        List<Writer> writers = getAll();
        long id = writers.size() == 0 ? 1 : writers.get(writers.size() - 1).getId() + 1;
        writer.setId(id);
    }


    private void saveAll(List <Writer> list) {
        StringBuilder stringBuilder = new StringBuilder();

        list.forEach((writer) -> stringBuilder.append(writerToStringRecording(writer)));

        IOUtil.rewriteAllRecordings(file, stringBuilder.toString());
    }
}
