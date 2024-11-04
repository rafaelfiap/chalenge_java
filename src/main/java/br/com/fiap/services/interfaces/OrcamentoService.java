package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.OrcamentoNotFoundException;
import br.com.fiap.exceptions.OrcamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Orcamento;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Orcamento.
 * Define métodos para criar, atualizar, excluir e buscar orçamentos.
 *
 * @since 1.0
 */
public interface OrcamentoService {

    /**
     * Cria um novo orçamento no sistema.
     *
     * @param orcamento A instância de Orcamento a ser criada.
     * @return O Orcamento criado com o ID gerado.
     * @throws OrcamentoNotSavedException Se o orçamento não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Orcamento create(Orcamento orcamento) throws UnsupportedServiceOperationException, SQLException, OrcamentoNotSavedException;

    /**
     * Retorna uma lista de todos os orçamentos.
     *
     * @return Lista de todos os orçamentos.
     */
    List<Orcamento> findAll();

    /**
     * Atualiza os dados de um orçamento existente no sistema.
     *
     * @param orcamento A instância de Orcamento com os dados atualizados.
     * @return O Orcamento atualizado.
     * @throws OrcamentoNotFoundException Se o orçamento não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Orcamento update(Orcamento orcamento) throws OrcamentoNotFoundException, SQLException;

    /**
     * Exclui um orçamento do sistema pelo seu ID.
     *
     * @param id O ID do orçamento a ser excluído.
     * @throws OrcamentoNotFoundException Se o orçamento não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws OrcamentoNotFoundException, SQLException;
}
