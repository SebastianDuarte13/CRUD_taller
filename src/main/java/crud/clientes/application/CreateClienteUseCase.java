package crud.clientes.application;

import crud.clientes.domain.entity.Cliente;
import crud.clientes.domain.service.ClienteService;

public class CreateClienteUseCase {
    private final ClienteService clienteService;

    public CreateClienteUseCase(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public void execute(Cliente cliente) {
        clienteService.createCliente(cliente);
    }
}
