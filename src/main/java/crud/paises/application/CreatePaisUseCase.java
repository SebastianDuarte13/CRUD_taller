package crud.paises.application;

import crud.paises.domain.entity.Pais;
import crud.paises.domain.service.PaisService;

public class CreatePaisUseCase {
    private final PaisService paisService;

    public CreatePaisUseCase(PaisService paisService) { // Corregir el nombre del constructor
        this.paisService = paisService;
    }

    public void execute(Pais pais) {
        paisService.createPais(pais);
    }
}
