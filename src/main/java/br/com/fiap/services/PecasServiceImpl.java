package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.PecasDao;
import br.com.fiap.dao.PecasDaoFactory;
import br.com.fiap.exceptions.PecasNotFoundException;
import br.com.fiap.exceptions.PecasNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Pecas;
import br.com.fiap.services.interfaces.PecasService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação do serviço para a entidade Pecas, utilizando PecasDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para peças no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class PecasServiceImpl implements PecasService {

    private static final Logger logger = Logger.getLogger(PecasServiceImpl.class.getName());
    private final PecasDao dao = PecasDaoFactory.create();

    /**
     * Cria uma nova Peça no banco de dados.
     *
     * @param peca O objeto Pecas a ser criado.
     * @return A Peça criada com o ID gerado.
     * @throws PecasNotSavedException Se a Peça não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Pecas create(Pecas peca) throws UnsupportedServiceOperationException, SQLException, PecasNotSavedException {
        if (peca.getIdPeca() == null) {
            try (Connection connection = DatabaseConnectionFactory.create().get()) {
                connection.setAutoCommit(false);
                peca = this.dao.save(peca, connection);
                connection.commit();
                return peca;
            } catch (SQLException | PecasNotSavedException e) {
                logger.warning("Erro ao salvar peça: " + e.getMessage());
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Peça já possui um ID, criação não suportada.");
        }
    }

    /**
     * Retorna uma lista de todas as peças.
     *
     * @return Lista de todas as peças.
     */
    @Override
    public List<Pecas> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma Peça existente no banco de dados.
     *
     * @param peca O objeto Pecas com os dados atualizados.
     * @return A Peça atualizada.
     * @throws PecasNotFoundException Se a Peça não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Pecas update(Pecas peca) throws PecasNotFoundException, SQLException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            connection.setAutoCommit(false);
            Pecas updatedPeca = this.dao.update(peca, connection);
            connection.commit();
            return updatedPeca;
        } catch (SQLException e) {
            logger.warning("Erro ao atualizar peça: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Exclui uma Peça do banco de dados com base no ID fornecido.
     *
     * @param id O ‘ID’ da Peça a ser excluída.
     * @throws PecasNotFoundException Se a Peça não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws PecasNotFoundException, SQLException {
        try (Connection connection = DatabaseConnectionFactory.create().get()) {
            connection.setAutoCommit(false);
            this.dao.deleteById(id, connection);
            connection.commit();
        } catch (SQLException e) {
            logger.warning("Erro ao deletar peça: " + e.getMessage());
            throw e;
        }
    }
}
