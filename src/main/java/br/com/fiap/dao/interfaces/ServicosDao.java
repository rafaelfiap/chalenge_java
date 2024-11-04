package br.com.fiap.dao.interfaces;

import br.com.fiap.exceptions.ServicosNotFoundException;
import br.com.fiap.exceptions.ServicosNotSavedException;
import br.com.fiap.models.Servicos;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Interface que define as operações CRUD para a entidade Servicos.
 *
 * <p>Esta interface fornece métodos para criação, leitura, atualização e exclusão de serviços
 * no sistema, incluindo operações para buscar todos os serviços, salvar um novo serviço,
 * atualizar um serviço existente e deletar pelo ID.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public interface ServicosDao {

    /**
     * Busca todas as instâncias de Servicos no banco de dados.
     *
     * @return Lista de instâncias de {@link Servicos}.
     */
    List<Servicos> findAll() ;

    /**
     * Remove um Servicos pelo ID no banco de dados.
     *
     * @param id O ID do Servicos a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws ServicosNotFoundException Se o Servicos com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    void deleteById(Long id, Connection connection) throws ServicosNotFoundException, SQLException;

    /**
     * Salva um novo Servicos no banco de dados.
     *
     * @param servicos A instância de Servicos a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Servicos} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws ServicosNotSavedException Se o Servicos não puder ser salvo.
     */
    Servicos save(Servicos servicos, Connection connection) throws SQLException, ServicosNotSavedException;

    /**
     * Atualiza um Servicos existente no banco de dados.
     *
     * @param servicos A instância de Servicos com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Servicos} atualizada.
     * @throws ServicosNotFoundException Se o Servicos com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    Servicos update(Servicos servicos, Connection connection) throws ServicosNotFoundException, SQLException;
}
