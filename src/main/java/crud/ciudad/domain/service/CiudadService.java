package crud.ciudad.domain.service;

import crud.ciudad.domain.entity.Ciudad;

public interface CiudadService {
    Ciudad findCiudadById(String codigo_ciudad);
    void createciudad(Ciudad ciudad);
    void updateciudad(Ciudad ciudad);
    void deleteciudad(String codigo_ciudad);
}
