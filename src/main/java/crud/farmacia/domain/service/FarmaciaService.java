package crud.farmacia.domain.service;

import crud.farmacia.domain.entity.Farmacia;

public interface FarmaciaService {
    void createFarmacia(Farmacia farmacia);
    Farmacia findFarmaciaById(String id);
    void updateFarmacia(Farmacia farmacia);
    void deleteFarmacia(String id);
}
