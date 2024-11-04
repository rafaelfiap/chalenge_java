package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.PecasNotFoundException;
import br.com.fiap.exceptions.PecasNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Pecas;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Pecas.
 * Define métodos para criar, atualizar, excluir e buscar peças.
 *
 * @since 1.0
 */
public interface PecasService {

    /**
     * Cria uma nova peça no sistema.
     *
     * @param peca A instância de Pecas a ser criada.
     * @return A Peça criada com o ID gerado.
     * @throws PecasNotSavedException Se a peça não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Pecas create(Pecas peca) throws UnsupportedServiceOperationException, SQLException, PecasNotSavedException;

    /**
     * Retorna uma lista de todas as peças.
     *
     * @return Lista de todas as peças.
     */
    List<Pecas> findAll();

    /**
     * Atualiza os dados de uma peça existente no sistema.
     *
     * @param peca A instância de Pecas com os dados atualizados.
     * @return A Peça atualizada.
     * @throws PecasNotFoundException Se a peça não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Pecas update(Pecas peca) throws PecasNotFoundException, SQLException;

    /**
     * Exclui uma peça do sistema pelo seu ID.
     *
     * @param id O ID da peça a ser excluída.
     * @throws PecasNotFoundException Se a peça não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws PecasNotFoundException, SQLException;
}
