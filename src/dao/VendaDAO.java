import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

class VendaDAO {
    public void createVenda(Venda venda) throws SQLException {
        String sql = "INSERT INTO Venda (data_hora, usuario_id) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setTimestamp(1, Timestamp.valueOf(venda.getDataHora()));
            stmt.setInt(2, venda.getUsuarioId());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                venda.setId(rs.getInt(1));
            }
        }
    }

    public Venda getVendaById(int id) throws SQLException {
        String sql = "SELECT * FROM Venda WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Venda(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getInt("usuario_id")
                );
            }
        }
        return null;
    }

    public List<Venda> getAllVendas() throws SQLException {
        String sql = "SELECT * FROM Venda";
        List<Venda> vendas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                vendas.add(new Venda(
                        rs.getInt("id"),
                        rs.getTimestamp("data_hora").toLocalDateTime(),
                        rs.getInt("usuario_id")
                ));
            }
        }
        return vendas;
    }

    public void updateVenda(Venda venda) throws SQLException {
        String sql = "UPDATE Venda SET data_hora = ?, usuario_id = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setTimestamp(1, Timestamp.valueOf(venda.getDataHora()));
            stmt.setInt(2, venda.getUsuarioId());
            stmt.setInt(3, venda.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteVenda(int id) throws SQLException {
        String sql = "DELETE FROM Venda WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
