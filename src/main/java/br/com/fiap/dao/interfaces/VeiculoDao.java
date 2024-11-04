package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.VeiculoNotFoundException;
import br.com.fiap.exceptions.VeiculoNotSavedException;
import br.com.fiap.models.Veiculo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Veiculo.
 *
 * @version 1.0
 * @since 1.0
 */
public interface VeiculoDao {

    /**
     * Busca todas as instâncias de Veiculo no banco de dados.
     *
     * @return Lista de instâncias de {@link Veiculo}.
     */
    List<Veiculo> findAll();

    /**
     * Remove um Veiculo pelo ID no banco de dados.
     *
     * @param id O ID do Veiculo a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws VeiculoNotFoundException Se o Veiculo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws VeiculoNotFoundException, SQLException;

    /**
     * Salva um novo Veiculo no banco de dados.
     *
     * @param veiculo A instância de Veiculo a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Veiculo} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws VeiculoNotSavedException Se o Veiculo não puder ser salvo.
     */
    Veiculo save(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotSavedException;

    /**
     * Atualiza um Veiculo existente no banco de dados.
     *
     * @param veiculo A instância de Veiculo com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Veiculo} atualizada.
     * @throws VeiculoNotFoundException Se o Veiculo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Veiculo update(Veiculo veiculo, Connection connection) throws VeiculoNotFoundException, SQLException;
}
