package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.TelefoneNotFoundException;
import br.com.fiap.exceptions.TelefoneNotSavedException;
import br.com.fiap.models.Telefone;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Telefone.
 * Define métodos para criar, atualizar, excluir e buscar telefones.
 *
 * @since 1.0
 */
public interface TelefoneService {

    /**
     * Cria um novo telefone no sistema.
     *
     * @param telefone A instância de Telefone a ser criada.
     * @return O Telefone criado com o ID gerado.
     * @throws TelefoneNotSavedException Se o telefone não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Telefone create(Telefone telefone) throws SQLException, TelefoneNotSavedException;

    /**
     * Retorna uma lista de todos os telefones.
     *
     * @return Lista de todos os telefones.
     */
    List<Telefone> findAll();

    /**
     * Atualiza os dados de um telefone existente no sistema.
     *
     * @param telefone A instância de Telefone com os dados atualizados.
     * @return O Telefone atualizado.
     * @throws TelefoneNotFoundException Se o telefone não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Telefone update(Telefone telefone) throws TelefoneNotFoundException, SQLException;

    /**
     * Exclui um telefone do sistema pelo seu ID.
     *
     * @param id O ID do telefone a ser excluído.
     * @throws TelefoneNotFoundException Se o telefone não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws TelefoneNotFoundException, SQLException;
}
