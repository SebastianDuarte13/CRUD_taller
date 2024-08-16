package crud.farmaciamedicina.domain.service;

import crud.farmaciamedicina.domain.entity.Farmedi;

public interface FarmediService {
    Farmedi findFarmediById(String id_farmacia);
    void createFarmedi(Farmedi farmedi); // Este método debe estar en la interfaz
    void updateFarmedi(Farmedi farmedi);
    void deleteFarmedi(String id_farmacia);
}
