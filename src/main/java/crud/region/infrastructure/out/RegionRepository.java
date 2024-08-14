package crud.region.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.infrastructure.config.DatabaseConfig;
import crud.region.domain.entity.Region;
import crud.region.domain.service.RegionService;

public class RegionRepository implements RegionService {

    @Override
    public void createRegion(Region region) {
        String sql = "INSERT INTO region (codigo_reg, nombre, codigo_pais) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, region.getCodigo_reg());
            statement.setString(2, region.getNombre());
            statement.setString(3, region.getCodigo_pais());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Region findRegionById(String codigo_reg) {
        String sql = "SELECT codigo_reg, nombre, codigo_pais FROM region WHERE codigo_reg = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo_reg);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String codigo_pais = resultSet.getString("codigo_pais");
                return new Region(codigo_reg, nombre, codigo_pais);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateRegion(Region region) {
        String sql = "UPDATE region SET nombre = ?, codigo_pais = ? WHERE codigo_reg = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, region.getNombre());
            statement.setString(2, region.getCodigo_pais());
            statement.setString(3, region.getCodigo_reg());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteRegion(String codigo_reg) {
        String sql = "DELETE FROM region WHERE codigo_reg = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, codigo_reg);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}