package br.com.fiap.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface genérica para operações de CRUD na entidade Telefone.
 * <p>
 * Esta interface define métodos padrão para criar, ler, atualizar e excluir registros de telefone no banco de dados.
 * Os métodos utilizam generics para que a interface possa ser reutilizada com outras entidades, se necessário.
 *
 * @param <T>  O tipo da entidade (exemplo: Telefone).
 * @param <ID> O tipo do identificador único da entidade (exemplo: Long).
 * @since 1.0
 * @version 1.1
 */
public interface TelefoneDao<T, ID> {

    /**
     * Retorna uma lista de todos os registros de telefone no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link T} (Telefone).
     */
    List<T> findAll();

    /**
     * Exclui um registro de telefone no banco de dados com base no identificador fornecido.
     *
     * @param id         O identificador único do registro de telefone a ser excluído.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @throws SQLException Se ocorrer um erro durante a operação de exclusão.
     */
    void deleteById(ID id, Connection connection) throws SQLException;

    /**
     * Salva um novo registro de telefone no banco de dados.
     *
     * @param telefone   A entidade de telefone a ser salva.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link T} (Telefone) salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro durante a operação de inserção.
     */
    T save(T telefone, Connection connection) throws SQLException;

    /**
     * Atualiza os dados de um registro de telefone existente no banco de dados.
     *
     * @param telefone   A entidade de telefone com os dados atualizados.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link T} (Telefone) atualizada.
     * @throws SQLException Se ocorrer um erro durante a operação de atualização.
     */
    T update(T telefone, Connection connection) throws SQLException;
}
