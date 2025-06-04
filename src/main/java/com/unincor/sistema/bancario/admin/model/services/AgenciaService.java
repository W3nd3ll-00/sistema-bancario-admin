package com.unincor.sistema.bancario.admin.model.services;

import com.unincor.sistema.bancario.admin.exceptions.CadastroException;
import com.unincor.sistema.bancario.admin.model.dao.AgenciaDao;
import com.unincor.sistema.bancario.admin.model.domain.Agencia;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioge
 */
public class AgenciaService {

    private final AgenciaDao agenciaDao = new AgenciaDao();

    public void salvarAgencia(Agencia agencia) throws CadastroException {
        if (agencia.getCodigoAgencia() == null
                || agencia.getCodigoAgencia().isBlank()) {
            throw new CadastroException("A agência não possui "
                    + "um código de agência!");
        }

        // Criar uma validação se o código da agência já está cadastrado
        Agencia agenciaBusca = agenciaDao
                .buscarAgenciaPorCodigoAgencia(agencia.getCodigoAgencia());
        if (agenciaBusca != null) {
            throw new CadastroException("Código da Agência já está "
                    + "cadastrado!");
        }
        
        // Validar se a agência está com Cidade e UF preenchidos.
        if (agencia.getCidade() == null
                || agencia.getCidade().isBlank()) {
            throw new CadastroException("A agência não possui "
                    + "uma cidade informada!");
        }
        if (agencia.getUf()== null
                || agencia.getUf().isBlank()) {
            throw new CadastroException("A agência não possui "
                    + "uma UF informada!");
        }

        agenciaDao.inserirAgencia(agencia);
    }
    
    public List<Agencia> buscarAgencias(){
        return agenciaDao.listarTodasAgencias();
    }
    

    public static void main(String[] args) {
        AgenciaService agenciaService = new AgenciaService();

        Agencia agencia = new Agencia(null, null, "Três Corações", "MG",
                "Rei Pelé", "468798", "37410000");
        
        try {
            agenciaService.salvarAgencia(agencia);
        } catch (CadastroException ex) {
            Logger.getLogger(AgenciaService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
