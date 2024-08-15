package crud.laboratorio.application;

import crud.laboratorio.domain.Service.LaboratorioService;
import crud.laboratorio.domain.entity.Laboratorio;

public class CreateLaboratorioUseCase {
    private final LaboratorioService laboratorioService;

    public CreateLaboratorioUseCase(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    public void execute(Laboratorio laboratorio) {
        laboratorioService.createLaboratorio(laboratorio);
    }
}