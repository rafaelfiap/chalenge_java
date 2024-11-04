package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.FalhasNotFoundException;
import br.com.fiap.exceptions.FalhasNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Falhas;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Falhas.
 * Define métodos para criar, atualizar, excluir e buscar falhas.
 *
 * @since 1.0
 */
public interface FalhasService {

    /**
     * Cria uma nova falha no sistema.
     *
     * @param falha A instância de Falhas a ser criada.
     * @return A Falha criada com o ID gerado.
     * @throws FalhasNotSavedException Se a falha não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Falhas create(Falhas falha) throws UnsupportedServiceOperationException, SQLException, FalhasNotSavedException;

    /**
     * Retorna uma lista de todas as falhas.
     *
     * @return Lista de todas as falhas.
     */
    List<Falhas> findAll();

    /**
     * Atualiza os dados de uma falha existente no sistema.
     *
     * @param falha A instância de Falhas com os dados atualizados.
     * @return A Falha atualizada.
     * @throws FalhasNotFoundException Se a falha não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Falhas update(Falhas falha) throws FalhasNotFoundException, SQLException;

    /**
     * Exclui uma falha do sistema pelo seu ID.
     *
     * @param id O ID da falha a ser excluída.
     * @throws FalhasNotFoundException Se a falha não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws FalhasNotFoundException, SQLException;
}
