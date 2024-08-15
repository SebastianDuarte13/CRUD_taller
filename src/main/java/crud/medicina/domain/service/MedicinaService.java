package crud.medicina.domain.service;

import crud.medicina.domain.entity.Medicina;

public interface MedicinaService {
    Medicina findMedicinaById(String id);
    void createMedicina(Medicina medicina);
    void updateMedicina(Medicina medicina);
    void deleteMedicina(String id);
}
