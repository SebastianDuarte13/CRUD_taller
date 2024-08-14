package crud.paises.domain.service;

import crud.paises.domain.entity.Pais;

public interface PaisService {
    Pais findPaisById(String codigo); 
    void createPais(Pais pais);
    void updatePais(Pais pais); 
    void deletePais(String codigo); 
}
