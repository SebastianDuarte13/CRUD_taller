package crud.medicina.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.medicina.application.CreateMedicinaUseCase;
import crud.medicina.domain.entity.Medicina;
import crud.medicina.domain.service.MedicinaService;

public class MedicinaController {
    private CreateMedicinaUseCase createMedicinaUseCase;
    private MedicinaService medicinaService;
    private Scanner scanner;
    public MedicinaController(CreateMedicinaUseCase createMedicinaUseCase, MedicinaService medicinaService,
            Scanner scanner) {
        this.createMedicinaUseCase = createMedicinaUseCase;
        this.medicinaService = medicinaService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_Medicina() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nueva medicina");
            System.out.println("2. Editar medicina");
            System.out.println("3. Buscar medicina");
            System.out.println("4. Eliminar medicina");
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
                    addMedicina();
                    break;
                case 2:
                    editMedicina();
                    break;
                case 3:
                    searchMedicina();
                    break;
                case 4:
                    deleteMedicina();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addMedicina() {
        System.out.print("Ingrese el id de la medicina: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese el proceedings de la medicina: ");
        String proceedings = scanner.nextLine();

        System.out.print("Ingrese los nombres de la medicina: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese el healthregister de la medicina: ");
        String healthregister = scanner.nextLine();

        System.out.print("Ingrese la descripcion de medicina: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la descripcionshort de la medicina: ");
        String descripcionshort = scanner.nextLine();

        System.out.print("Ingrese el nombrerol de medicina: ");
        String nombrerol = scanner.nextLine();

        System.out.print("Ingrese el cod_admin de medicina: ");
        String cod_admin = scanner.nextLine();

        System.out.print("Ingrese el cod_activo de medicina: ");
        String cod_activo = scanner.nextLine();

        System.out.print("Ingrese el cod_medida de medicina: ");
        String cod_medida = scanner.nextLine();
        
        System.out.print("Ingrese el cod_lab de medicina: ");
        String cod_lab = scanner.nextLine();

        Medicina medicina = new Medicina(id, proceedings, nombre, healthregister, descripcion, descripcionshort, nombrerol, cod_admin, cod_activo, cod_medida, cod_lab);

        try {
            createMedicinaUseCase.execute(medicina);
            System.out.println("Medicina agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
        }
    }

    public void editMedicina() {
        System.out.print("Ingrese el id de la Medicina que desea editar: ");
        String id = scanner.nextLine();

        Medicina medicina = medicinaService.findMedicinaById(id);
        if (medicina == null) {
            System.out.println("la medicina con el codigo ingresado no existe.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del region: ");
        String proceedings = scanner.nextLine();

        medicina.setProceedings(proceedings);

        System.out.print("Ingrese la descripcion: ");
        String nombre = scanner.nextLine();

        medicina.setNombre(nombre);
        
        System.out.print("Ingrese la descripcion: ");
        String healthregister = scanner.nextLine();

        medicina.setHealthregister(healthregister);

        System.out.print("Ingrese la descripcion: ");
        String descripcion = scanner.nextLine();

        medicina.setDescripcion(descripcion);

        System.out.print("Ingrese nuevo el descripcionshort : ");
        String descripcionshort = scanner.nextLine();

        medicina.setDescripcionshort(descripcionshort);

        System.out.print("Ingrese el nombrerol nombre: ");
        String nombrerol = scanner.nextLine();

        medicina.setNombrerol(nombrerol);

        System.out.print("Ingrese el nuevo cod_admin: ");
        String cod_admin = scanner.nextLine();

        medicina.setCod_admin(cod_admin);

        System.out.print("Ingrese el nuevo cod_activo : ");
        String cod_activo = scanner.nextLine();

        medicina.setCod_activo(cod_activo);


        System.out.print("Ingrese el nuevo cod_medida: ");
        String cod_medida = scanner.nextLine();

        medicina.setCod_medida(cod_medida);

        System.out.print("Ingrese el nuevo cod_lab: ");
        String cod_lab = scanner.nextLine();

        medicina.setCod_lab(cod_lab);


        try {
            medicinaService.updateMedicina(medicina);
            System.out.println("Medicina actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar Cliente: " + e.getMessage());
        }
    }

    public void searchMedicina() {
        System.out.print("Ingrese el id de la medicina que desea buscar: ");
        String id = scanner.nextLine();

        // Buscar el país
        Medicina medicina = medicinaService.findMedicinaById(id);
        if (medicina == null) {
            System.out.println("la medicina con el codigo ingresado no existe.");
            return;
        }

        System.out.println("id: " + medicina.getId());
        System.out.println("Proceedings: " + medicina.getProceedings());
        System.out.println("Nombre: " + medicina.getNombre());
        System.out.println("healthregister: " + medicina.getHealthregister());
        System.out.println("descripcion: " + medicina.getDescripcion());
        System.out.println("descripcionshort: " + medicina.getDescripcionshort());
        System.out.println("nombrerol: " + medicina.getNombrerol());
        System.out.println("cod_admin: " + medicina.getCod_admin());
        System.out.println("cod_activo: " + medicina.getCod_activo());
        System.out.println("cod_medida: " + medicina.getCod_medida());
        System.out.println("cod_lab: " + medicina.getCod_lab());
        esperarTecla();
    }

    public void deleteMedicina() {
        System.out.print("Ingrese el id de la medicina que desea eliminar: ");
        String id = scanner.nextLine();

        try {
            medicinaService.deleteMedicina(id);
            System.out.println("medicina eliminada exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar medicina: " + e.getMessage());
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
