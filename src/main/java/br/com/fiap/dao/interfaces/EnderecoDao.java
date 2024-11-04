package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.EnderecoNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica para operações CRUD no banco de dados para a entidade Endereco.
 *
 * <p>Essa interface é parametrizada com o tipo de entidade {@link T} e o tipo da chave primária {@link ID}.
 * Implementações específicas para EnderecoCliente e EnderecoOficina devem estender essa interface.</p>
 *
 * @param <T> Tipo da entidade (ex: EnderecoCliente, EnderecoOficina, etc.).
 * @param <ID> Tipo da chave primária (ex: Integer, Long, String, etc.).
 * @version 1.0
 * @since 1.0
 */
public interface EnderecoDao<T, ID> {

    /**
     * Retorna todas as entidades do tipo {@link T} do banco de dados.
     *
     * @return Lista de entidades.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<T> findAll();

    /**
     * Remove uma entidade do tipo {@link T} do banco de dados com base no ID fornecido.
     *
     * @param id O ID da entidade a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */

    void deleteById(Long id, Connection connection) throws EnderecoNotFoundException, SQLException;

    /**
     * Salva uma nova entidade do tipo {@link T} no banco de dados.
     *
     * @param entity A entidade a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A entidade salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    T save(T entity, Connection connection) throws SQLException;

    /**
     * Atualiza uma entidade existente do tipo {@link T} no banco de dados.
     *
     * @param entity A entidade com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A entidade atualizada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    T update(T entity, Connection connection) throws SQLException;
}
