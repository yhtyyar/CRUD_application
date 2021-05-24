package controller;

import model.Region;
import repository.RegionRepository;
import repository.json.JsonRepositoryDeserialization;

import java.util.List;

public class RegionController {

    private RegionRepository regionRepository;

    public RegionController() {
        regionRepository = JsonRepositoryDeserialization.getRegionRepository();
    }

    public void save(Region region) {
        regionRepository.save(region);
    }


    public Region getById(Long id) {
        return regionRepository.getById(id);
    }


    public void update(Region region) {
        regionRepository.update(region);
    }


    public void deleteById(Long id) {
        regionRepository.deleteById(id);
    }


    public List<Region> getAll() {
        return regionRepository.getAll();
    }
}
