package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.OrcamentoDao;
import br.com.fiap.dao.OrcamentoDaoFactory;
import br.com.fiap.exceptions.OrcamentoNotFoundException;
import br.com.fiap.exceptions.OrcamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Orcamento;
import br.com.fiap.services.interfaces.OrcamentoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Orcamento, utilizando OrcamentoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para orçamentos no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OrcamentoServiceImpl implements OrcamentoService {

    private final OrcamentoDao dao = OrcamentoDaoFactory.create();

    /**
     * Cria um novo Orcamento no banco de dados.
     *
     * @param orcamento O objeto Orcamento a ser criado.
     * @return O Orcamento criado com o ID gerado.
     * @throws OrcamentoNotSavedException Se o Orcamento não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Orcamento create(Orcamento orcamento) throws UnsupportedServiceOperationException, SQLException, OrcamentoNotSavedException {
        if (orcamento.getIdOrcamento() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                orcamento = this.dao.save(orcamento, connection);
                connection.commit();
                return orcamento;
            } catch (SQLException | OrcamentoNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se o Orcamento já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Orcamento já possui um ID e não pode ser criado novamente.");
        }
    }

    /**
     * Retorna uma lista de todos os orçamentos.
     *
     * @return Lista de todos os orçamentos.
     */
    @Override
    public List<Orcamento> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Orcamento existente no banco de dados.
     *
     * @param orcamento O objeto Orcamento com os dados atualizados.
     * @return O Orcamento atualizado.
     * @throws OrcamentoNotFoundException Se o Orcamento não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Orcamento update(Orcamento orcamento) throws OrcamentoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            orcamento = this.dao.update(orcamento, connection);
            connection.commit();
            return orcamento;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Orcamento do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Orcamento a ser excluído.
     * @throws OrcamentoNotFoundException Se o Orcamento não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws OrcamentoNotFoundException, SQLException {
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
