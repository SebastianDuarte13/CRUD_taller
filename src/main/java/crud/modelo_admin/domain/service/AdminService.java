package crud.modelo_admin.domain.service;

import crud.modelo_admin.domain.entity.Admin;

public interface AdminService {
    Admin findAdminById(int id);

    void createAdmin(Admin admin);

    void updateAdmin(Admin admin);

    void deleteAdmin(int id);
}
