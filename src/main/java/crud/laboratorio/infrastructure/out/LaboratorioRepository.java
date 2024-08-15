package crud.laboratorio.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.infrastructure.config.DatabaseConfig;
import crud.laboratorio.domain.Service.LaboratorioService;
import crud.laboratorio.domain.entity.Laboratorio;

public class LaboratorioRepository implements LaboratorioService {

    @Override
    public void createLaboratorio(Laboratorio laboratorio) {
        String sql = "INSERT INTO laboratorio (id, nombre, cod_ciudad) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, laboratorio.getId());
            statement.setString(2, laboratorio.getNombre());
            statement.setString(3, laboratorio.getCod_ciudad());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Laboratorio findLaboratorioById(String id) {
        String sql = "SELECT id, nombre, cod_ciudad FROM laboratorio WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String cod_ciudad = resultSet.getString("cod_ciudad");
                return new Laboratorio(id, nombre, cod_ciudad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateLaboratorio(Laboratorio laboratorio) {
        String sql = "UPDATE laboratorio SET nombre = ?, cod_ciudad = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, laboratorio.getNombre());
            statement.setString(2, laboratorio.getCod_ciudad());
            statement.setString(3, laboratorio.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLaboratorio(String id) {
        String sql = "DELETE FROM laboratorio WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}