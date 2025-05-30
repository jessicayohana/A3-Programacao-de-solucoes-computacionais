package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Treino;
import util.ConexaoDB;

public class TreinoDAO {

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS treino (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "aluno_id INTEGER NOT NULL," +
                     "tipo TEXT NOT NULL," +
                     "descricao TEXT," +
                     "duracao_minutos INTEGER," +
                     "data_inicio TEXT," +
                     "FOREIGN KEY(aluno_id) REFERENCES aluno(id))";
        try (Connection conn = ConexaoDB.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela de treino: " + e.getMessage());
        }
    }

    public void inserir(Treino treino) {
        String sql = "INSERT INTO treino(aluno_id, tipo, descricao, duracao_minutos, data_inicio) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, treino.getAlunoId());
            stmt.setString(2, treino.getTipoTreino());
            stmt.setString(3, treino.getDescricao());
            stmt.setInt(4, treino.getDuracaoMinutos());
            stmt.setString(5, treino.getDataInicio());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir treino: " + e.getMessage());
        }
    }

    public List<Treino> listarPorAlunoId(int alunoId) {
        List<Treino> lista = new ArrayList<>();
        String sql = "SELECT * FROM treino WHERE aluno_id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, alunoId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(new Treino(
                    rs.getInt("id"),
                    rs.getInt("aluno_id"),
                    rs.getString("tipo"),
                    rs.getString("descricao"),
                    rs.getInt("duracao_minutos"),
                    rs.getString("data_inicio")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar treinos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Treino treino) {
        String sql = "UPDATE treino SET tipo = ?, descricao = ?, duracao_minutos = ?, data_inicio = ? WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, treino.getTipoTreino());
            stmt.setString(2, treino.getDescricao());
            stmt.setInt(3, treino.getDuracaoMinutos());
            stmt.setString(4, treino.getDataInicio());
            stmt.setInt(5, treino.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar treino: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM treino WHERE id = ?";
        try (Connection conn = ConexaoDB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao excluir treino: " + e.getMessage());
        }
    }
}
