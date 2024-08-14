package crud.modelo_admin.application;

import crud.modelo_admin.domain.entity.Admin;
import crud.modelo_admin.domain.service.AdminService;

public class CreateAdminUseCase {
    private final AdminService adminService;

    public CreateAdminUseCase(AdminService adminService) {
        this.adminService = adminService;
    }

    public void execute(Admin admin) {
        adminService.createAdmin(admin);
    }
}
