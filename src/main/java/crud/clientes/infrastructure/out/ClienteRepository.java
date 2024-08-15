package crud.clientes.infrastructure.out;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crud.clientes.domain.entity.Cliente;
import crud.clientes.domain.service.ClienteService;
import crud.infrastructure.config.DatabaseConfig;

public class ClienteRepository implements ClienteService {

    @Override
    public Cliente findClienteById(String id) {
        String sql = "SELECT id, nombres, apellidos, email, fecha_nacimiento, lon, latitud, cod_ciudad FROM clientes WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String nombres = resultSet.getString("nombres");
                String apellidos = resultSet.getString("apellidos");
                String email = resultSet.getString("email");
                Date fecha_nacimiento = resultSet.getDate("fecha_nacimiento");
                Float lon = resultSet.getFloat("lon");
                Float latitud = resultSet.getFloat("latitud");
                String cod_ciudad = resultSet.getString("cod_ciudad");
                return new Cliente(cod_ciudad, nombres, apellidos, email, fecha_nacimiento, lon, latitud, cod_ciudad);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (id, nombres, apellidos, email, fecha_nacimiento, lon, latitud, cod_ciudad) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getId());
            statement.setString(2, cliente.getNombres());
            statement.setString(3, cliente.getApellidos());
            statement.setString(4, cliente.getEmail());
            statement.setDate(5, cliente.getFecha_nacimiento());
            statement.setFloat(6, cliente.getLon());
            statement.setFloat(7, cliente.getLatitud());
            statement.setString(8, cliente.getCod_ciudad());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombres = ?, apellidos = ?, email = ?, fecha_nacimiento = ?, lon = ?, latitud = ?, cod_ciudad = ? WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, cliente.getNombres());
            statement.setString(2, cliente.getApellidos());
            statement.setString(3, cliente.getEmail());
            statement.setDate(4, cliente.getFecha_nacimiento());
            statement.setFloat(5, cliente.getLon());
            statement.setFloat(6, cliente.getLatitud());
            statement.setString(7, cliente.getCod_ciudad());
            statement.setString(8, cliente.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCliente(String id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection connection = DatabaseConfig.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
