package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.PagamentoNotFoundException;
import br.com.fiap.exceptions.PagamentoNotSavedException;
import br.com.fiap.models.Pagamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Pagamento.
 *
 * @version 1.0
 * @since 1.0
 */
public interface PagamentoDao {

    /**
     * Busca todas as instâncias de Pagamento no banco de dados.
     *
     * @return Lista de instâncias de {@link Pagamento}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Pagamento> findAll();

    /**
     * Remove um Pagamento pelo ID no banco de dados.
     *
     * @param id O ID do Pagamento a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws PagamentoNotFoundException Se o Pagamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws PagamentoNotFoundException, SQLException;

    /**
     * Salva um novo Pagamento no banco de dados.
     *
     * @param pagamento A instância de Pagamento a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Pagamento} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws PagamentoNotSavedException Se o Pagamento não puder ser salvo.
     */
    Pagamento save(Pagamento pagamento, Connection connection) throws SQLException, PagamentoNotSavedException;

    /**
     * Atualiza um Pagamento existente no banco de dados.
     *
     * @param pagamento A instância de Pagamento com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Pagamento} atualizada.
     * @throws PagamentoNotFoundException Se o Pagamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Pagamento update(Pagamento pagamento, Connection connection) throws PagamentoNotFoundException, SQLException;
}
