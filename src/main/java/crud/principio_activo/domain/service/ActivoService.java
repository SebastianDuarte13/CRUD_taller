package crud.principio_activo.domain.service;

import crud.principio_activo.domain.entity.Activo;

public interface ActivoService {
    Activo findActivoById(int id); 
    void createActivo(Activo activo);
    void updateActivo(Activo activo); 
    void deleteActivo(int id); 
}
