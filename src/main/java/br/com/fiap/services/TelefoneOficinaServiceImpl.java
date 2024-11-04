package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.TelefoneDao;
import br.com.fiap.dao.TelefoneOficinaDaoFactory;
import br.com.fiap.exceptions.TelefoneNotFoundException;
import br.com.fiap.exceptions.TelefoneNotSavedException;
import br.com.fiap.models.Telefone;
import br.com.fiap.services.interfaces.TelefoneService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade TelefoneOficina, utilizando TelefoneOficinaDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para telefones de oficinas no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class TelefoneOficinaServiceImpl implements TelefoneService {

    private final TelefoneDao<Telefone, Long> dao = TelefoneOficinaDaoFactory.create();

    /**
     * Cria um novo TelefoneOficina no banco de dados.
     *
     * @param telefone O objeto Telefone a ser criado.
     * @return O Telefone criado com o ID gerado.
     * @throws TelefoneNotSavedException Se o Telefone não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Telefone create(Telefone telefone) throws SQLException, TelefoneNotSavedException {
        if (telefone.getIdTelefone() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                telefone = this.dao.save(telefone, connection);
                connection.commit();
                return telefone;
            } catch (SQLException | TelefoneNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException("Telefone já possui um ID, operação não permitida.");
        }
    }

    /**
     * Retorna uma lista de todos os telefones de oficina.
     *
     * @return Lista de todos os telefones de oficina.
     */
    @Override
    public List<Telefone> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um TelefoneOficina existente no banco de dados.
     *
     * @param telefone O objeto Telefone com os dados atualizados.
     * @return O Telefone atualizado.
     * @throws TelefoneNotFoundException Se o Telefone não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Telefone update(Telefone telefone) throws TelefoneNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            telefone = this.dao.update(telefone, connection);
            connection.commit();
            return telefone;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Telefone do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Telefone a ser excluído.
     * @throws TelefoneNotFoundException Se o Telefone não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws TelefoneNotFoundException, SQLException {
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
