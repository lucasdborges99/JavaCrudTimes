package com.template.model.dao;

import com.template.model.ConexaoBD;
import com.template.model.dto.TimesDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimesDAO {

    private static final Logger logger = Logger.getLogger(TimesDAO.class.getName());

    public void cadastrarTime(TimesDTO time) {
        String sql = "INSERT INTO times (nome, ano_fund, estado, brasileiros) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConexaoBD().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, time.getNome());
            pstm.setInt(2, time.getAnoFundacao());
            pstm.setString(3, time.getEstado());
            pstm.setInt(4, time.getTitulosBrasileiros());

            pstm.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao cadastrar time", e);
            throw new RuntimeException("Erro ao cadastrar time");
        }
    }

    public ArrayList<TimesDTO> listarTimes() {
        String sql = "SELECT * FROM times ORDER BY id ASC";
        ArrayList<TimesDTO> lista = new ArrayList<>();

        try (Connection conn = new ConexaoBD().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                TimesDTO time = new TimesDTO();
                time.setId(rs.getInt("id"));
                time.setNome(rs.getString("nome"));
                time.setAnoFundacao(rs.getInt("ano_fund"));
                time.setEstado(rs.getString("estado"));
                time.setTitulosBrasileiros(rs.getInt("brasileiros"));
                lista.add(time);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao listar times", e);
            throw new RuntimeException("Erro ao listar times");
        }

        return lista;
    }

    public void alterarTime(TimesDTO time) {
        String sql = "UPDATE times SET nome = ?, ano_fund = ?, estado = ?, brasileiros = ? WHERE id = ?";

        try (Connection conn = new ConexaoBD().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, time.getNome());
            pstm.setInt(2, time.getAnoFundacao());
            pstm.setString(3, time.getEstado());
            pstm.setInt(4, time.getTitulosBrasileiros());
            pstm.setInt(5, time.getId());

            pstm.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao alterar time", e);
            throw new RuntimeException("Erro ao alterar time");
        }
    }

    public void excluirTime(int id) {
        String sql = "DELETE FROM times WHERE id = ?";

        try (Connection conn = new ConexaoBD().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id);
            pstm.execute();

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao excluir time", e);
            throw new RuntimeException("Erro ao excluir time");
        }
    }
}