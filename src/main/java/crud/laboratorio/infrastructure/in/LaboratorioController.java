package crud.laboratorio.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.laboratorio.application.CreateLaboratorioUseCase;
import crud.laboratorio.domain.Service.LaboratorioService;
import crud.laboratorio.domain.entity.Laboratorio;

public class LaboratorioController {
    private CreateLaboratorioUseCase createLaboratorioUseCase;
    private LaboratorioService laboratorioService;
    private Scanner scanner;

    public LaboratorioController(CreateLaboratorioUseCase createLaboratorioUseCase,
            LaboratorioService laboratorioService, Scanner scanner) {
        this.createLaboratorioUseCase = createLaboratorioUseCase;
        this.laboratorioService = laboratorioService;
        this.scanner = new Scanner(System.in);
    }

        public void tabla_Laboratorio() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nueva laboratorio");
            System.out.println("2. Editar laboratorio");
            System.out.println("3. Buscar laboratorio");
            System.out.println("4. Eliminar laboratorio");
            System.out.println("5. Salir al menú principal");
            System.out.print("Selecciona una opción: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                continue;
            }

            switch (choice) {
                case 1:
                    addLaboratorio();
                    break;
                case 2:
                    editLaboratorio();
                    break;
                case 3:
                    searchLaboratorio();
                    break;
                case 4:
                    deleteLaboratorio();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public void addLaboratorio() {
        System.out.print("Ingrese el id del laboratorio: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese el nombre del laboratorio: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el codigo de la ciudad del laboratorio: ");
        String cod_ciudad = scanner.nextLine();

        Laboratorio laboratorio = new Laboratorio(id, nombre, cod_ciudad);
        laboratorio.setId(id);
        laboratorio.setNombre(nombre);
        laboratorio.setCod_ciudad(cod_ciudad);

        try {
            createLaboratorioUseCase.execute(laboratorio);
            System.out.println("laboratorio agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar laboratorio  : " + e.getMessage());
        }
    }
    public void editLaboratorio() {
        System.out.print("Ingrese el id del laboratorio que desea editar: ");
        String id = scanner.nextLine();

        Laboratorio laboratorio = laboratorioService.findLaboratorioById(id);
        if (laboratorio == null) {
            System.out.println("el laboratorio con el id ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del laboratorio: ");
        String nuevoNombre = scanner.nextLine();

        laboratorio.setNombre(nuevoNombre);

        System.out.print("Ingrese el nuevo codigo de la ciudad: ");
        String nuevoCod_ciudad = scanner.nextLine();

        laboratorio.setCod_ciudad(nuevoCod_ciudad);

        try {
            laboratorioService.updateLaboratorio(laboratorio);
            System.out.println("laboratorio actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar laboratorio: " + e.getMessage());
            esperarTecla();
        }
    }

    public void searchLaboratorio() {
        System.out.print("Ingrese el id del laboratorio que desea buscar: ");
        String id = scanner.nextLine();

        // Buscar el país
        Laboratorio laboratorio = laboratorioService.findLaboratorioById(id);
        if (laboratorio == null) {
            System.out.println("el laboratorio con el id ingresado no existe.");
            esperarTecla();
            return;
        }

        System.out.println("id: " + laboratorio.getId());
        System.out.println("Nombre: " + laboratorio.getNombre());
        System.out.println("codigo ciudad: " + laboratorio.getCod_ciudad());
        esperarTecla();
    }

    public void deleteLaboratorio() {
        System.out.print("Ingrese el codigo del laboratorio que desea eliminar: ");
        String id = scanner.nextLine();

        try {
            laboratorioService.deleteLaboratorio(id);
            System.out.println("laboratorio eliminada exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar el laboratorio: " + e.getMessage());
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