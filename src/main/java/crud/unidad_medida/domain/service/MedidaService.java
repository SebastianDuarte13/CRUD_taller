package crud.unidad_medida.domain.service;

import crud.unidad_medida.domain.entity.Medida;

public interface MedidaService {
    Medida findMedidaById(int id); 
    void createMedida(Medida medida);
    void updateMedida(Medida medida); 
    void deleteMedida(int id); 
}