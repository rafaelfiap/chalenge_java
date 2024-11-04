package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.ServicosDao;
import br.com.fiap.dao.ServicosDaoFactory;
import br.com.fiap.exceptions.ServicosNotFoundException;
import br.com.fiap.exceptions.ServicosNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Servicos;
import br.com.fiap.services.interfaces.ServicosService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Servicos, utilizando ServicosDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para serviços no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class ServicosServiceImpl implements ServicosService {

    private final ServicosDao dao = ServicosDaoFactory.create();

    /**
     * Cria um novo Serviço no banco de dados.
     *
     * @param servico O objeto Servicos a ser criado.
     * @return O Serviço criado com o ID gerado.
     * @throws ServicosNotSavedException Se o Serviço não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Servicos create(Servicos servico) throws UnsupportedServiceOperationException, SQLException, ServicosNotSavedException {
        if (servico.getIdServico() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                servico = this.dao.save(servico, connection);
                connection.commit();
                return servico;
            } catch (SQLException | ServicosNotSavedException e) {
                connection.rollback();
                throw e;
            }
        } else {
            throw new UnsupportedServiceOperationException("Serviço já possui um ID e não pode ser criado novamente.");
        }
    }

    /**
     * Retorna uma lista de todos os serviços.
     *
     * @return Lista de todos os serviços.
     */
    @Override
    public List<Servicos> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Serviço existente no banco de dados.
     *
     * @param servico O objeto Servicos com os dados atualizados.
     * @return O Serviço atualizado.
     * @throws ServicosNotFoundException Se o Serviço não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Servicos update(Servicos servico) throws ServicosNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            servico = this.dao.update(servico, connection);
            connection.commit();
            return servico;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Serviço do banco de dados com base no ID fornecido.
     *
     * @param id O ID do Serviço a ser excluído.
     * @throws ServicosNotFoundException Se o Serviço não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws ServicosNotFoundException, SQLException {
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
