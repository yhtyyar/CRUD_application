package repository.json;

import model.Region;
import model.StoredData;
import repository.RegionRepository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JsonRegionRepositoryImpl extends JsonRepository<Region>  implements RegionRepository {


    public JsonRegionRepositoryImpl(String fileName, Type listType) {
        super(fileName, listType);
    }

    @Override
    public Region getById(Long id) {

        return (Region) super.getById(id);
    }

    @Override
    public Region save(Region region)  {
        super.save(region);

        return region;
    }

    @Override
    public Region update(Region region)  {
        super.update(region);

        return region;
    }

    @Override
    public void deleteById(Long id)  {

        super.deleteById(id);
    }

    @Override
    public List<Region> getAll()  {

        List <StoredData> storedList = super.getAllRecordings();

        if (storedList.size() == 0) {
            return  new ArrayList<>();
        }
        return storedList.stream().map((r) -> (Region) r)
                .collect(Collectors.toList());
    }
}
