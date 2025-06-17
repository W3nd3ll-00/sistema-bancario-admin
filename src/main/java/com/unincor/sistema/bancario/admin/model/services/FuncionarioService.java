/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.FuncionarioDao;
import com.unincor.sistema.bancario.admin.model.domain.Funcionario;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendell
 */
public class FuncionarioService {
    private final FuncionarioDao funcionarioDao = new FuncionarioDao();

    public void salvarFuncionario(Funcionario funcionario) throws CadastroException {
        if (funcionario.getNome() == null || funcionario.getNome().isBlank()) {
            throw new CadastroException("Funcionario não possui nome!");
        }
       
        // validar se a funcionario esta com cidade e uf preenchido:
//        Funcionario funcionarioCidade = funcionarioDao.buscarFuncionarioPorCodigoFuncionario(funcionario.getCidade());
//        Funcionario funcionarioUf = funcionarioDao.buscarFuncionarioPorCodigoFuncionario(funcionario.getUf());
        if (funcionario.getCpf() == null || funcionario.getCpf().isBlank()) {
            throw new CadastroException("Funcionario não possui CPF!");
        }
        if (funcionario.getEmail() == null || funcionario.getEmail().isBlank()) {
            throw new CadastroException("Funcionario não informou e-mail para contato!");
        }

        funcionarioDao.inserirFuncionario(funcionario);
    }
    
    public List<Funcionario> buscarFuncionario() {
        return funcionarioDao.buscarTodosFuncionarios();
    }
    
     public static void main(String[] args) {
        FuncionarioService funcionarioService = new FuncionarioService();

        Funcionario funcionario = new Funcionario(null, "Adalberto Costa", "9080999", LocalDate.now(), 
        "adalberto.costa@gmail.com", "359888888888", "980272222", "3");
        
        try {
            funcionarioService.salvarFuncionario(funcionario);
        } catch (CadastroException ex) {
            Logger.getLogger(FuncionarioService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}