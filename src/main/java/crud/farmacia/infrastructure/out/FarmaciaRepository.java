package crud.farmacia.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.farmacia.domain.entity.Farmacia;
import crud.farmacia.domain.service.FarmaciaService;
import crud.infrastructure.config.DatabaseConfig;

public class FarmaciaRepository implements FarmaciaService {
    @Override
    public Farmacia findFarmaciaById(String id) {
        String sql = "SELECT id, nombre, direccion, longitud, lat, cod_ciudad, logo FROM farmacia WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String direccion = resultSet.getString("direccion");
                Float longitud = resultSet.getFloat("longitud");
                Float lat = resultSet.getFloat("lat");
                String cod_ciudad = resultSet.getString("cod_ciudad");
                String logo = resultSet.getString("logo");
                return new Farmacia(cod_ciudad, nombre, direccion, longitud, lat, cod_ciudad, logo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createFarmacia(Farmacia farmacia) {
        String sql = "INSERT INTO farmacia (id, nombre, direccion, longitud, lat, cod_ciudad, logo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, farmacia.getId());
            statement.setString(2, farmacia.getNombre());
            statement.setString(3, farmacia.getDireccion());
            statement.setFloat(4, farmacia.getLongitud());
            statement.setFloat(5, farmacia.getLat());
            statement.setString(6, farmacia.getCod_ciudad());
            statement.setString(7, farmacia.getLogo());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFarmacia(Farmacia farmacia) {
        String sql = "UPDATE farmacia SET nombre = ?, direccion = ?, longitud = ?, lat = ?, cod_ciudad = ?, logo = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, farmacia.getNombre());
            statement.setString(2, farmacia.getDireccion());
            statement.setFloat(3, farmacia.getLongitud());
            statement.setFloat(4, farmacia.getLat());
            statement.setString(5, farmacia.getCod_ciudad());
            statement.setString(6, farmacia.getLogo());
            statement.setString(7, farmacia.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFarmacia(String id) {
        String sql = "DELETE FROM farmacia WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}