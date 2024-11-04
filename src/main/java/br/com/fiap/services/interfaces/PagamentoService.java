package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.PagamentoNotFoundException;
import br.com.fiap.exceptions.PagamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Pagamento;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Pagamento.
 * Define métodos para criar, atualizar, excluir e buscar pagamentos.
 *
 * @since 1.0
 */
public interface PagamentoService {

    /**
     * Cria um novo pagamento no sistema.
     *
     * @param pagamento A instância de Pagamento a ser criada.
     * @return O Pagamento criado com o ID gerado.
     * @throws PagamentoNotSavedException Se o pagamento não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Pagamento create(Pagamento pagamento) throws UnsupportedServiceOperationException, SQLException, PagamentoNotSavedException;

    /**
     * Retorna uma lista de todos os pagamentos.
     *
     * @return Lista de todos os pagamentos.
     */
    List<Pagamento> findAll();

    /**
     * Atualiza os dados de um pagamento existente no sistema.
     *
     * @param pagamento A instância de Pagamento com os dados atualizados.
     * @return O Pagamento atualizado.
     * @throws PagamentoNotFoundException Se o pagamento não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Pagamento update(Pagamento pagamento) throws PagamentoNotFoundException, SQLException;

    /**
     * Exclui um pagamento do sistema pelo seu ID.
     *
     * @param id O ID do pagamento a ser excluído.
     * @throws PagamentoNotFoundException Se o pagamento não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws PagamentoNotFoundException, SQLException;
}
