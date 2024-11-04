package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.AgendamentoNotFoundException;
import br.com.fiap.exceptions.AgendamentoNotSavedException;
import br.com.fiap.models.Agendamento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Agendamento.
 *
 * @version 1.0
 * @since 1.0
 */
public interface AgendamentoDao {

    /**
     * Busca todas as instâncias de Agendamento no banco de dados.
     *
     * @return Lista de instâncias de {@link Agendamento}.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    List<Agendamento> findAll();

    /**
     * Remove um Agendamento pelo ID no banco de dados.
     *
     * @param id O ID do Agendamento a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws AgendamentoNotFoundException Se o Agendamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws AgendamentoNotFoundException, SQLException;

    /**
     * Salva um novo Agendamento no banco de dados.
     *
     * @param agendamento A instância de Agendamento a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Agendamento} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws AgendamentoNotSavedException Se o Agendamento não puder ser salvo.
     */
    Agendamento save(Agendamento agendamento, Connection connection) throws SQLException, AgendamentoNotSavedException;

    /**
     * Atualiza um Agendamento existente no banco de dados.
     *
     * @param agendamento A instância de Agendamento com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Agendamento} atualizada.
     * @throws AgendamentoNotFoundException Se o Agendamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Agendamento update(Agendamento agendamento, Connection connection) throws AgendamentoNotFoundException, SQLException;
}
