package crud.ciudad.apllication;

import crud.ciudad.domain.entity.Ciudad;
import crud.ciudad.domain.service.CiudadService;

public class CreateCiudadUseCase {
    private final CiudadService ciudadService;

    public CreateCiudadUseCase(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    public void execute(Ciudad ciudad) {
        ciudadService.createciudad(ciudad);
    }
}
