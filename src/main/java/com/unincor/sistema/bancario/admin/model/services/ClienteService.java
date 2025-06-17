/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.ClienteDao;
import com.unincor.sistema.bancario.admin.model.domain.Cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wendell
 */
public class ClienteService {
    private final ClienteDao clienteDao = new ClienteDao();

    public void salvarCliente(Cliente cliente) throws CadastroException {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new CadastroException("A cliente não possui um código cliente!");
        }


        // validar se a cliente esta com cidade e uf preenchido:
//        Cliente clienteCidade = clienteDao.buscarClientePorCodigoCliente(cliente.getCidade());
//        Cliente clienteUf = clienteDao.buscarClientePorCodigoCliente(cliente.getUf());
        if (cliente.getCpf() == null || cliente.getCpf().isBlank()) {
            throw new CadastroException("O cliente não possui cpf");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isBlank()) {
            throw new CadastroException("O cliente não possui um email de contato!");
        }

        clienteDao.inserirCliente(cliente);
    }
    
    public List<Cliente> buscarCliente() {
        return clienteDao.buscarTodosClientes();
    }

public static void main(String[] args) {
        ClienteService clienteService = new ClienteService();

        Cliente cliente = new Cliente(null, "Roger Guimarães", "190656", LocalDate.now(),
                "roger.guimaraes@gmail.com","999999999", "0000000");
        
        try {
            clienteService.salvarCliente(cliente);
        } catch (CadastroException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
}
