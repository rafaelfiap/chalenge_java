package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.FalhasNotFoundException;
import br.com.fiap.exceptions.FalhasNotSavedException;
import br.com.fiap.models.Falhas;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Falhas.
 *
 * @version 1.0
 * @since 1.0
 */
public interface FalhasDao {

    /**
     * Busca todas as instâncias de Falhas no banco de dados.
     *
     * @return Lista de instâncias de {@link Falhas}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Falhas> findAll();

    /**
     * Remove uma Falhas pelo ID no banco de dados.
     *
     * @param id O ID da Falhas a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws FalhasNotFoundException Se a Falhas com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws FalhasNotFoundException, SQLException;

    /**
     * Salva uma nova Falhas no banco de dados.
     *
     * @param Falhas A instância de Falhas a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Falhas} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws FalhasNotSavedException Se a Falhas não puder ser salva.
     */
    Falhas save(Falhas Falhas, Connection connection) throws SQLException, FalhasNotSavedException;

    /**
     * Atualiza uma Falhas existente no banco de dados.
     *
     * @param Falhas A instância de Falhas com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Falhas} atualizada.
     * @throws FalhasNotFoundException Se a Falhas com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Falhas update(Falhas Falhas, Connection connection) throws FalhasNotFoundException, SQLException;
}
