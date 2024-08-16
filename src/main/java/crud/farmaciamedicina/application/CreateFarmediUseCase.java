package crud.farmaciamedicina.application;

import crud.farmaciamedicina.domain.entity.Farmedi;
import crud.farmaciamedicina.domain.service.FarmediService;

public class CreateFarmediUseCase {
    private final FarmediService farmediService;

    public CreateFarmediUseCase(FarmediService farmediService) {
        this.farmediService = farmediService;
    }

    public void execute(Farmedi farmedi) {
        farmediService.createFarmedi(farmedi); // Asegúrate de que este método esté en FarmediService
    }
}
