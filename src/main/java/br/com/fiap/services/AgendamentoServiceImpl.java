package br.com.fiap.services;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.AgendamentoDao;
import br.com.fiap.dao.AgendamentoDaoFactory;
import br.com.fiap.exceptions.AgendamentoNotFoundException;
import br.com.fiap.exceptions.AgendamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Agendamento;
import br.com.fiap.services.interfaces.AgendamentoService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementação do serviço para a entidade Agendamento, utilizando AgendamentoDaoFactory.
 *
 * <p>Esta classe fornece operações de criação, leitura, atualização e exclusão para agendamentos no sistema.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class AgendamentoServiceImpl implements AgendamentoService {

    private final AgendamentoDao dao = AgendamentoDaoFactory.create();

    /**
     * Cria um novo Agendamento no banco de dados.
     *
     * @param agendamento O objeto Agendamento a ser criado.
     * @return O Agendamento criado com o ID gerado.
     * @throws AgendamentoNotSavedException Se o Agendamento não puder ser salvo.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Agendamento create(Agendamento agendamento) throws UnsupportedServiceOperationException, SQLException, AgendamentoNotSavedException {
        if (agendamento.getIdAgendamento() == null) {
            Connection connection = DatabaseConnectionFactory.create().get();
            try {
                agendamento = this.dao.save(agendamento, connection);
                connection.commit();
                return agendamento;
            } catch (SQLException | AgendamentoNotSavedException e) {
                // Reverte a transação em caso de erro.
                connection.rollback();
                throw e;
            }
        } else {
            // Lança exceção se o Agendamento já tiver um ID, indicando que a operação não é suportada.
            throw new UnsupportedServiceOperationException("Agendamento já possui um ID e não pode ser criado.");
        }
    }

    /**
     * Retorna uma lista de todos os agendamentos.
     *
     * @return Lista de todos os agendamentos.
     */
    @Override
    public List<Agendamento> findAll() {
        return this.dao.findAll();
    }

    /**
     * Atualiza um Agendamento existente no banco de dados.
     *
     * @param agendamento O objeto Agendamento com os dados atualizados.
     * @return O Agendamento atualizado.
     * @throws AgendamentoNotFoundException Se o Agendamento não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public Agendamento update(Agendamento agendamento) throws AgendamentoNotFoundException, SQLException {
        Connection connection = DatabaseConnectionFactory.create().get();
        try {
            agendamento = this.dao.update(agendamento, connection);
            connection.commit();
            return agendamento;
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
    }

    /**
     * Exclui um Agendamento do banco de dados com base no ‘ID’ fornecido.
     *
     * @param id O ‘ID’ do Agendamento a ser excluído.
     * @throws AgendamentoNotFoundException Se o Agendamento não for encontrado.
     * @throws SQLException Em caso de erro de SQL.
     */
    @Override
    public void deleteById(Long id) throws AgendamentoNotFoundException, SQLException {
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
