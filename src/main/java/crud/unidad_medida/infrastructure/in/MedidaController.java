package crud.unidad_medida.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.unidad_medida.application.CreateMedidaUseCase;
import crud.unidad_medida.domain.entity.Medida;
import crud.unidad_medida.domain.service.MedidaService;

public class MedidaController {
    private CreateMedidaUseCase createMedidaUseCase;
    private MedidaService medidaService;
    private Scanner scanner;

    public MedidaController(CreateMedidaUseCase createMedidaUseCase, MedidaService medidaService) {
        this.createMedidaUseCase = createMedidaUseCase;
        this.medidaService = medidaService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_unidad_medida() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nuevo unidad_medida");
            System.out.println("2. Editar unidad_medida");
            System.out.println("3. Buscar unidad_medida");
            System.out.println("4. Eliminar unidad_medida");
            System.out.println("5. Salir al menú principal");
            System.out.print("Selecciona una opción: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar la entrada incorrecta
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                continue;
            }

            switch (choice) {
                case 1:
                    addMedida();
                    break;
                case 2:
                    editMedida();
                    break;
                case 3:
                    searchMedida();
                    break;
                case 4:
                    deleteMedida();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addMedida() {
        System.out.print("Ingrese el id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese el nombre del medida: ");
        String nombre = scanner.nextLine();

        Medida medida = new Medida();
        medida.setId(id);
        medida.setNombre(nombre);

        try {
            createMedidaUseCase.execute(medida);
            System.out.println("Medida agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar medida: " + e.getMessage());
        }
    }

    public void editMedida() {
        System.out.print("Ingrese el id del medida que desea editar: ");
        int id = Integer.parseInt(scanner.nextLine());

        Medida medida = medidaService.findMedidaById(id);
        if (medida == null) {
            System.out.println("El medida con el id ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del medida: ");
        String nuevoNombre = scanner.nextLine();

        medida.setNombre(nuevoNombre);

        try {
            medidaService.updateMedida(medida);
            System.out.println("Medida actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar medida: " + e.getMessage());
        }
    }

    public void searchMedida() {
        System.out.print("Ingrese el id del medida que desea buscar: ");
        int id = Integer.parseInt(scanner.nextLine());

        // Buscar el país
        Medida medida = medidaService.findMedidaById(id);
        if (medida == null) {
            System.out.println("El medida con el id ingresado no existe.");
            return;
        }

        System.out.println("id: " + medida.getId());
        System.out.println("Nombre: " + medida.getNombre());
        esperarTecla();
    }

    public void deleteMedida() {
        System.out.print("Ingrese el id del Medida que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());

        try {
            medidaService.deleteMedida(id);
            System.out.println("Medida eliminado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar Medida: " + e.getMessage());
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
