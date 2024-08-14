package crud.principio_activo.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.principio_activo.domain.entity.Activo;
import crud.principio_activo.application.CreateActivoUseCase;
import crud.principio_activo.domain.service.ActivoService;

public class ActivoController {
    private CreateActivoUseCase createActivoUseCase;
    private ActivoService activoService;
    private Scanner scanner;

    public ActivoController(CreateActivoUseCase createActivoUseCase, ActivoService activoService) {
        this.createActivoUseCase = createActivoUseCase;
        this.activoService = activoService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_principio_activo() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nuevo principio_activo");
            System.out.println("2. Editar principio_activo");
            System.out.println("3. Buscar principio_activo");
            System.out.println("4. Eliminar principio_activo");
            System.out.println("5. Salir al menú principal");
            System.out.print("Selecciona una opción: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // Limpiar el buffer
            } catch (InputMismatchException e) {
                scanner.next();  // Limpiar la entrada incorrecta
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                continue;
            }

            switch (choice) {
                case 1:
                    addActivo();
                    break;
                case 2:
                    editActivo();
                    break;
                case 3:
                    searchActivo();
                    break;
                case 4:
                    deleteActivo();
                    break;
                case 5:
                    return;  // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addActivo() {
        System.out.print("Ingrese el id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nombre del activo: ");
        String nombre = scanner.nextLine();

        Activo activo = new Activo();
        activo.setId(id);
        activo.setNombre(nombre);

        try {
            createActivoUseCase.execute(activo);
            System.out.println("Activo agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar activo: " + e.getMessage());
        }
    }

    public void editActivo() {
        System.out.print("Ingrese el id del activo que desea editar: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        Activo activo = activoService.findActivoById(id);
        if (activo == null) {
            System.out.println("El activo con el id ingresado no existe.");
            return;
        }
    
        System.out.print("Ingrese el nuevo nombre del activo: ");
        String nuevoNombre = scanner.nextLine();
    
        activo.setNombre(nuevoNombre);
    
        try {
            activoService.updateActivo(activo);
            System.out.println("Activo actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar activo: " + e.getMessage());
        }
    }

    public void searchActivo() {
        System.out.print("Ingrese el id del activo que desea buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        // Buscar el país
        Activo activo = activoService.findActivoById(id);
        if (activo == null) {
            System.out.println("El activo con el id ingresado no existe.");
            return;
        }
    
        System.out.println("id: " + activo.getId());
        System.out.println("Nombre: " + activo.getNombre());
        esperarTecla();
    }

    public void deleteActivo() {
        System.out.print("Ingrese el id del Activo que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        try {
            activoService.deleteActivo(id);
            System.out.println("Activo eliminado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar Activo: " + e.getMessage());
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
