package crud.ciudad.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.ciudad.apllication.CreateCiudadUseCase;
import crud.ciudad.domain.service.CiudadService;
import crud.ciudad.domain.entity.Ciudad;

public class CiudadController {
    private CreateCiudadUseCase createCiudadUseCase;
    private CiudadService ciudadService;
    private Scanner scanner;

    public CiudadController(CreateCiudadUseCase createCiudadUseCase, CiudadService ciudadService, Scanner scanner) {
        this.createCiudadUseCase = createCiudadUseCase;
        this.ciudadService = ciudadService;
        this.scanner =  new Scanner(System.in);
    };
    



    public void tabla_ciudad() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nueva ciudad");
            System.out.println("2. Editar ciudad");
            System.out.println("3. Buscar ciudad");
            System.out.println("4. Eliminar ciudad");
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
                    addCiudad();
                    break;
                case 2:
                    editCiudad();
                    break;
                case 3:
                    searchCiudad();
                    break;
                case 4:
                    deleteCiudad();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    public void addCiudad() {
        System.out.print("Ingrese el codigo de la ciudad: ");
        String codigo_ciudad = scanner.nextLine();

        System.out.print("Ingrese el nombre de la ciudad: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el codigo de la region: ");
        String cod_region = scanner.nextLine();

        Ciudad ciudad = new Ciudad(cod_region, cod_region, cod_region);
        ciudad.setCodigo_ciudad(codigo_ciudad);
        ciudad.setNombre(nombre);
        ciudad.setCod_region(cod_region);

        try {
            createCiudadUseCase.execute(ciudad);
            System.out.println("Ciudad agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar Ciudad: " + e.getMessage());
        }
    }
    public void editCiudad() {
        System.out.print("Ingrese el codigo de la ciudad que desea editar: ");
        String codigo_ciudad = scanner.nextLine();

        Ciudad ciudad = ciudadService.findCiudadById(codigo_ciudad);
        if (ciudad == null) {
            System.out.println("La ciudad con el codigo ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre de la ciudad: ");
        String nuevoNombre = scanner.nextLine();

        ciudad.setNombre(nuevoNombre);

        System.out.print("Ingrese el nuevo codigo de la region: ");
        String nuevoCod_region = scanner.nextLine();

        ciudad.setCod_region(nuevoCod_region);

        try {
            ciudadService.updateciudad(ciudad);
            System.out.println("Ciudad actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar Ciudad: " + e.getMessage());
            esperarTecla();
        }
    }

    public void searchCiudad() {
        System.out.print("Ingrese el codigo de la ciudad que desea buscar: ");
        String codigo_ciudad = scanner.nextLine();

        // Buscar el país
        Ciudad ciudad = ciudadService.findCiudadById(codigo_ciudad);
        if (ciudad == null) {
            System.out.println("la ciudad con el codigo ingresado no existe.");
            esperarTecla();
            return;
        }

        System.out.println("codigo_ciudad: " + ciudad.getCodigo_ciudad());
        System.out.println("Nombre: " + ciudad.getNombre());
        System.out.println("codigo_region: " + ciudad.getCod_region());
        esperarTecla();
    }

    public void deleteCiudad() {
        System.out.print("Ingrese el codigo de la ciudad que desea eliminar: ");
        String codigo_ciudad = scanner.nextLine();

        try {
            ciudadService.deleteciudad(codigo_ciudad);
            System.out.println("Ciudad eliminada exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar la ciudad: " + e.getMessage());
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

