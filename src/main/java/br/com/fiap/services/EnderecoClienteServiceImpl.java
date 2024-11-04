package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.EnderecoDao;
import br.com.fiap.dao.EnderecoClienteDaoFactory;
import br.com.fiap.exceptions.EnderecoNotFoundException;
import br.com.fiap.exceptions.EnderecoNotSavedException;
import br.com.fiap.models.Endereco;
import br.com.fiap.services.interfaces.EnderecoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade EnderecoCliente, utilizando EnderecoClienteDaoFactory.
 */
public final class EnderecoClienteServiceImpl implements EnderecoService {

    private final EnderecoDao<Endereco, Long> dao = EnderecoClienteDaoFactory.create();

    /**
     * Cria um novo EnderecoCliente no banco de dados.
     *
     * @param endereco O objeto Endereco a ser criado.
     * @return O Endereco criado com o ID gerado.
     * @throws EnderecoNotSavedException Se o Endereco não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Endereco create(Endereco endereco) throws SQLException, EnderecoNotSavedException {
        if (endereco.getIdEndereco() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                endereco = this.dao.save(endereco, connection);
                connection.commit();
                return endereco;
            } catch (SQLException | EnderecoNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedOperationException("Endereço já possui um ID, operação não permitida.");
        }
    }

    /**
     * Retorna uma lista de todos os endereços.
     *
     * @return Lista de todos os endereços.
     */
    @Override
    public List<Endereco> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um EnderecoCliente existente no banco de dados.
     *
     * @param endereco O objeto Endereco com os dados atualizados.
     * @return O Endereco atualizado.
     * @throws EnderecoNotFoundException Se o Endereco não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Endereco update(Endereco endereco) throws EnderecoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            endereco = this.dao.update(endereco, connection);
            connection.commit();
            return endereco;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Endereco do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Endereco a ser excluído.
     * @throws EnderecoNotFoundException Se o Endereco não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws EnderecoNotFoundException, SQLException {
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
