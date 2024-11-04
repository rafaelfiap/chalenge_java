package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.ServicosNotFoundException;
import br.com.fiap.exceptions.ServicosNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Servicos;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Servicos.
 * Define métodos para criar, atualizar, excluir e buscar serviços.
 *
 * @since 1.0
 */
public interface ServicosService {

    /**
     * Cria um novo serviço no sistema.
     *
     * @param servico A instância de Servicos a ser criada.
     * @return O Servico criado com o ID gerado.
     * @throws ServicosNotSavedException Se o serviço não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Servicos create(Servicos servico) throws UnsupportedServiceOperationException, SQLException, ServicosNotSavedException;

    /**
     * Retorna uma lista de todos os serviços.
     *
     * @return Lista de todos os serviços.
     */
    List<Servicos> findAll();

    /**
     * Atualiza os dados de um serviço existente no sistema.
     *
     * @param servico A instância de Servicos com os dados atualizados.
     * @return O Servico atualizado.
     * @throws ServicosNotFoundException Se o serviço não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Servicos update(Servicos servico) throws ServicosNotFoundException, SQLException;

    /**
     * Exclui um serviço do sistema pelo seu ID.
     *
     * @param id O ID do serviço a ser excluído.
     * @throws ServicosNotFoundException Se o serviço não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws ServicosNotFoundException, SQLException;
}
