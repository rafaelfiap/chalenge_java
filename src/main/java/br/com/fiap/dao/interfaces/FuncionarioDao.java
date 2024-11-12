package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.FuncionarioNotFoundException;
import br.com.fiap.exceptions.FuncionarioNotSavedException;
import br.com.fiap.models.Funcionario;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Funcionario.
 *
 * @version 1.0
 * @since 1.0
 */
public interface FuncionarioDao {

    /**
     * Busca todas as instâncias de Funcionario no banco de dados.
     *
     * @return Lista de instâncias de {@link Funcionario}.
     */
    List<Funcionario> findAll();

    /**
     * Remove um Funcionario pelo ID no banco de dados.
     *
     * @param id O ID do Funcionario a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws FuncionarioNotFoundException Se o Funcionario com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws FuncionarioNotFoundException, SQLException;

    /**
     * Salva um novo Funcionario no banco de dados.
     *
     * @param funcionario A instância de Funcionario a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Funcionario} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws FuncionarioNotSavedException Se o Funcionario não puder ser salvo.
     */
    Funcionario save(Funcionario funcionario, Connection connection) throws SQLException, FuncionarioNotSavedException;

    /**
     * Atualiza um Funcionario existente no banco de dados.
     *
     * @param funcionario A instância de Funcionario com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Funcionario} atualizada.
     * @throws FuncionarioNotFoundException Se o Funcionario com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Funcionario update(Funcionario funcionario, Connection connection) throws FuncionarioNotFoundException, SQLException;
}
