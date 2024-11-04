package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.AgendamentoNotFoundException;
import br.com.fiap.exceptions.AgendamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Agendamento;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Agendamento.
 * Define métodos para criar, atualizar, excluir e buscar agendamentos.
 *
 * @since 1.0
 */
public interface AgendamentoService {

    /**
     * Cria um novo agendamento no sistema.
     *
     * @param agendamento A instância de Agendamento a ser criada.
     * @return O Agendamento criado com o ‘ID’ gerado.
     * @throws AgendamentoNotSavedException Se o agendamento não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Agendamento create(Agendamento agendamento) throws UnsupportedServiceOperationException, SQLException, AgendamentoNotSavedException;

    /**
     * Retorna uma lista de todos os agendamentos.
     *
     * @return Lista de todos os agendamentos.
     */
    List<Agendamento> findAll();

    /**
     * Atualiza os dados de um agendamento existente no sistema.
     *
     * @param agendamento A instância de Agendamento com os dados atualizados.
     * @return O Agendamento atualizado.
     * @throws AgendamentoNotFoundException Se o agendamento não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Agendamento update(Agendamento agendamento) throws AgendamentoNotFoundException, SQLException;

    /**
     * Exclui um agendamento do sistema pelo seu ID.
     *
     * @param id O ID do agendamento a ser excluído.
     * @throws AgendamentoNotFoundException Se o agendamento não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws AgendamentoNotFoundException, SQLException;
}
