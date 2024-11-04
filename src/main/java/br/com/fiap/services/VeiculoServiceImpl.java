package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.VeiculoDao;
import br.com.fiap.dao.VeiculoDaoFactory;
import br.com.fiap.exceptions.VeiculoNotFoundException;
import br.com.fiap.exceptions.VeiculoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Veiculo;
import br.com.fiap.services.interfaces.VeiculoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Veiculo, utilizando VeiculoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para veículos no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class VeiculoServiceImpl implements VeiculoService {

    private final VeiculoDao dao = VeiculoDaoFactory.create();

    /**
     * Cria um novo Veiculo no banco de dados.
     *
     * @param veiculo O objeto Veiculo a ser criado.
     * @return O Veiculo criado com o ID gerado.
     * @throws VeiculoNotSavedException Se o Veiculo não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Veiculo create(Veiculo veiculo) throws UnsupportedServiceOperationException, SQLException, VeiculoNotSavedException {
        if (veiculo.getIdVeiculo() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                veiculo = this.dao.save(veiculo, connection);
                connection.commit();
                return veiculo;
            } catch (SQLException | VeiculoNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se o Veiculo já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Veículo já possui um ID e não pode ser criado novamente.");
        }
    }

    /**
     * Retorna uma lista de todos os veículos.
     *
     * @return Lista de todos os veículos.
     */
    @Override
    public List<Veiculo> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Veiculo existente no banco de dados.
     *
     * @param veiculo O objeto Veiculo com os dados atualizados.
     * @return O Veiculo atualizado.
     * @throws VeiculoNotFoundException Se o Veiculo não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Veiculo update(Veiculo veiculo) throws VeiculoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            veiculo = this.dao.update(veiculo, connection);
            connection.commit();
            return veiculo;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Veiculo do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Veiculo a ser excluído.
     * @throws VeiculoNotFoundException Se o Veiculo não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws VeiculoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            this.dao.deleteById(id, connection);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }
}
