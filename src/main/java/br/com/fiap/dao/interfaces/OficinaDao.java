package br.com.fiap.dao.interfaces;

import br.com.fiap.models.Oficina;
import br.com.fiap.exceptions.OficinaNotFoundException;
import br.com.fiap.exceptions.OficinaNotSavedException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Oficina.
 *
 * @version 1.0
 * @since 1.0
 */
public interface OficinaDao {

    /**
     * Busca todas as instâncias de Oficina no banco de dados.
     *
     * @return Lista de instâncias de {@link Oficina}.
     */
    List<Oficina> findAll();

    /**
     * Remove uma Oficina pelo ID no banco de dados.
     *
     * @param id O ID da Oficina a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws OficinaNotFoundException Se a Oficina com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws OficinaNotFoundException, SQLException;

    /**
     * Salva uma nova Oficina no banco de dados.
     *
     * @param oficina A instância de Oficina a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Oficina} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws OficinaNotSavedException Se a Oficina não puder ser salva.
     */
    Oficina save(Oficina oficina, Connection connection) throws SQLException, OficinaNotSavedException;

    /**
     * Atualiza uma Oficina existente no banco de dados.
     *
     * @param oficina A instância de Oficina com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Oficina} atualizada.
     * @throws OficinaNotFoundException Se a Oficina com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Oficina update(Oficina oficina, Connection connection) throws OficinaNotFoundException, SQLException;
}
