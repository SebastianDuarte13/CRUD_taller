package crud.medicina.application;

import crud.medicina.domain.entity.Medicina;
import crud.medicina.domain.service.MedicinaService;

public class CreateMedicinaUseCase {
    private final MedicinaService medicinaService;

    public CreateMedicinaUseCase(MedicinaService medicinaService) {
        this.medicinaService = medicinaService;
    }

    public void execute(Medicina medicina){
        medicinaService.createMedicina(medicina);
    }
}
