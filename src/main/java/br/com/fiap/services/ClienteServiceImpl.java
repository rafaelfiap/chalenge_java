package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.ClienteDao;
import br.com.fiap.dao.ClienteDaoFactory;
import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Cliente;
import br.com.fiap.services.interfaces.ClienteService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Cliente, utilizando ClienteDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para clientes no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class ClienteServiceImpl implements ClienteService {

    private final ClienteDao dao = ClienteDaoFactory.create();

    /**
     * Cria um novo Cliente no banco de dados.
     *
     * @param cliente O objeto Cliente a ser criado.
     * @return O Cliente criado com o ID gerado.
     * @throws ClienteNotSavedException Se o Cliente não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Cliente create(Cliente cliente) throws UnsupportedServiceOperationException, SQLException, ClienteNotSavedException {
        if (cliente.getIdCliente() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                cliente = this.dao.save(cliente, connection);
                connection.commit();
                return cliente;
            } catch (SQLException | ClienteNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se o Cliente já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Cliente já possui um ID e não pode ser criado novamente.");
        }
    }

    /**
     * Retorna uma lista de todos os clientes.
     *
     * @return Lista de todos os clientes.
     */
    @Override
    public List<Cliente> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Cliente existente no banco de dados.
     *
     * @param cliente O objeto Cliente com os dados atualizados.
     * @return O Cliente atualizado.
     * @throws ClienteNotFoundException Se o Cliente não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Cliente update(Cliente cliente) throws ClienteNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            cliente = this.dao.update(cliente, connection);
            connection.commit();
            return cliente;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Cliente do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Cliente a ser excluído.
     * @throws ClienteNotFoundException Se o Cliente não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws ClienteNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.deleteById(id, connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
