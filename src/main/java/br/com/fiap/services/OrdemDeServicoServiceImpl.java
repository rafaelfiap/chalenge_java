package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.OrdemDeServicoDao;
import br.com.fiap.dao.OrdemDeServicoDaoFactory;
import br.com.fiap.exceptions.OrdemDeServicoNotFoundException;
import br.com.fiap.exceptions.OrdemDeServicoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.OrdemDeServico;
import br.com.fiap.services.interfaces.OrdemDeServicoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade OrdemDeServico, utilizando OrdemDeServicoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para ordens de serviço no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OrdemDeServicoServiceImpl implements OrdemDeServicoService {

    private final OrdemDeServicoDao dao = OrdemDeServicoDaoFactory.create();

    /**
     * Cria uma nova OrdemDeServico no banco de dados.
     *
     * @param ordemDeServico O objeto OrdemDeServico a ser criado.
     * @return A OrdemDeServico criada com o ID gerado.
     * @throws OrdemDeServicoNotSavedException Se a OrdemDeServico não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public OrdemDeServico create(OrdemDeServico ordemDeServico) throws UnsupportedServiceOperationException, SQLException, OrdemDeServicoNotSavedException {
        if (ordemDeServico.getIdOs() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                ordemDeServico = this.dao.save(ordemDeServico, connection);
                connection.commit();
                return ordemDeServico;
            } catch (SQLException | OrdemDeServicoNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se a OrdemDeServico já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Ordem de Serviço já possui um ID e não pode ser criada novamente.");
        }
    }

    /**
     * Retorna uma lista de todas as ordens de serviço.
     *
     * @return Lista de todas as ordens de serviço.
     */
    @Override
    public List<OrdemDeServico> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma OrdemDeServico existente no banco de dados.
     *
     * @param ordemDeServico O objeto OrdemDeServico com os dados atualizados.
     * @return A OrdemDeServico atualizada.
     * @throws OrdemDeServicoNotFoundException Se a OrdemDeServico não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public OrdemDeServico update(OrdemDeServico ordemDeServico) throws OrdemDeServicoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            ordemDeServico = this.dao.update(ordemDeServico, connection);
            connection.commit();
            return ordemDeServico;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui uma OrdemDeServico do banco de dados com base no ID fornecido.
     *
     * @param id O ID da OrdemDeServico a ser excluída.
     * @throws OrdemDeServicoNotFoundException Se a OrdemDeServico não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws OrdemDeServicoNotFoundException, SQLException {
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
