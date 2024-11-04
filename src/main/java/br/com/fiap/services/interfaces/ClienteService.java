package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Cliente;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Cliente.
 * Define métodos para criar, atualizar, excluir e buscar clientes.
 *
 * @since 1.0
 */
public interface ClienteService {

    /**
     * Cria um novo cliente no sistema.
     *
     * @param cliente A instância de Cliente a ser criada.
     * @return O Cliente criado com o ID gerado.
     * @throws ClienteNotSavedException Se o cliente não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Cliente create(Cliente cliente) throws UnsupportedServiceOperationException, SQLException, ClienteNotSavedException;

    /**
     * Retorna uma lista de todos os clientes.
     *
     * @return Lista de todos os clientes.
     */
    List<Cliente> findAll();

    /**
     * Atualiza os dados de um cliente existente no sistema.
     *
     * @param cliente A instância de Cliente com os dados atualizados.
     * @return O Cliente atualizado.
     * @throws ClienteNotFoundException Se o cliente não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Cliente update(Cliente cliente) throws ClienteNotFoundException, SQLException;

    /**
     * Exclui um cliente do sistema pelo seu ID.
     *
     * @param id O ID do cliente a ser excluído.
     * @throws ClienteNotFoundException Se o cliente não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws ClienteNotFoundException, SQLException;
}
