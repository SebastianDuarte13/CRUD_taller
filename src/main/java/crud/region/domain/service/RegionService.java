package crud.region.domain.service;

import crud.region.domain.entity.Region;

public interface RegionService {
    Region findRegionById(String codigo_reg);
    void createRegion(Region region);
    void updateRegion(Region region);
    void deleteRegion(String codigo_reg);
}
