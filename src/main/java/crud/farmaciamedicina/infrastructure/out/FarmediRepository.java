package crud.farmaciamedicina.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.farmaciamedicina.domain.entity.Farmedi;
import crud.farmaciamedicina.domain.service.FarmediService;
import crud.infrastructure.config.DatabaseConfig;

public class FarmediRepository implements FarmediService {

    @Override
    public Farmedi findFarmediById(String id_farmacia) {
        String sql = "SELECT id_farmacia, id_medicina, prince FROM farmaciamedicina WHERE id_farmacia = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id_farmacia);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String id_medicina = resultSet.getString("id_medicina");
                Float prince = resultSet.getFloat("prince");
                return new Farmedi(id_farmacia, id_medicina, prince);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createFarmedi(Farmedi farmedi) {
        String sql = "INSERT INTO farmaciamedicina (id_farmacia, id_medicina, prince) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, farmedi.getId_farmacia());
            statement.setString(2, farmedi.getId_medicina());
            statement.setFloat(3, farmedi.getPrince());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateFarmedi(Farmedi farmedi) {
        String sql = "UPDATE farmaciamedicina SET id_medicina = ?, prince = ? WHERE id_farmacia = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, farmedi.getId_medicina());
            statement.setFloat(2, farmedi.getPrince());
            statement.setString(3, farmedi.getId_farmacia());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFarmedi(String id_farmacia) {
        String sql = "DELETE FROM farmaciamedicina WHERE id_farmacia = ?";
        try (Connection connection = DatabaseConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id_farmacia);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
