package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.FuncionarioNotFoundException;
import br.com.fiap.exceptions.FuncionarioNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Funcionario;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Funcionario.
 * Define métodos para criar, atualizar, excluir e buscar funcionários.
 *
 * @since 1.0
 */
public interface FuncionarioService {

    /**
     * Cria um novo funcionário no sistema.
     *
     * @param funcionario A instância de Funcionario a ser criada.
     * @return O Funcionario criado com o ID gerado.
     * @throws FuncionarioNotSavedException Se o funcionário não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Funcionario create(Funcionario funcionario) throws UnsupportedServiceOperationException, SQLException, FuncionarioNotSavedException;

    /**
     * Retorna uma lista de todos os funcionários.
     *
     * @return Lista de todos os funcionários.
     */
    List<Funcionario> findAll();

    /**
     * Atualiza os dados de um funcionário existente no sistema.
     *
     * @param funcionario A instância de Funcionario com os dados atualizados.
     * @return O Funcionario atualizado.
     * @throws FuncionarioNotFoundException Se o funcionário não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Funcionario update(Funcionario funcionario) throws FuncionarioNotFoundException, SQLException;

    /**
     * Exclui um funcionário do sistema pelo seu ID.
     *
     * @param id O ID do funcionário a ser excluído.
     * @throws FuncionarioNotFoundException Se o funcionário não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws FuncionarioNotFoundException, SQLException;
}
