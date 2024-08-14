package crud.principio_activo.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.infrastructure.config.DatabaseConfig;
import crud.principio_activo.domain.entity.Activo;
import crud.principio_activo.domain.service.ActivoService;

public class ActivoRepository implements ActivoService {

    @Override
    public void createActivo(Activo activo) {
        String sql = "INSERT INTO principio_activo (id, nombre) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, activo.getId());
            statement.setString(2, activo.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Activo findActivoById(int id) {
        String sql = "SELECT id, nombre FROM principio_activo WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                return new Activo(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el activo
    }

    @Override
    public void updateActivo(Activo activo) {
        String sql = "UPDATE principio_activo SET nombre = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, activo.getNombre());
            statement.setInt(2, activo.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteActivo(int id) {
        String sql = "DELETE FROM principio_activo WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
