package crud.modelo_admin.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.modelo_admin.domain.entity.Admin;
import crud.modelo_admin.domain.service.AdminService;
import crud.infrastructure.config.DatabaseConfig;

public class AdminRepository implements AdminService {

    @Override
    public void createAdmin(Admin admin) {
        String sql = "INSERT INTO modelo_administracion (id, descripcion) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, admin.getId());
            statement.setString(2, admin.getDescripcion());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin findAdminById(int id) {
        String sql = "SELECT id, descripcion FROM modelo_administracion WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String descripcion = resultSet.getString("descripcion");
                return new Admin(id, descripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public void updateAdmin(Admin admin) {
        String sql = "UPDATE modelo_administracion SET descripcion = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, admin.getDescripcion());
            statement.setInt(2, admin.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAdmin(int id) {
        String sql = "DELETE FROM modelo_administracion WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
