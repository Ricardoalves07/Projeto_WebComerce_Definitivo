package src.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    // Create (Inserir novo usuário)
    public void create(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuario (email, nome, endereco, login, senha) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEndereco());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.executeUpdate();
        }
    }

    // Read (Consultar usuário por ID)
    public Usuario read(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("login"),
                        rs.getString("senha")
                );
            }
        }
        return null;
    }

    // List (Consultar todos os usuários)
    public List<Usuario> listAll() throws SQLException {
        String sql = "SELECT * FROM Usuario";
        List<Usuario> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("login"),
                        rs.getString("senha")
                ));
            }
        }
        return usuarios;
    }

    // Update (Atualizar informações do usuário)
    public void update(Usuario usuario) throws SQLException {
        String sql = "UPDATE Usuario SET email = ?, nome = ?, endereco = ?, login = ?, senha = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getNome());
            stmt.setString(3, usuario.getEndereco());
            stmt.setString(4, usuario.getLogin());
            stmt.setString(5, usuario.getSenha());
            stmt.setInt(6, usuario.getId());
            stmt.executeUpdate();
        }
    }

    // Delete (Remover usuário)
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
