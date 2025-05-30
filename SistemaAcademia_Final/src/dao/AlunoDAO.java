package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Aluno;
import util.ConexaoDB;

public class AlunoDAO {

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS aluno (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nome TEXT NOT NULL," +
                     "cpf TEXT NOT NULL," +
                     "data_nascimento TEXT NOT NULL," +
                     "telefone TEXT," +
                     "email TEXT)";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela de aluno: " + e.getMessage());
        }
    }

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno(nome, cpf, data_nascimento, telefone, email) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getDataNascimento());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public List<Aluno> listar() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                alunos.add(new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar alunos: " + e.getMessage());
        }
        return alunos;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome = ?, telefone = ?, email = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getTelefone());
            stmt.setString(3, aluno.getEmail());
            stmt.setInt(4, aluno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM aluno WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir aluno: " + e.getMessage());
        }
    }

    public List<Aluno> buscarPorTermo(String termo) {
        List<Aluno> encontrados = new ArrayList<>();
        String sql = "SELECT * FROM aluno WHERE nome LIKE ? OR cpf LIKE ? OR email LIKE ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String filtro = "%" + termo + "%";
            stmt.setString(1, filtro);
            stmt.setString(2, filtro);
            stmt.setString(3, filtro);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                encontrados.add(new Aluno(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento"),
                    rs.getString("telefone"),
                    rs.getString("email")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar aluno: " + e.getMessage());
        }
        return encontrados;
    }
}
