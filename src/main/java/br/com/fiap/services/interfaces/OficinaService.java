package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.OficinaNotFoundException;
import br.com.fiap.exceptions.OficinaNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Oficina;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Oficina.
 * Define métodos para criar, atualizar, excluir e buscar oficinas.
 *
 * @since 1.0
 */
public interface OficinaService {

    /**
     * Cria uma nova oficina no sistema.
     *
     * @param oficina A instância de Oficina a ser criada.
     * @return A Oficina criada com o ID gerado.
     * @throws OficinaNotSavedException Se a oficina não puder ser salva.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Oficina create(Oficina oficina) throws UnsupportedServiceOperationException, SQLException, OficinaNotSavedException;

    /**
     * Retorna uma lista de todas as oficinas.
     *
     * @return Lista de todas as oficinas.
     */
    List<Oficina> findAll();

    /**
     * Atualiza os dados de uma oficina existente no sistema.
     *
     * @param oficina A instância de Oficina com os dados atualizados.
     * @return A Oficina atualizada.
     * @throws OficinaNotFoundException Se a oficina não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Oficina update(Oficina oficina) throws OficinaNotFoundException, SQLException;

    /**
     * Exclui uma oficina do sistema pelo seu ID.
     *
     * @param id O ID da oficina a ser excluída.
     * @throws OficinaNotFoundException Se a oficina não for encontrada.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws OficinaNotFoundException, SQLException;
}
