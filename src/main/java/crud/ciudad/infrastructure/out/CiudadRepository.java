package crud.ciudad.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.ciudad.domain.entity.Ciudad;
import crud.ciudad.domain.service.CiudadService;
import crud.infrastructure.config.DatabaseConfig;



public class CiudadRepository implements CiudadService{
    @Override
    public void createciudad(Ciudad ciudad) {
        String sql = "INSERT INTO ciudad (codigo_ciudad, nombre, cod_region) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, ciudad.getCodigo_ciudad());
            statement.setString(2, ciudad.getNombre());
            statement.setString(3, ciudad.getCod_region());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Ciudad findCiudadById(String codigo_ciudad) {
        String sql = "SELECT codigo_ciudad, nombre, cod_region FROM ciudad WHERE codigo_ciudad = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo_ciudad);
            ResultSet resultSet = statement.executeQuery();
            
           if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String cod_region = resultSet.getString("cod_region");
                return new Ciudad(codigo_ciudad, nombre, cod_region);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }

    @Override
    public void updateciudad(Ciudad ciudad) {
        String sql = "UPDATE ciudad SET nombre = ?, cod_region = ? WHERE codigo_ciudad = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(2, ciudad.getNombre());
            statement.setString(3, ciudad.getCod_region());
            statement.setString(1, ciudad.getCodigo_ciudad());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteciudad(String codigo_ciudad) {
        String sql = "DELETE FROM ciudad WHERE codigo_ciudad = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo_ciudad);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

 

}
