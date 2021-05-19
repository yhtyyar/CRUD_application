package service;

import model.Region;

public interface RegionService extends GenericService <Region, Long>{

    void create (String nameRegion) throws Exception;

    void update (Long id, String nameRegion) throws Exception;

}
