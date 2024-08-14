package crud.principio_activo.application;

import crud.principio_activo.domain.entity.Activo;
import crud.principio_activo.domain.service.ActivoService;

public class CreateActivoUseCase {
    private final ActivoService activoService;

    public CreateActivoUseCase(ActivoService activoService) { // Corregir el nombre del constructor
        this.activoService = activoService;
    }

    public void execute(Activo activo) {
        activoService.createActivo(activo);
    }
}
