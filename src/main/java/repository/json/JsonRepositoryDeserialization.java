package repository.json;

import com.google.gson.reflect.TypeToken;
import model.Post;
import model.Region;
import model.Writer;


import java.lang.reflect.Type;
import java.util.List;


public class JsonRepositoryDeserialization {


    private static final String WRITERSFILENAME = "writers.json";
    private static final String POSTSFILENAME = "posts.json";
    private static final String REGIONSFILENAME = "regions.json";

    //десериализация
    private static final Type WRITERSLISTTYPE = new TypeToken<List<Writer>>() {}.getType();
    private static final Type POSTSLISTTYPE = new TypeToken<List<Post>>() {}.getType();
    private static final Type REGIONSLISTTYPE = new TypeToken<List<Region>>() {}.getType();

}
