package crud.farmacia.infrastructure.in;


import java.util.InputMismatchException;
import java.util.Scanner;

import crud.farmacia.application.CreateFarmaciaUseCase;
import crud.farmacia.domain.entity.Farmacia;
import crud.farmacia.domain.service.FarmaciaService;

public class FarmaciaController {
    private CreateFarmaciaUseCase createFarmaciaUseCase;
    private FarmaciaService farmaciaService;
    private Scanner scanner;

    public FarmaciaController(CreateFarmaciaUseCase createFarmaciaUseCase, FarmaciaService farmaciaService,
            Scanner scanner) {
        this.createFarmaciaUseCase = createFarmaciaUseCase;
        this.farmaciaService = farmaciaService;
        this.scanner = new Scanner(System.in);
    }

    public void tabla_Farmacia() {
        while (true) {
            limpiarConsola();
            System.out.println("1. Añadir nuevo farmacia");
            System.out.println("2. Editar farmacia");
            System.out.println("3. Buscar farmacia");
            System.out.println("4. Eliminar farmacia");
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
                    addFarmacia();
                    break;
                case 2:
                    editFarmacia();
                    break;
                case 3:
                    searchFarmacia();
                    break;
                case 4:
                    deletefarmacia();
                    break;
                case 5:
                    return; // Salir al menú principal
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public void addFarmacia() {
        System.out.print("Ingrese el id de la farmacia: ");
        String id = scanner.nextLine();

        System.out.print("Ingrese el nombre de la farmacia: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la direccion de la farmacia: ");
        String direccion = scanner.nextLine();

        Float longitud = null;
        while (longitud == null) {
            System.out.print("Ingrese la longitud de la farmacia: ");
            try {
                longitud = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }

        Float lat = null;
        while (lat == null) {
            System.out.print("Ingrese la latitud de la farmacia: ");
            try {
                lat = Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }

        System.out.print("Ingrese el código de ciudad de la farmacia: ");
        String cod_ciudad = scanner.nextLine();

        System.out.print("Ingrese el logo de la farmacia: ");
        String logo = scanner.nextLine();

        Farmacia farmacia = new Farmacia(id, nombre, direccion, longitud, lat, cod_ciudad, logo);

        try {
            createFarmaciaUseCase.execute(farmacia);
            System.out.println("Farmacia agregado exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al agregar Farmacia: " + e.getMessage());
        }
    }

    public void editFarmacia() {
        System.out.print("Ingrese el id de la farmacia que desea editar: ");
        String id = scanner.nextLine();
    
        Farmacia farmacia = farmaciaService.findFarmaciaById(id);
        if (farmacia == null) {
            System.out.println("El Farmacia con el código ingresado no existe.");
            return;
        }
    
        System.out.print("Ingrese el nuevo nombre de la farmacia: ");
        String nuevoNombre = scanner.nextLine();
        farmacia.setNombre(nuevoNombre);
    
        System.out.print("Ingrese la dirección de la farmacia: ");
        String nuevaDireccion = scanner.nextLine();
        farmacia.setDireccion(nuevaDireccion);
    
        Float nuevaLongitud = null;
        while (nuevaLongitud == null) {
            System.out.print("Ingrese la nueva longitud de la farmacia: ");
            try {
                nuevaLongitud = Float.parseFloat(scanner.nextLine());
                farmacia.setLongitud(nuevaLongitud);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }
    
        Float nuevaLatitud = null;
        while (nuevaLatitud == null) {
            System.out.print("Ingrese la nueva latitud de la farmacia: ");
            try {
                nuevaLatitud = Float.parseFloat(scanner.nextLine());
                farmacia.setLat(nuevaLatitud);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Por favor, introduce un número de punto flotante.");
            }
        }
    
        System.out.print("Ingrese el nuevo código de ciudad de la farmacia: ");
        String nuevoCodCiudad = scanner.nextLine();
        farmacia.setCod_ciudad(nuevoCodCiudad);
    
        System.out.print("Ingrese el nuevo logo de la farmacia: ");
        String nuevoLogo = scanner.nextLine();
        farmacia.setLogo(nuevoLogo);
    
        try {
            farmaciaService.updateFarmacia(farmacia);
            System.out.println("Farmacia actualizada exitosamente!");
            esperarTecla();
        } catch (Exception e) {
            System.out.println("Error al actualizar farmacia: " + e.getMessage());
        }
    }
    

    public void searchFarmacia() {
        System.out.print("Ingrese el id de la farmacia que desea buscar: ");
        String id = scanner.nextLine();

        // Buscar el país
        Farmacia farmacia = farmaciaService.findFarmaciaById(id);
        if (farmacia == null) {
            System.out.println("la farmacia con el codigo ingresado no existe.");
            return;
        }

        System.out.println("id: " + farmacia.getId());
        System.out.println("nombre: " + farmacia.getNombre());
        System.out.println("direccion: " + farmacia.getDireccion());
        System.out.println("longitud: " + farmacia.getLongitud());
        System.out.println("lat: " + farmacia.getLat());
        System.out.println("cod_ciudad: " + farmacia.getCod_ciudad());
        System.out.println("logo: " + farmacia.getLogo());
        esperarTecla();
    }

    public void deletefarmacia() {
        System.out.print("Ingrese el id del farmacia que desea eliminar: ");
        String id = scanner.nextLine();

        try {
            farmaciaService.deleteFarmacia(id);
            System.out.println("Farmacia eliminada exitosamente!");
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