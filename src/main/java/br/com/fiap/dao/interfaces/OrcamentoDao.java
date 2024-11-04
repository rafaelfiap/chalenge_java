package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.OrcamentoNotFoundException;
import br.com.fiap.exceptions.OrcamentoNotSavedException;
import br.com.fiap.models.Orcamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Orcamento.
 *
 * @version 1.0
 * @since 1.0
 */
public interface OrcamentoDao {

    /**
     * Busca todas as instâncias de Orcamento no banco de dados.
     *
     * @return Lista de instâncias de {@link Orcamento}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Orcamento> findAll();

    /**
     * Remove um Orcamento pelo ID no banco de dados.
     *
     * @param id O ID do Orcamento a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws OrcamentoNotFoundException Se o Orcamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws OrcamentoNotFoundException, SQLException;

    /**
     * Salva um novo Orcamento no banco de dados.
     *
     * @param orcamento A instância de Orcamento a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Orcamento} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws OrcamentoNotSavedException Se o Orcamento não puder ser salvo.
     */
    Orcamento save(Orcamento orcamento, Connection connection) throws SQLException, OrcamentoNotSavedException;

    /**
     * Atualiza um Orcamento existente no banco de dados.
     *
     * @param orcamento A instância de Orcamento com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Orcamento} atualizada.
     * @throws OrcamentoNotFoundException Se o Orcamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Orcamento update(Orcamento orcamento, Connection connection) throws OrcamentoNotFoundException, SQLException;
}
