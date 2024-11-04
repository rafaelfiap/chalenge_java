package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.OficinaDao;
import br.com.fiap.dao.OficinaDaoFactory;
import br.com.fiap.exceptions.OficinaNotFoundException;
import br.com.fiap.exceptions.OficinaNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Oficina;
import br.com.fiap.services.interfaces.OficinaService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Oficina, utilizando OficinaDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para oficinas no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OficinaServiceImpl implements OficinaService {

    private final OficinaDao dao = OficinaDaoFactory.create();

    /**
     * Cria uma nova Oficina no banco de dados.
     *
     * @param oficina O objeto Oficina a ser criado.
     * @return A Oficina criada com o ID gerado.
     * @throws OficinaNotSavedException Se a Oficina não puder ser salva.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Oficina create(Oficina oficina) throws UnsupportedServiceOperationException, SQLException, OficinaNotSavedException {
        if (oficina.getIdOficina() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                oficina = this.dao.save(oficina, connection);
                connection.commit();
                return oficina;
            } catch (SQLException | OficinaNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se a Oficina já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Operação de serviço não suportada: verifique se o serviço solicitado está implementado ou permitido.");
        }
    }

    /**
     * Retorna uma lista de todas as oficinas.
     *
     * @return Lista de todas as oficinas.
     */
    @Override
    public List<Oficina> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza uma Oficina existente no banco de dados.
     *
     * @param oficina O objeto Oficina com os dados atualizados.
     * @return A Oficina atualizada.
     * @throws OficinaNotFoundException Se a Oficina não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Oficina update(Oficina oficina) throws OficinaNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            oficina = this.dao.update(oficina, connection);
            connection.commit();
            return oficina;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui uma Oficina do banco de dados com base no ID fornecido.
     *
     * @param id O ID da Oficina a ser excluída.
     * @throws OficinaNotFoundException Se a Oficina não for encontrada.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws OficinaNotFoundException, SQLException {
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
