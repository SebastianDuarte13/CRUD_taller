package crud.region.application;


import crud.region.domain.entity.Region;
import crud.region.domain.service.RegionService;

public class CreateRegionUseCase {
private final RegionService regionService;

    public CreateRegionUseCase(RegionService regionService) { // Corregir el nombre del constructor
        this.regionService = regionService;
    }

    public void execute(Region region) {
        regionService.createRegion(region);
    }
}
