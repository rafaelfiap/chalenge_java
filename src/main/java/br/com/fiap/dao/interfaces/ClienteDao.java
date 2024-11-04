package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.models.Cliente;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Cliente.
 *
 * @version 1.0
 * @since 1.0
 */
public interface ClienteDao {

    /**
     * Busca todas as instâncias de Cliente no banco de dados.
     *
     * @return Lista de instâncias de {@link Cliente}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Cliente> findAll();

    /**
     * Remove um Cliente pelo ID no banco de dados.
     *
     * @param id O ID do Cliente a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws ClienteNotFoundException Se o Cliente com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws ClienteNotFoundException, SQLException;

    /**
     * Salva um novo Cliente no banco de dados.
     *
     * @param cliente A instância de Cliente a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Cliente} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws ClienteNotSavedException Se o Cliente não puder ser salvo.
     */
    Cliente save(Cliente cliente, Connection connection) throws SQLException, ClienteNotSavedException;

    /**
     * Atualiza um Cliente existente no banco de dados.
     *
     * @param cliente A instância de Cliente com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Cliente} atualizada.
     * @throws ClienteNotFoundException Se o Cliente com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Cliente update(Cliente cliente, Connection connection) throws ClienteNotFoundException, SQLException;
}
