package service.impl;

import model.Region;
import repository.RegionRepository;
import repository.WriterRepository;
import service.RegionService;

import java.util.List;

public class RegionServiceImpl implements RegionService {

    private RegionRepository regionRepository;


    public RegionServiceImpl (RegionRepository regionRepository) {

        this.regionRepository = regionRepository;
    }

    public Region getById(Long id) throws Exception {
        return regionRepository.getById(id);
    }


    public void create(String nameRegion) throws Exception {
        Region region = new Region();

        region.setId(regionRepository.getLastId() + 1);
        region.setNameRegion(nameRegion);

        regionRepository.save(region);
    }

    public void update(Long id, String nameRegion) throws Exception {
        Region region = new Region();

        region.setId(id);
        region.setNameRegion(nameRegion);

        regionRepository.update(region);
    }



    public void delete(Long id) throws Exception {

        //переделать удаление
        regionRepository.delete(getById(id));
    }

    public List<Region> getAll() throws Exception {
        return regionRepository.getAll();
    }
}
