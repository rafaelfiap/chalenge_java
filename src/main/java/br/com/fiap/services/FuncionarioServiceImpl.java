package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.FuncionarioDao;
import br.com.fiap.dao.FuncionarioDaoFactory;
import br.com.fiap.exceptions.FuncionarioNotFoundException;
import br.com.fiap.exceptions.FuncionarioNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Funcionario;
import br.com.fiap.services.interfaces.FuncionarioService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;



/**
 * Implementação do serviço para a entidade Funcionario, utilizando FuncionarioDaoFactory.
 */
public final class FuncionarioServiceImpl implements FuncionarioService {

    private final FuncionarioDao dao = FuncionarioDaoFactory.create();


    /**
     * Cria um novo Funcionario no banco de dados.
     *
     * @param funcionario O objeto Funcionario a ser criado.
     * @return O Funcionario criado com o ID gerado.
     * @throws FuncionarioNotSavedException Se o Funcionario não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Funcionario create(Funcionario funcionario) throws UnsupportedServiceOperationException, SQLException, FuncionarioNotSavedException {
        if (funcionario.getIdFuncionario() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try{
                funcionario = this.dao.save(funcionario, connection);
                connection.commit();
                return funcionario;
            } catch (SQLException | FuncionarioNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se a Pessoa já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Funcionario já possui um ID e não pode ser criado novamente.");
        }
    }

    /**
     * Retorna uma lista de todos os funcionários.
     *
     * @return Lista de todos os funcionários.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public List<Funcionario> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Funcionario existente no banco de dados.
     *
     * @param funcionario O objeto Funcionario com os dados atualizados.
     * @return O Funcionario atualizado.
     * @throws FuncionarioNotFoundException Se o Funcionario não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Funcionario update(Funcionario funcionario) throws FuncionarioNotFoundException, SQLException {
            Connection connection = DatabaseConnectionFactory.create().get();
            funcionario = this.dao.update(funcionario, connection);
            connection.commit();
            return funcionario;
    }

    /**
     * Exclui um Funcionario do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Funcionario a ser excluído.
     * @throws FuncionarioNotFoundException Se o Funcionario não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws FuncionarioNotFoundException, SQLException {
            Connection connection = DatabaseConnectionFactory.create().get();
            this.dao.deleteById(id, connection);
            connection.commit();
    }

}
