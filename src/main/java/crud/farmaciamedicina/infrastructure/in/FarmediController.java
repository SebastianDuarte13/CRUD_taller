package crud.farmaciamedicina.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.farmaciamedicina.application.CreateFarmediUseCase;
import crud.farmaciamedicina.domain.entity.Farmedi;
import crud.farmaciamedicina.domain.service.FarmediService;

public class FarmediController {
    private CreateFarmediUseCase createFarmediUseCase;
    private FarmediService farmediService;
    private Scanner scanner;

    public FarmediController(CreateFarmediUseCase createFarmediUseCase, FarmediService farmediService, Scanner scanner) {
        this.createFarmediUseCase = createFarmediUseCase;
        this.farmediService = farmediService;
        this.scanner = scanner;
    }

    public void tabla_Farmedi() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nueva Farmedi");
            System.out.println("2. Editar Farmedi");
            System.out.println("3. Buscar Farmedi");
            System.out.println("4. Eliminar Farmedi");
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
                    addFarmedi();
                    break;
                case 2:
                    editFarmedi();
                    break;
                case 3:
                    searchFarmedi();
                    break;
                case 4:
                    deleteFarmedi();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addFarmedi() {
        System.out.print("Ingrese el id_farmacia del farmedi: ");
        String id_farmacia = scanner.nextLine();

        System.out.print("Ingrese el id_medicina del Farmedi: ");
        String id_medicina = scanner.nextLine();

        Float prince = null;
        while (prince == null) {
            System.out.print("Ingrese el codigo de la prince del Farmedi: ");
            try {
                prince = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }

        Farmedi farmedi = new Farmedi(id_farmacia, id_medicina, prince);

        try {
            createFarmediUseCase.execute(farmedi);
            System.out.println("farmedi agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar farmedi  : " + e.getMessage());
        }
    }

    public void editFarmedi() {
        System.out.print("Ingrese el id_farmacia desea editar: ");
        String id_farmacia = scanner.nextLine();

        Farmedi farmedi = farmediService.findFarmediById(id_farmacia);
        if (farmedi == null) {
            System.out.println("el farmedi con el id ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo id_medicina: ");
        String nuevoId_medicina = scanner.nextLine();

        farmedi.setId_medicina(nuevoId_medicina);

        Float nuevoPrince = null;
        while (nuevoPrince == null) {
            System.out.print("Ingrese la nueva longitud de la farmacia: ");
            try {
                nuevoPrince = Float.parseFloat(scanner.nextLine());
                farmedi.setPrince(nuevoPrince);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }

        try {
            farmediService.updateFarmedi(farmedi);
            System.out.println("farmedi actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar farmedi: " + e.getMessage());
            esperarTecla();
        }
    }

    public void searchFarmedi() {
        System.out.print("Ingrese el id_farmacia del farmedi a buscar: ");
        String id_farmacia = scanner.nextLine();

        Farmedi farmedi = farmediService.findFarmediById(id_farmacia);
        if (farmedi != null) {
            System.out.println("ID Farmacia: " + farmedi.getId_farmacia());
            System.out.println("ID Medicina: " + farmedi.getId_medicina());
            System.out.println("Prince: " + farmedi.getPrince());
        } else {
            System.out.println("No se encontró el farmedi con el id ingresado.");
        }
        esperarTecla();
    }

    public void deleteFarmedi() {
        System.out.print("Ingrese el id_farmacia del farmedi a eliminar: ");
        String id_farmacia = scanner.nextLine();

        try {
            farmediService.deleteFarmedi(id_farmacia);
            System.out.println("farmedi eliminado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar farmedi: " + e.getMessage());
            esperarTecla();
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
}
