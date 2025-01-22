package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {
    private Connection connection;

    public CompraDAO(Connection connection) {
        this.connection = connection;
    }

    public void create(Compra compra) throws SQLException {
        String sql = "INSERT INTO Compra (data_hora, usuario_id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setTimestamp(1, compra.getDataHora());
            stmt.setInt(2, compra.getUsuarioId());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                compra.setId(generatedKeys.getInt(1));
            }
        }
    }

    public Compra read(int id) throws SQLException {
        String sql = "SELECT * FROM Compra WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Compra(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora"),
                        rs.getInt("usuario_id")
                );
            }
        }
        return null;
    }

    public List<Compra> listAll() throws SQLException {
        String sql = "SELECT * FROM Compra";
        List<Compra> compras = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                compras.add(new Compra(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora"),
                        rs.getInt("usuario_id")
                ));
            }
        }
        return compras;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Compra WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
