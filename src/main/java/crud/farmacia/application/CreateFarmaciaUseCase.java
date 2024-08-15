package crud.farmacia.application;

import crud.farmacia.domain.entity.Farmacia;
import crud.farmacia.domain.service.FarmaciaService;

public class CreateFarmaciaUseCase {

    private FarmaciaService farmaciaService;

    public CreateFarmaciaUseCase(FarmaciaService farmaciaService) {
        this.farmaciaService = farmaciaService;
    }
    public void execute(Farmacia farmacia) {
        farmaciaService.createFarmacia(farmacia);
    }
}
