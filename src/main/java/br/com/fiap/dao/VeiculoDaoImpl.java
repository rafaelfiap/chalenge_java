package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.VeiculoDao;
import br.com.fiap.exceptions.VeiculoNotFoundException;
import br.com.fiap.exceptions.VeiculoNotSavedException;
import br.com.fiap.models.Veiculo;

import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Veiculo, gerenciando operações de CRUD.
 */
class VeiculoDaoImpl implements VeiculoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Veiculo no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link Veiculo} encontradas no banco de dados.
     */
    @Override
    public List<Veiculo> findAll() {
        final List<Veiculo> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_VEICULO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Veiculo.
            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getLong("id_veiculo"),
                        rs.getString("placa"),
                        rs.getString("marca"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getString("cor"),
                        rs.getString("combustivel"),
                        rs.getLong("cliente_id")
                );
                all.add(veiculo);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar veículos: " + e.getMessage());
        }
        return all;
    }

    /**
     * Remove um Veiculo pelo ID no banco de dados.
     *
     * @param id O ID do Veiculo a ser removido.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @throws VeiculoNotFoundException Se o Veiculo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws VeiculoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_VEICULO WHERE id_veiculo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Veiculo não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new VeiculoNotFoundException("Veiculo com ID " + id + " não encontrado.");
        }
    }

    /**
     * Salva um novo Veiculo no banco de dados.
     *
     * @param veiculo A instância de Veiculo a ser salva.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Veiculo} que foi salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws VeiculoNotSavedException Se o Veiculo não puder ser salvo por algum motivo.
     */
    @Override
    public Veiculo save(Veiculo veiculo, Connection connection) throws SQLException, VeiculoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_VEICULO(placa, marca, modelo, ano, cor, combustivel, cliente_id) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_veiculo INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, veiculo.getPlaca());
        call.setString(2, veiculo.getMarca());
        call.setString(3, veiculo.getModelo());
        call.setInt(4, veiculo.getAno());
        call.setString(5, veiculo.getCor());
        call.setString(6, veiculo.getCombustivel());
        call.setLong(7, veiculo.getClienteId());
        call.registerOutParameter(8, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(8);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new VeiculoNotSavedException("Erro ao salvar o veículo.");
        }

        veiculo.setIdVeiculo(id);
        return veiculo;
    }

    /**
     * Atualiza um Veiculo existente no banco de dados.
     *
     * @param veiculo A instância de Veiculo com os dados atualizados.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Veiculo} atualizada.
     * @throws VeiculoNotFoundException Se o Veiculo com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Veiculo update(Veiculo veiculo, Connection connection) throws VeiculoNotFoundException, SQLException {
        final String sql = "UPDATE T_VEICULO SET placa = ?, marca = ?, modelo = ?, ano = ?, cor = ?, combustivel = ?, cliente_id = ? WHERE id_veiculo = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, veiculo.getPlaca());
        stmt.setString(2, veiculo.getMarca());
        stmt.setString(3, veiculo.getModelo());
        stmt.setInt(4, veiculo.getAno());
        stmt.setString(5, veiculo.getCor());
        stmt.setString(6, veiculo.getCombustivel());
        stmt.setLong(7, veiculo.getClienteId());
        stmt.setLong(8, veiculo.getIdVeiculo());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Veiculo não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new VeiculoNotFoundException("Veiculo com ID " + veiculo.getIdVeiculo() + " não encontrado.");
        }
        return veiculo;
    }
}
