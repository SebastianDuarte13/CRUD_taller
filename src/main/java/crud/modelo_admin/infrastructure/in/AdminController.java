package crud.modelo_admin.infrastructure.in;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.modelo_admin.application.CreateAdminUseCase;
import crud.modelo_admin.domain.entity.Admin;
import crud.modelo_admin.domain.service.AdminService;

public class AdminController {
private CreateAdminUseCase createAdminUseCase;
    private AdminService adminService;
    private Scanner scanner;

    public AdminController(CreateAdminUseCase createAdminUseCase, AdminService adminService) {
        this.createAdminUseCase = createAdminUseCase;
        this.adminService = adminService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_admin() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nuevo modelo_admin");
            System.out.println("2. Editar modelo_admin");
            System.out.println("3. Buscar modelo_admin");
            System.out.println("4. Eliminar modelo_admin");
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
                    addAdmin();
                    break;
                case 2:
                    editAdmin();
                    break;
                case 3:
                    searchAdmin();
                    break;
                case 4:
                    deleteAdmin();
                    break;
                case 5:
                    return;  // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addAdmin() {
        System.out.print("Ingrese el id: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Ingrese descripcion del modelo_admin: ");
        String descripcion = scanner.nextLine();

        Admin admin = new Admin(id, descripcion);
        admin.setId(id);
        admin.setDescripcion(descripcion);

        try {
            createAdminUseCase.execute(admin);
            System.out.println("Admin agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar admin: " + e.getMessage());
        }
    }

    public void editAdmin() {
        System.out.print("Ingrese el id del admin que desea editar: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        Admin admin = adminService.findAdminById(id);
        if (admin == null) {
            System.out.println("El admin con el id ingresado no existe.");
            esperarTecla();
            return;
        }
    
        System.out.print("Ingrese la nueva descipcion del admin: ");
        String nuevoDescripcion = scanner.nextLine();
    
        admin.setDescripcion(nuevoDescripcion);
    
        try {
            adminService.updateAdmin(admin);
            System.out.println("Admin actualizado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar admin: " + e.getMessage());
        }
    }

    public void searchAdmin() {
        System.out.print("Ingrese el id del admin que desea buscar: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        Admin admin = adminService.findAdminById(id);
        if (admin == null) {
            System.out.println("El admin con el id ingresado no existe.");
            esperarTecla();
            return;
        }
    
        System.out.println("id: " + admin.getId());
        System.out.println("Descripcion: " + admin.getDescripcion());
        esperarTecla();
    }

    public void deleteAdmin() {
        System.out.print("Ingrese el id del admin que desea eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
    
        try {
            adminService.deleteAdmin(id);
            System.out.println("Admin eliminado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al eliminar admin: " + e.getMessage());
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
