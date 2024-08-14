package crud.paises.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.paises.domain.entity.Pais;
import crud.paises.application.CreatePaisUseCase;
import crud.paises.domain.service.PaisService;

public class PaisController {
    private CreatePaisUseCase createPaisUseCase;
    private PaisService paisService;
    private Scanner scanner;

    public PaisController(CreatePaisUseCase createPaisUseCase, PaisService paisService) {
        this.createPaisUseCase = createPaisUseCase;
        this.paisService = paisService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_paises() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nuevo país");
            System.out.println("2. Editar país");
            System.out.println("3. Buscar país");
            System.out.println("4. Eliminar país");
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
                    addPais();
                    break;
                case 2:
                    editPais();
                    break;
                case 3:
                    searchPais();
                    break;
                case 4:
                    deletePais();
                    break;
                case 5:
                    return;  // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addPais() {
        System.out.print("Ingrese el código: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese el nombre del país: ");
        String nombre = scanner.nextLine();

        Pais pais = new Pais();
        pais.setCodigo(codigo);
        pais.setNombre(nombre);

        try {
            createPaisUseCase.execute(pais);
            System.out.println("País agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar país: " + e.getMessage());
        }
    }

    public void editPais() {
        System.out.print("Ingrese el código del país que desea editar: ");
        String codigo = scanner.nextLine();
    
        // Buscar el país
        Pais pais = paisService.findPaisById(codigo);
        if (pais == null) {
            System.out.println("El país con el código ingresado no existe.");
            return;
        }
    
        System.out.print("Ingrese el nuevo nombre del país: ");
        String nuevoNombre = scanner.nextLine();
    
        pais.setNombre(nuevoNombre);
    
        try {
            paisService.updatePais(pais);
            System.out.println("País actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar país: " + e.getMessage());
        }
    }

    public void searchPais() {
        System.out.print("Ingrese el código del país que desea buscar: ");
        String codigo = scanner.nextLine();
    
        // Buscar el país
        Pais pais = paisService.findPaisById(codigo);
        if (pais == null) {
            System.out.println("El país con el código ingresado no existe.");
            return;
        }
    
        System.out.println("Código: " + pais.getCodigo());
        System.out.println("Nombre: " + pais.getNombre());
        esperarTecla();
    }

    public void deletePais() {
        System.out.print("Ingrese el código del país que desea eliminar: ");
        String codigo = scanner.nextLine();
    
        try {
            paisService.deletePais(codigo);
            System.out.println("País eliminado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar país: " + e.getMessage());
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
