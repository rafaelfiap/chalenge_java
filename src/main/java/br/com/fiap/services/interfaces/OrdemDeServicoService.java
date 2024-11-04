package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.OrdemDeServicoNotFoundException;
import br.com.fiap.exceptions.OrdemDeServicoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.OrdemDeServico;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade OrdemDeServico.
 * Define métodos para criar, atualizar, excluir e buscar ordens de serviço.
 *
 * @since 1.0
 */
public interface OrdemDeServicoService {

    /**
     * Cria uma nova ordem de serviço no sistema.
     *
     * @param ordem A instância de OrdemDeServico a ser criada.
     * @return A OrdemDeServico criada com o ID gerado.
     * @throws OrdemDeServicoNotSavedException Se a ordem de serviço não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    OrdemDeServico create(OrdemDeServico ordem) throws UnsupportedServiceOperationException, SQLException, OrdemDeServicoNotSavedException;

    /**
     * Retorna uma lista de todas as ordens de serviço.
     *
     * @return Lista de todas as ordens de serviço.
     */
    List<OrdemDeServico> findAll();

    /**
     * Atualiza os dados de uma ordem de serviço existente no sistema.
     *
     * @param ordem A instância de OrdemDeServico com os dados atualizados.
     * @return A OrdemDeServico atualizada.
     * @throws OrdemDeServicoNotFoundException Se a ordem de serviço não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    OrdemDeServico update(OrdemDeServico ordem) throws OrdemDeServicoNotFoundException, SQLException;

    /**
     * Exclui uma ordem de serviço do sistema pelo seu ID.
     *
     * @param id O ID da ordem de serviço a ser excluída.
     * @throws OrdemDeServicoNotFoundException Se a ordem de serviço não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws OrdemDeServicoNotFoundException, SQLException;
}
