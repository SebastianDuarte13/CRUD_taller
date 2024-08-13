package crud.paises.domain.service;

import crud.paises.domain.entity.Pais;

public interface PaisService {
    Pais findPaisById(String codigo); // Cambiado a String para coincidir con el código de país
    void createPais(Pais pais);
    void updatePais(Pais pais); // Nuevo método para actualizar un país
    void deletePais(String codigo); // Nuevo método para eliminar un país
}
