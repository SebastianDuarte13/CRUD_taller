package crud.unidad_medida.application;

import crud.unidad_medida.domain.entity.Medida;
import crud.unidad_medida.domain.service.MedidaService;

public class CreateMedidaUseCase {
    private final MedidaService medidaService;

    public CreateMedidaUseCase(MedidaService medidaService) { 
        this.medidaService = medidaService;
    }

    public void execute(Medida medida) {
        medidaService.createMedida(medida);
    }
}
