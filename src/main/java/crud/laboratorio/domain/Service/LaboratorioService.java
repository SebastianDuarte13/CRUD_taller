package crud.laboratorio.domain.Service;

import crud.laboratorio.domain.entity.Laboratorio;

public interface LaboratorioService {
    Laboratorio findLaboratorioById(String id);
    void createLaboratorio(Laboratorio laboratorio);
    void updateLaboratorio(Laboratorio laboratorio);
    void deleteLaboratorio(String id);
}