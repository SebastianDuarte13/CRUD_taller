package crud.clientes.domain.service;

import crud.clientes.domain.entity.Cliente;

public interface ClienteService {
    Cliente findClienteById(String id);
    void createCliente(Cliente cliente);
    void updateCliente(Cliente cliente);
    void deleteCliente(String id);
}
