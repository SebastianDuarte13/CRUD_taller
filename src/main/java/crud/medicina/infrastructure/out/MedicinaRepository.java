package crud.medicina.infrastructure.out;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.infrastructure.config.DatabaseConfig;
import crud.medicina.domain.entity.Medicina;
import crud.medicina.domain.service.MedicinaService;

public class MedicinaRepository implements MedicinaService {
    @Override
    public Medicina findMedicinaById(String id) {
        String sql = "SELECT id, proceedings, nombre, healthregister, descripcion, descripcionshort, nombrerol, cod_admin, cod_activo, cod_medida, cod_lab  FROM medicina WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String proceedings = resultSet.getString("proceedings");
                String nombre = resultSet.getString("nombre");
                String healthregister = resultSet.getString("healthregister");
                String descripcion = resultSet.getString("descripcion");
                String descripcionshort = resultSet.getString("descripcionshort");
                String nombrerol = resultSet.getString("nombrerol");
                String cod_admin = resultSet.getString("cod_admin");
                String cod_activo = resultSet.getString("cod_activo");
                String cod_medida = resultSet.getString("cod_medida");
                String cod_lab = resultSet.getString("cod_lab");

                return new Medicina(id, proceedings, nombre, healthregister, descripcion, descripcionshort, nombrerol,
                        cod_admin, cod_activo, cod_medida, cod_lab);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createMedicina(Medicina medicina) {
        String sql = "INSERT INTO medicina (id, proceedings, nombre, healthregister, descripcion, descripcionshort, nombrerol, cod_admin, cod_activo, cod_medida, cod_lab) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medicina.getId());
            statement.setString(2, medicina.getProceedings());
            statement.setString(3, medicina.getNombre());
            statement.setString(4, medicina.getHealthregister());
            statement.setString(5, medicina.getDescripcion());
            statement.setString(6, medicina.getDescripcionshort());
            statement.setString(7, medicina.getNombrerol());
            statement.setString(8, medicina.getCod_admin());
            statement.setString(9, medicina.getCod_activo());
            statement.setString(10, medicina.getCod_medida());
            statement.setString(11, medicina.getCod_lab());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateMedicina(Medicina medicina) {
        String sql = "UPDATE medicina SET proceedings = ?, nombre = ?, healthregister = ?, descripcion = ?, descripcionshort = ?, nombrerol = ?, cod_admin = ?, cod_activo = ?, cod_medida = ?, cod_lab = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, medicina.getProceedings());
            statement.setString(2, medicina.getNombre());
            statement.setString(3, medicina.getHealthregister());
            statement.setString(4, medicina.getDescripcion());
            statement.setString(5, medicina.getDescripcionshort());
            statement.setString(6, medicina.getNombrerol());
            statement.setString(7, medicina.getCod_admin());
            statement.setString(8, medicina.getCod_activo());
            statement.setString(9, medicina.getCod_medida());
            statement.setString(10, medicina.getCod_lab());
            statement.setString(11, medicina.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteMedicina(String id) {
        String sql = "DELETE FROM medicina WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
