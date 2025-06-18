/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.AgenciaDao;
import com.unincor.sistema.bancario.admin.model.dao.GerenteDao;
import com.unincor.sistema.bancario.admin.model.domain.Gerente;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendell
 */
public class GerenteService {

    private final GerenteDao gerenteDao = new GerenteDao();

    public void salvarGerente(Gerente gerente) throws CadastroException {
        
        if (gerente == null) {
            throw new CadastroException("Gerente inválido!");
        }
        
        if (gerente.getNome() == null || gerente.getNome().isBlank()) {
            throw new CadastroException("Gerente não possui nome!");
        }

        // validar se a gerente esta com cidade e uf preenchido:
//        Gerente gerenteCidade = gerenteDao.buscarGerentePorCodigoGerente(gerente.getCidade());
//        Gerente gerenteUf = gerenteDao.buscarGerentePorCodigoGerente(gerente.getUf());
        if (gerente.getCpf() == null || gerente.getCpf().isBlank()) {
            throw new CadastroException("Gerente não possui CPF!");
        }
        
        if (gerente.getEmail() == null || gerente.getEmail().isBlank()) {
            throw new CadastroException("Gerente não informou e-mail para contato!");
        }

        gerenteDao.inserirGerente(gerente);
    }

    public List<Gerente> buscarGerente() {
        return gerenteDao.buscarTodosGerentes();
    }

    public static void main(String[] args) {
        GerenteService gerenteService = new GerenteService();

        var AgenciaDao = new AgenciaDao().buscarAgenciaPorId(3l);
        
        Gerente gerente = new Gerente(null, "Junior Neto", "120000987", LocalDate.now(),
                "junior.neto@gmail.com", "3598887525", "000977752", AgenciaDao);

        try {
            gerenteService.salvarGerente(gerente);
        } catch (CadastroException ex) {
            Logger.getLogger(GerenteService.class.getName()).log(Level.SEVERE, null, ex);
        
    }
}
}
