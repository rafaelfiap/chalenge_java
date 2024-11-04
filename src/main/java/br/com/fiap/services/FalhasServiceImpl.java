package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.FalhasDao;
import br.com.fiap.dao.FalhasDaoFactory;
import br.com.fiap.exceptions.FalhasNotFoundException;
import br.com.fiap.exceptions.FalhasNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Falhas;
import br.com.fiap.services.interfaces.FalhasService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Falhas, utilizando FalhasDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para falhas no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class FalhasServiceImpl implements FalhasService {

    private final FalhasDao dao = FalhasDaoFactory.create();

    /**
     * Cria uma nova Falha no banco de dados.
     *
     * @param falhas O objeto Falhas a ser criado.
     * @return A Falha criada com o ID gerado.
     * @throws FalhasNotSavedException Se a Falha não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Falhas create(Falhas falhas) throws UnsupportedServiceOperationException, SQLException, FalhasNotSavedException {
        if (falhas.getIdFalha() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                falhas = this.dao.save(falhas, connection);
                connection.commit();
                return falhas;
            } catch (SQLException | FalhasNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Falha já possui um ID e não pode ser criada novamente.");
        }
    }

    /**
     * Retorna uma lista de todas as falhas.
     *
     * @return Lista de todas as falhas.
     */
    @Override
    public List<Falhas> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma Falha existente no banco de dados.
     *
     * @param falhas O objeto Falhas com os dados atualizados.
     * @return A Falha atualizada.
     * @throws FalhasNotFoundException Se a Falha não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Falhas update(Falhas falhas) throws FalhasNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            falhas = this.dao.update(falhas, connection);
            connection.commit();
            return falhas;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui uma Falha do banco de dados com base no ID fornecido.
     *
     * @param id O ID da Falha a ser excluída.
     * @throws FalhasNotFoundException Se a Falha não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws FalhasNotFoundException, SQLException {
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
