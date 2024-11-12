package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.OrcamentoDao;
import br.com.fiap.exceptions.OrcamentoNotFoundException;
import br.com.fiap.exceptions.OrcamentoNotSavedException;
import br.com.fiap.models.Orcamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Orcamento, gerenciando operações de CRUD.
 */
class OrcamentoDaoImpl implements OrcamentoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private static final Logger logger = Logger.getLogger(OrcamentoDaoImpl.class.getName());

    /**
     * Busca todas as instâncias de Orcamento no banco de dados.
     *
     * @return Lista de instâncias de {@link Orcamento} encontradas no banco de dados.
     */
    @Override
    public List<Orcamento> findAll() {
        final List<Orcamento> orcamentos = new ArrayList<>();
        final String sql = "SELECT * FROM T_ORCAMENTO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Orcamento.
            while (rs.next()) {
                Orcamento orcamento = new Orcamento(
                        rs.getLong("id_orcamento"),
                        rs.getDouble("vl_orcamento"),
                        rs.getString("st_situacao"),
                        rs.getInt("id_veiculo"),
                        rs.getInt("id_oficina"),
                        rs.getInt("id_servico"),
                        rs.getInt("id_peca")
                );
                orcamentos.add(orcamento);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar orçamentos: " + e.getMessage());
        }
        return orcamentos;
    }

    /**
     * Remove um Orcamento pelo ID no banco de dados.
     *
     * @param id O ID do Orcamento a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws OrcamentoNotFoundException Se o Orcamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws OrcamentoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_ORCAMENTO WHERE id_orcamento = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Orçamento não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new OrcamentoNotFoundException("Orçamento não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo Orcamento no banco de dados.
     *
     * @param orcamento A instância de Orcamento a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Orcamento} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws OrcamentoNotSavedException Se o Orcamento não puder ser salvo.
     */
    @Override
    public Orcamento save(Orcamento orcamento, Connection connection) throws SQLException, OrcamentoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_ORCAMENTO(vl_orcamento, st_situacao, id_veiculo, id_oficina, id_servico, id_peca) VALUES(?, ?, ?, ?, ?, ?) RETURNING id_orcamento INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setDouble(1, orcamento.getValorOrcamento());
        call.setString(2, orcamento.getSituacao());
        call.setInt(3, orcamento.getIdVeiculo());
        call.setInt(4, orcamento.getIdOficina());
        call.setInt(5, orcamento.getIdServico());
        call.setInt(6, orcamento.getIdPeca());
        call.registerOutParameter(7, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(7);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new OrcamentoNotSavedException("Erro ao salvar o orçamento: verifique os dados fornecidos ou tente novamente.");
        }

        orcamento.setIdOrcamento(id);
        return orcamento;
    }

    /**
     * Atualiza um Orcamento existente no banco de dados.
     *
     * @param orcamento A instância de Orcamento com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Orcamento} atualizada.
     * @throws OrcamentoNotFoundException Se o Orcamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Orcamento update(Orcamento orcamento, Connection connection) throws OrcamentoNotFoundException, SQLException {
        final String sql = "UPDATE T_ORCAMENTO SET vl_orcamento = ?, st_situacao = ?, id_veiculo = ?, id_oficina = ?, id_servico = ?, id_peca = ? WHERE id_orcamento = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDouble(1, orcamento.getValorOrcamento());
        stmt.setString(2, orcamento.getSituacao());
        stmt.setInt(3, orcamento.getIdVeiculo());
        stmt.setInt(4, orcamento.getIdOficina());
        stmt.setInt(5, orcamento.getIdServico());
        stmt.setInt(6, orcamento.getIdPeca());
        stmt.setLong(7, orcamento.getIdOrcamento());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Orçamento não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new OrcamentoNotFoundException("Orçamento não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return orcamento;
    }
}
