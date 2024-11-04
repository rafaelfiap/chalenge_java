package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.OrdemDeServicoNotFoundException;
import br.com.fiap.exceptions.OrdemDeServicoNotSavedException;
import br.com.fiap.models.OrdemDeServico;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade OrdemDeServico.
 *
 * @version 1.0
 * @since 1.0
 */
public interface OrdemDeServicoDao {

    /**
     * Busca todas as instâncias de OrdemDeServico no banco de dados.
     *
     * @return Lista de instâncias de {@link OrdemDeServico}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<OrdemDeServico> findAll();

    /**
     * Remove uma OrdemDeServico pelo ID no banco de dados.
     *
     * @param id O ID da OrdemDeServico a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws OrdemDeServicoNotFoundException Se a OrdemDeServico com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws OrdemDeServicoNotFoundException, SQLException;

    /**
     * Salva uma nova OrdemDeServico no banco de dados.
     *
     * @param ordemDeServico A instância de OrdemDeServico a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link OrdemDeServico} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws OrdemDeServicoNotSavedException Se a OrdemDeServico não puder ser salva.
     */
    OrdemDeServico save(OrdemDeServico ordemDeServico, Connection connection) throws SQLException, OrdemDeServicoNotSavedException;

    /**
     * Atualiza uma OrdemDeServico existente no banco de dados.
     *
     * @param ordemDeServico A instância de OrdemDeServico com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link OrdemDeServico} atualizada.
     * @throws OrdemDeServicoNotFoundException Se a OrdemDeServico com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    OrdemDeServico update(OrdemDeServico ordemDeServico, Connection connection) throws OrdemDeServicoNotFoundException, SQLException;
}
