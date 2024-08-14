package crud.unidad_medida.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.infrastructure.config.DatabaseConfig;
import crud.unidad_medida.domain.service.MedidaService;
import crud.unidad_medida.domain.entity.Medida;


public class MedidaRepository implements MedidaService {

    @Override
    public void createMedida(Medida medida) {
        String sql = "INSERT INTO unidad_medida (id, nombre) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, medida.getId());
            statement.setString(2, medida.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Medida findMedidaById(int id) {
        String sql = "SELECT id, nombre FROM unidad_medida WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                return new Medida(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el Medida
    }

    @Override
    public void updateMedida(Medida medida) {
        String sql = "UPDATE unidad_medida SET nombre = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medida.getNombre());
            statement.setInt(2, medida.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMedida(int id) {
        String sql = "DELETE FROM unidad_medida WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

