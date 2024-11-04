package br.com.fiap.services.interfaces;

import br.com.fiap.exceptions.VeiculoNotFoundException;
import br.com.fiap.exceptions.VeiculoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Veiculo;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface para operações de serviço relacionadas à entidade Veiculo.
 * Define métodos para criar, atualizar, excluir e buscar veículos.
 *
 * @since 1.0
 */
public interface VeiculoService {

    /**
     * Cria um novo veículo no sistema.
     *
     * @param veiculo A instância de Veiculo a ser criada.
     * @return O Veiculo criado com o ID gerado.
     * @throws VeiculoNotSavedException Se o veículo não puder ser salvo.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Veiculo create(Veiculo veiculo) throws UnsupportedServiceOperationException, SQLException, VeiculoNotSavedException;

    /**
     * Retorna uma lista de todos os veículos.
     *
     * @return Lista de todos os veículos.
     */
    List<Veiculo> findAll();

    /**
     * Atualiza os dados de um veículo existente no sistema.
     *
     * @param veiculo A instância de Veiculo com os dados atualizados.
     * @return O Veiculo atualizado.
     * @throws VeiculoNotFoundException Se o veículo não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    Veiculo update(Veiculo veiculo) throws VeiculoNotFoundException, SQLException;

    /**
     * Exclui um veículo do sistema pelo seu ID.
     *
     * @param id O ID do veículo a ser excluído.
     * @throws VeiculoNotFoundException Se o veículo não for encontrado.
     * @throws SQLException Se ocorrer um erro de SQL.
     */
    void deleteById(Long id) throws VeiculoNotFoundException, SQLException;
}
