package crud.paises.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.infrastructure.config.DatabaseConfig;
import crud.paises.domain.entity.Pais;
import crud.paises.domain.service.PaisService;

public class PaisRepository implements PaisService {
    
    @Override
    public void createPais(Pais pais) {
        String sql = "INSERT INTO paises (codigo, nombre) VALUES (?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pais.getCodigo());
            statement.setString(2, pais.getNombre());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Pais findPaisById(String codigo) {
        String sql = "SELECT codigo, nombre FROM paises WHERE codigo = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                return new Pais(codigo, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Retorna null si no se encuentra el país
    }

    @Override
    public void updatePais(Pais pais) {
        String sql = "UPDATE paises SET nombre = ? WHERE codigo = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pais.getNombre());
            statement.setString(2, pais.getCodigo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePais(String codigo) {
        String sql = "DELETE FROM paises WHERE codigo = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
