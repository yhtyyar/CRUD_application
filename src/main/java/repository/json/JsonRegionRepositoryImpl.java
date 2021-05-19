package repository.json;

import model.Region;
import repository.RegionRepository;

import java.util.List;

public class JsonRegionRepositoryImpl implements RegionRepository {

    private final static String JSON_NAME = "regions.json";


    public Region getId(Long aLong) throws Exception {
        return null;
    }


    public void create(Region region) throws Exception {

    }

    @Override
    public Region getById(Long aLong) throws Exception {
        return null;
    }

    public void save(Region item) {

    }

    public void update(Region item) throws Exception {

    }

    public void delete(Region item) throws Exception {

    }

    public List<Region> getAll() throws Exception {
        return null;
    }


    public List<Region> stringToData(List<String> items) throws Exception {
        return null;
    }


    public List<String> dataToString(List<Region> items) throws Exception {
        return null;
    }

    @Override
    public String dataToString(Region region) {
        return null;
    }

    @Override
    public Long getLastId() throws Exception {
        return null;
    }
}
