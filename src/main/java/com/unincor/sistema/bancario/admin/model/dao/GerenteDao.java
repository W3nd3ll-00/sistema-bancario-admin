/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.dao;

import com.unincor.sistema.bancario.admin.configurations.MySQL;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendell
 */
public class GerenteDao {

    private Agencia Agencia;

    public void inserirGerente(Gerente Gerente) {
        String sql = "INSERT INTO gerentes(nome, cpf, data_nascimento, "
                + "email, telefone, senha_hash, id_agencia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, Gerente.getNome());
            ps.setString(2, Gerente.getCpf());
            ps.setDate(3, Date.valueOf(Gerente.getDataNascimento()));
            ps.setString(4, Gerente.getEmail());
            ps.setString(5, Gerente.getTelefone());
            ps.setString(6, Gerente.getSenhaHash());
            ps.setLong(7, Gerente.getAgencia().getIdAgencia());
            ps.execute();
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Gerente> buscarTodosGerentes() {
        List<Gerente> gerentes = new ArrayList<>();
        String sql = "SELECT * FROM gerentes";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Gerente gerente = new Gerente();
                gerente.setIdGerente(rs.getLong("id_gerente"));
                gerente.setNome(rs.getString("nome"));
                //novos:
                gerente.setCpf(rs.getString("cpf"));
                gerente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
                gerente.setEmail(rs.getString("email"));
                gerente.setTelefone(rs.getString("telefone"));
                gerente.setSenhaHash(rs.getString("senha_hash"));

                // teste:
                Agencia agencia = new Agencia();
                agencia.setIdAgencia(rs.getLong("id_agencia"));
                gerente.setAgencia(agencia);

                gerentes.add(gerente);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gerentes;
    }

    public Gerente buscarGerentePorId(Long idGerente) {
        String sql = "SELECT * FROM gerentes WHERE id_gerente = ?";
        try (Connection con = MySQL.connect(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, idGerente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return construirGerenteSql(rs);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GerenteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public Gerente construirGerenteSql(ResultSet rs) throws SQLException {
        Gerente gerente = new Gerente();
        gerente.setIdGerente(rs.getLong("id_gerente"));
        gerente.setNome(rs.getString("nome"));
        //novos:
        gerente.setCpf(rs.getString("cpf"));
        gerente.setDataNascimento(rs.getDate("data_nascimento").toLocalDate());
        gerente.setEmail(rs.getString("email"));
        gerente.setTelefone(rs.getString("telefone"));
        gerente.setSenhaHash(rs.getString("senha_hash"));

        //teste:
        Agencia agencia = new Agencia();
        agencia.setIdAgencia(rs.getLong("id_agencia"));
        gerente.setAgencia(agencia);

        return gerente;
    }

    public static void main(String[] args) {
        GerenteDao gerenteDao = new GerenteDao();
//        var clientes = clienteDao.buscarTodosClientes();
//        clientes.forEach(c -> System.out.println("ID: " + c.getIdCliente() + " Nome: " + c.getNome() + " Cpf: " + c.getCpf()
//                + " Data nascimento: " + c.getDataNascimento() + " Email: " + c.getEmail() + " telefone: " + c.getTelefone()
//                + " senha_hash: " + c.getSenhaHash()
//        ));

        var g = gerenteDao.buscarGerentePorId(1l);
        System.out.println("Id: " + g.getIdGerente()
                + " Nome: " + g.getNome());
    }
}