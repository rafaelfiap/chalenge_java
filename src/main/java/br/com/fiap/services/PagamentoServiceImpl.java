package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.PagamentoDao;
import br.com.fiap.dao.PagamentoDaoFactory;
import br.com.fiap.exceptions.PagamentoNotFoundException;
import br.com.fiap.exceptions.PagamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Pagamento;
import br.com.fiap.services.interfaces.PagamentoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Pagamento, utilizando PagamentoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para pagamentos no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class PagamentoServiceImpl implements PagamentoService {

    private static final Logger logger = Logger.getLogger(PagamentoServiceImpl.class.getName());
    private final PagamentoDao dao = PagamentoDaoFactory.create();

    /**
     * Cria um novo Pagamento no banco de dados.
     *
     * @param pagamento O objeto Pagamento a ser criado.
     * @return O Pagamento criado com o ID gerado.
     * @throws PagamentoNotSavedException Se o Pagamento não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Pagamento create(Pagamento pagamento) throws UnsupportedServiceOperationException, SQLException, PagamentoNotSavedException {
        if (pagamento.getIdPagamento() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                pagamento = this.dao.save(pagamento, connection);
                connection.commit();
                return pagamento;
            } catch (SQLException | PagamentoNotSavedException e) {
                connection.rollback();
                logger.severe("Erro ao criar pagamento: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("O pagamento já possui um ID.");
        }
    }

    /**
     * Retorna uma lista de todos os pagamentos.
     *
     * @return Lista de todos os pagamentos.
     */
    @Override
    public List<Pagamento> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Pagamento existente no banco de dados.
     *
     * @param pagamento O objeto Pagamento com os dados atualizados.
     * @return O Pagamento atualizado.
     * @throws PagamentoNotFoundException Se o Pagamento não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Pagamento update(Pagamento pagamento) throws PagamentoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            pagamento = this.dao.update(pagamento, connection);
            connection.commit();
            return pagamento;
        } catch (SQLException e) {
            connection.rollback();
            logger.severe("Erro ao atualizar pagamento: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui um Pagamento do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Pagamento a ser excluído.
     * @throws PagamentoNotFoundException Se o Pagamento não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws PagamentoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.deleteById(id, connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            logger.severe("Erro ao deletar pagamento: " + e.getMessage());
            throw e;
        }
    }
}
