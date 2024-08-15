package crud;

import java.util.InputMismatchException;
import java.util.Scanner;

import crud.ciudad.apllication.CreateCiudadUseCase;
import crud.ciudad.infrastructure.in.CiudadController;
import crud.ciudad.infrastructure.out.CiudadRepository;

import crud.clientes.application.CreateClienteUseCase;
import crud.clientes.infrastructure.in.ClienteController;
import crud.clientes.infrastructure.out.ClienteRepository;

import crud.modelo_admin.application.CreateAdminUseCase;
import crud.modelo_admin.infrastructure.in.AdminController;
import crud.modelo_admin.infrastructure.out.AdminRepository;

import crud.paises.application.CreatePaisUseCase;
import crud.paises.infrastructure.in.PaisController;
import crud.paises.infrastructure.out.PaisRepository;

import crud.principio_activo.application.CreateActivoUseCase;
import crud.principio_activo.infrastructure.in.ActivoController;
import crud.principio_activo.infrastructure.out.ActivoRepository;

import crud.unidad_medida.application.CreateMedidaUseCase;
import crud.unidad_medida.infrastructure.in.MedidaController;
import crud.unidad_medida.infrastructure.out.MedidaRepository;

import crud.region.application.CreateRegionUseCase;
import crud.region.infrastructure.in.RegionController;
import crud.region.infrastructure.out.RegionRepository;

import crud.farmacia.infrastructure.in.FarmaciaController;
import crud.farmacia.infrastructure.out.FarmaciaRepository;
import crud.farmacia.application.CreateFarmaciaUseCase;

public class MenuPrincipal {
    private Scanner scanner = new Scanner(System.in);
    private PaisController paisController;
    private ActivoController activoController;
    private AdminController adminController;
    private MedidaController medidaController;
    private RegionController regionController;
    private CiudadController ciudadController;
    private ClienteController clienteController;
    private FarmaciaController farmaciaController;

    public MenuPrincipal() {
        
        PaisRepository paisRepository = new PaisRepository();
        CreatePaisUseCase createPaisUseCase = new CreatePaisUseCase(paisRepository);
        paisController = new PaisController(createPaisUseCase, paisRepository);

        ActivoRepository activoRepository = new ActivoRepository();
        CreateActivoUseCase createActivoUseCase = new CreateActivoUseCase(activoRepository);
        activoController = new ActivoController(createActivoUseCase, activoRepository);

        AdminRepository adminRepository = new AdminRepository();
        CreateAdminUseCase createAdminUseCase = new CreateAdminUseCase(adminRepository);
        adminController = new AdminController(createAdminUseCase, adminRepository);

        MedidaRepository medidaRepository = new MedidaRepository();
        CreateMedidaUseCase createMedidaUseCase = new CreateMedidaUseCase(medidaRepository);
        medidaController = new MedidaController(createMedidaUseCase, medidaRepository);

        RegionRepository regionRepository = new RegionRepository();
        CreateRegionUseCase createRegionUseCase = new CreateRegionUseCase(regionRepository);
        regionController = new RegionController(createRegionUseCase, regionRepository);

        CiudadRepository ciudadRepository = new CiudadRepository();
        CreateCiudadUseCase createCiudadUseCase = new CreateCiudadUseCase(ciudadRepository);
        ciudadController = new CiudadController(createCiudadUseCase, ciudadRepository, scanner);

        ClienteRepository clienteRepository = new ClienteRepository();
        CreateClienteUseCase createClienteUseCase = new CreateClienteUseCase(clienteRepository);
        clienteController = new ClienteController(createClienteUseCase, clienteRepository, scanner);

        FarmaciaRepository farmaciaRepository = new FarmaciaRepository();
        CreateFarmaciaUseCase createFarmaciaUseCase = new CreateFarmaciaUseCase(farmaciaRepository);
        farmaciaController = new FarmaciaController(createFarmaciaUseCase, farmaciaRepository, scanner);
    }

    public void start() {
        int opcion = 0;

        do {
            limpiarConsola();
            System.out.println("1. tabla de paises");
            System.out.println("2. tabla de principio_activo");
            System.out.println("3. tabla de modelo_administracion");
            System.out.println("4. tabla de unidad_medida");
            System.out.println("5. tabla de region");
            System.out.println("6. tabla de ciudad");
            System.out.println("7. tabla de clientes");
            System.out.println("8. tabla de farmacia");
            System.out.println("9. tabla de laboratorio");
            System.out.println("10. tabla de medicina");
            System.out.println("11. tabla de farmaciamedicina");
            System.out.println("12. salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer
            } catch (InputMismatchException e) {
                scanner.next(); // Limpiar la entrada incorrecta
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                esperarTecla();
                continue;
            }

            switch (opcion) {
                case 1:
                    paisController.tabla_paises();
                    break;
                case 2:
                    activoController.tabla_principio_activo();
                    break;
                case 3:
                    adminController.tabla_admin();
                    break;
                case 4:
                    medidaController.tabla_unidad_medida();
                    break;
                case 5:
                    regionController.tabla_region();
                    break;
                case 6:
                    ciudadController.tabla_ciudad();
                    break;
                case 7:
                    clienteController.tabla_cliente();
                    break;
                case 8:
                    farmaciaController.tabla_farmacia();
                    break;
                case 9:
                    // tabla de laboratorio
                    break;
                case 10:
                    // tabla de medicina
                    break;
                case 11:
                    // tabla de farmaciamedicina
                    break;
                case 12:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida.");
                    esperarTecla();
            }
        } while (opcion != 12);
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
