package crud.region.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.region.domain.entity.Region;
import crud.region.application.CreateRegionUseCase;
import crud.region.domain.service.RegionService;

public class RegionController {
    private CreateRegionUseCase createRegionUseCase;
    private RegionService regionService;
    private Scanner scanner;

    public RegionController(CreateRegionUseCase createRegionUseCase, RegionService regionService) {
        this.createRegionUseCase = createRegionUseCase;
        this.regionService = regionService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_region() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nueva region");
            System.out.println("2. Editar region");
            System.out.println("3. Buscar region");
            System.out.println("4. Eliminar region");
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
                    addRegion();
                    break;
                case 2:
                    editRegion();
                    break;
                case 3:
                    searchRegion();
                    break;
                case 4:
                    deleteRegion();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addRegion() {
        System.out.print("Ingrese el codigo de la region: ");
        String codigo_reg = scanner.nextLine();

        System.out.print("Ingrese el nombre de la Region: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el codigo del pais: ");
        String codigo_pais = scanner.nextLine();

        Region region = new Region(codigo_pais, codigo_pais, codigo_pais);
        region.setCodigo_reg(codigo_reg);
        region.setNombre(nombre);
        region.setCodigo_pais(codigo_pais);

        try {
            createRegionUseCase.execute(region);
            System.out.println("Region agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar region: " + e.getMessage());
        }
    }

    public void editRegion() {
        System.out.print("Ingrese el id de la region que desea editar: ");
        String codigo_reg = scanner.nextLine();

        Region region = regionService.findRegionById(codigo_reg);
        if (region == null) {
            System.out.println("El region con el codigo ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del region: ");
        String nuevoNombre = scanner.nextLine();

        region.setNombre(nuevoNombre);

        System.out.print("Ingrese el nuevo codigo del pais: ");
        String nuevoCodigo_pais = scanner.nextLine();

        region.setCodigo_pais(nuevoCodigo_pais);

        try {
            regionService.updateRegion(region);
            System.out.println("Region actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar region: " + e.getMessage());
        }
    }

    public void searchRegion() {
        System.out.print("Ingrese el codigo de la region que desea buscar: ");
        String codigo_reg = scanner.nextLine();

        // Buscar el país
        Region region = regionService.findRegionById(codigo_reg);
        if (region == null) {
            System.out.println("la region con el codigo ingresado no existe.");
            return;
        }

        System.out.println("codigo_reg: " + region.getCodigo_reg());
        System.out.println("Nombre: " + region.getNombre());
        System.out.println("codigo_pais: " + region.getCodigo_pais());
        esperarTecla();
    }

    public void deleteRegion() {
        System.out.print("Ingrese el codigo de la Region que desea eliminar: ");
        String codigo_reg = scanner.nextLine();

        try {
            regionService.deleteRegion(codigo_reg);
            System.out.println("Region eliminada exitosamente!");
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
