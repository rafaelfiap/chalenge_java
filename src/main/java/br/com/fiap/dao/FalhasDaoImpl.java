package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.FalhasDao;
import br.com.fiap.exceptions.FalhasNotFoundException;
import br.com.fiap.exceptions.FalhasNotSavedException;
import br.com.fiap.models.Falhas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Falhas, gerenciando operações de CRUD.
 */
class FalhasDaoImpl implements FalhasDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private static final Logger logger = Logger.getLogger(FalhasDaoImpl.class.getName());

    /**
     * Busca todas as instâncias de Falhas no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link Falhas} encontradas no banco de dados.
     */
    @Override
    public List<Falhas> findAll() {
        final List<Falhas> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_FALHAS";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Falhas.
            while (rs.next()) {
                Falhas falha = new Falhas(
                        rs.getLong("id_falha"),
                        rs.getString("ds_falha"),
                        rs.getString("ds_solucao"),
                        rs.getLong("id_orcamento"),
                        rs.getLong("id_veiculo"),
                        rs.getString("st_gravidade")
                );
                all.add(falha);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar falhas: " + e.getMessage());
        }
        return all;
    }

    /**
     * Remove uma Falha pelo ID no banco de dados.
     *
     * @param id O ID da Falha a ser removida.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @throws FalhasNotFoundException Se a Falha com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws FalhasNotFoundException, SQLException {
        final String sql = "DELETE FROM T_FALHAS WHERE id_falha = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Falha não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new FalhasNotFoundException("Falha não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva uma nova Falha no banco de dados.
     *
     * @param falha A instância de Falhas a ser salva.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Falhas} que foi salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws FalhasNotSavedException Se a Falha não puder ser salva.
     */
    @Override
    public Falhas save(Falhas falha, Connection connection) throws SQLException, FalhasNotSavedException {
        final String sql = "BEGIN INSERT INTO T_FALHAS(ds_falha, ds_solucao, id_orcamento, id_veiculo, st_gravidade) VALUES(?, ?, ?, ?, ?) RETURNING id_falha INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, falha.getDescricaoFalha());
        call.setString(2, falha.getDescricaoSolucao());
        call.setLong(3, falha.getIdOrcamento());
        call.setLong(4, falha.getIdVeiculo());
        call.setString(5, falha.getGravidade());
        call.registerOutParameter(6, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new FalhasNotSavedException("Erro ao salvar a Falha: verifique os dados fornecidos ou tente novamente.");
        }

        falha.setIdFalha(id);
        return falha;
    }

    /**
     * Atualiza uma Falha existente no banco de dados.
     *
     * @param falha A instância de Falhas com os dados atualizados.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Falhas} atualizada.
     * @throws FalhasNotFoundException Se a Falha com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Falhas update(Falhas falha, Connection connection) throws FalhasNotFoundException, SQLException {
        final String sql = "UPDATE T_FALHAS SET ds_falha = ?, ds_solucao = ?, id_orcamento = ?, id_veiculo = ?, st_gravidade = ? WHERE id_falha = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, falha.getDescricaoFalha());
        stmt.setString(2, falha.getDescricaoSolucao());
        stmt.setLong(3, falha.getIdOrcamento());
        stmt.setLong(4, falha.getIdVeiculo());
        stmt.setString(5, falha.getGravidade());
        stmt.setLong(6, falha.getIdFalha());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Falha não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new FalhasNotFoundException("Falha não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
        return falha;
    }
}
