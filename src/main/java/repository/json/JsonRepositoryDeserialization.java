package repository.json;

import com.google.gson.reflect.TypeToken;
import model.Post;
import model.Region;
import model.Writer;
import repository.PostRepository;
import repository.RegionRepository;
import repository.WriterRepository;


import java.lang.reflect.Type;
import java.util.List;


public class JsonRepositoryDeserialization {


    private static final String WRITERS_FILE_NAME = "src/main/resources/json_files/writers.json";
    private static final String POSTS_FILE_NAME = "src/main/resources/json_files/posts.json";
    private static final String REGIONS_FILE_NAME = "src/main/resources/json_files/regions.json";

    //десериализация
    private static final Type WRITERS_LIST_TYPE = new TypeToken<List<Writer>>() {}.getType();
    private static final Type POSTS_LIST_TYPE = new TypeToken<List<Post>>() {}.getType();
    private static final Type REGIONS_LIST_TYPE = new TypeToken<List<Region>>() {}.getType();


    public static WriterRepository getWriterRepository() {
        return new JsonWriterRepositoryImpl(WRITERS_FILE_NAME,WRITERS_LIST_TYPE,
                getPostRepository(), getRegionRepository());
    }


    public static PostRepository getPostRepository() {
        return new JsonPostRepositoryImpl(POSTS_FILE_NAME, POSTS_LIST_TYPE);
    }


    public static RegionRepository getRegionRepository() {
        return new JsonRegionRepositoryImpl(REGIONS_FILE_NAME, REGIONS_LIST_TYPE);
    }

}
