package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.PecasNotFoundException;
import br.com.fiap.exceptions.PecasNotSavedException;
import br.com.fiap.models.Pecas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Pecas.
 *
 * <p>Essa interface define métodos para criar, atualizar, excluir e buscar peças no banco de dados,
 * utilizando a entidade {@link Pecas}.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public interface PecasDao {

    /**
     * Busca todas as instâncias de Pecas no banco de dados.
     *
     * @return Lista de instâncias de {@link Pecas}.
     */
    List<Pecas> findAll();

    /**
     * Remove uma Peça pelo ID no banco de dados.
     *
     * @param id O ID da Peça a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws PecasNotFoundException Se a Peça com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws PecasNotFoundException, SQLException;

    /**
     * Salva uma nova Peça no banco de dados.
     *
     * @param pecas A instância de Pecas a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Pecas} que foi salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws PecasNotSavedException Se a Peça não puder ser salva.
     */
    Pecas save(Pecas pecas, Connection connection) throws SQLException, PecasNotSavedException;

    /**
     * Atualiza uma Peça existente no banco de dados.
     *
     * @param pecas A instância de Pecas com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Pecas} atualizada.
     * @throws PecasNotFoundException Se a Peça com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Pecas update(Pecas pecas, Connection connection) throws PecasNotFoundException, SQLException;


}
