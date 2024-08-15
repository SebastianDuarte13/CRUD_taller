package crud.clientes.infrastructure.in;

import java.sql.Date;
import java.text.ParseException; // Importación correcta
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

import crud.clientes.application.CreateClienteUseCase;
import crud.clientes.domain.entity.Cliente;
import crud.clientes.domain.service.ClienteService;

public class ClienteController {
    private CreateClienteUseCase createClienteUseCase;
    private ClienteService clienteService;
    private Scanner scanner;

    public ClienteController(CreateClienteUseCase createClienteUseCase, ClienteService clienteService,
            Scanner scanner) {
        this.createClienteUseCase = createClienteUseCase;
        this.clienteService = clienteService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_cliente() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nuevo cliente");
            System.out.println("2. Editar cliente");
            System.out.println("3. Buscar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Salir al menú principal");
            System.out.print("Selecciona una opción: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                continue;
            }

            switch (choice) {
                case 1:
                    addCliente();
                    break;
                case 2:
                    editCliente();
                    break;
                case 3:
                    searchCliente();
                    break;
                case 4:
                    deleteCliente();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addCliente() {
        System.out.print("Ingrese el id del cliente: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese el nombre del cliente: ");
        String nombres = scanner.nextLine();

        System.out.print("Ingrese los apellidos del cliente: ");
        String apellidos = scanner.nextLine();

        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        System.out.print("Ingrese la fecha de nacimiento del cliente (yyyy-MM-dd): ");
        Date fecha_nacimiento = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            fecha_nacimiento = new Date(dateFormat.parse(scanner.nextLine()).getTime());
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido.");
            return;
        }

        Float lon = null;
        while (lon == null) {
            System.out.print("Ingrese la longitud del cliente: ");
            try {
                lon = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }

        Float latitud = null;
        while (latitud == null) {
            System.out.print("Ingrese la latitud del cliente: ");
            try {
                latitud = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }

        System.out.print("Ingrese el código de ciudad del cliente: ");
        String cod_ciudad = scanner.nextLine();

        Cliente cliente = new Cliente(id, nombres, apellidos, email, fecha_nacimiento, lon, latitud, cod_ciudad);

        try {
            createClienteUseCase.execute(cliente);
            System.out.println("Cliente agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    public void editCliente() {
        System.out.print("Ingrese el id del cliente que desea editar: ");
        String id = scanner.nextLine();

        Cliente cliente = clienteService.findClienteById(id);
        if (cliente == null) {
            System.out.println("El cliente con el codigo ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del region: ");
        String nuevoNombres = scanner.nextLine();

        cliente.setNombres(nuevoNombres);

        System.out.print("Ingrese los apellidos del cliente: ");
        String nuevoApellidos = scanner.nextLine();

        cliente.setApellidos(nuevoApellidos);

        System.out.print("Ingrese nuevo el email del cliente: ");
        String nuevoemail = scanner.nextLine();

        cliente.setEmail(nuevoemail);

        System.out.print("Ingrese la nuevo fecha de nacimiento del cliente (yyyy-MM-dd): ");
        Date nuevofecha_nacimiento = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            nuevofecha_nacimiento = new Date(dateFormat.parse(scanner.nextLine()).getTime());
        } catch (ParseException e) {
            System.out.println("Formato de fecha inválido.");
            return;
        }

        cliente.setFecha_nacimiento(nuevofecha_nacimiento);

        System.out.print("Ingrese la nuevo longitud del cliente: ");
        Float nuevolon = scanner.nextFloat();

        cliente.setLon(nuevolon);

        System.out.print("Ingrese la nuevo latitud del cliente: ");
        Float nuevolatitud = scanner.nextFloat();

        cliente.setLatitud(nuevolatitud);

        scanner.nextLine(); // Consumir la nueva línea sobrante

        System.out.print("Ingrese el nuevo código de ciudad del cliente: ");
        String nuevocod_ciudad = scanner.nextLine();

        cliente.setCod_ciudad(nuevocod_ciudad);

        try {
            clienteService.updateCliente(cliente);
            System.out.println("cliente actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar Cliente: " + e.getMessage());
        }
    }

    public void searchCliente() {
        System.out.print("Ingrese el id de la Cliente que desea buscar: ");
        String id = scanner.nextLine();

        // Buscar el país
        Cliente cliente = clienteService.findClienteById(id);
        if (cliente == null) {
            System.out.println("la Cliente con el codigo ingresado no existe.");
            return;
        }

        System.out.println("id: " + cliente.getId());
        System.out.println("nombres: " + cliente.getNombres());
        System.out.println("apellidos: " + cliente.getApellidos());
        System.out.println("email: " + cliente.getEmail());
        System.out.println("fecha_nacimiento: " + cliente.getFecha_nacimiento());
        System.out.println("lon: " + cliente.getLon());
        System.out.println("latitud: " + cliente.getLatitud());
        System.out.println("cod_ciudad: " + cliente.getCod_ciudad());
        esperarTecla();
    }

    public void deleteCliente() {
        System.out.print("Ingrese el id del cliente que desea eliminar: ");
        String id = scanner.nextLine();

        try {
            clienteService.deleteCliente(id);
            System.out.println("Cliente eliminada exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar la region: " + e.getMessage());
            esperarTecla();
        }
    }

    private void limpiarConsola() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void esperarTecla() {
        System.out.println("Presiona cualquier tecla para continuar...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
