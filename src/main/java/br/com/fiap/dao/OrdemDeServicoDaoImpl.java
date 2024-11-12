package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.OrdemDeServicoDao;
import br.com.fiap.models.OrdemDeServico;
import br.com.fiap.exceptions.OrdemDeServicoNotFoundException;
import br.com.fiap.exceptions.OrdemDeServicoNotSavedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade OrdemDeServico, gerenciando operações de CRUD.
 */
class OrdemDeServicoDaoImpl implements OrdemDeServicoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private static final Logger logger = Logger.getLogger(OrdemDeServicoDaoImpl.class.getName());

    /**
     * Busca todas as instâncias de OrdemDeServico no banco de dados.
     *
     * @return Lista de instâncias de {@link OrdemDeServico} encontradas no banco de dados.
     */
    @Override
    public List<OrdemDeServico> findAll() {
        final List<OrdemDeServico> ordens = new ArrayList<>();
        final String sql = "SELECT * FROM T_ORDEM_DE_SERVICO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de OrdemDeServico.
            while (rs.next()) {
                OrdemDeServico ordem = new OrdemDeServico(
                        rs.getLong("id_os"),
                        rs.getString("st_status"),
                        rs.getLong("id_orcamento"),
                        rs.getLong("id_funcionario"),
                        rs.getLong("id_veiculo"),
                        rs.getDate("dt_inicio"),
                        rs.getDate("dt_fim"),
                        rs.getTimestamp("hr_inicio"),
                        rs.getTimestamp("hr_fim")
                );
                ordens.add(ordem);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar ordens de serviço: " + e.getMessage());
        }
        return ordens;
    }

    /**
     * Remove uma Ordem de Serviço pelo ID no banco de dados.
     *
     * @param id O ID da Ordem de Serviço a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws OrdemDeServicoNotFoundException Se a Ordem de Serviço com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws OrdemDeServicoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_ORDEM_DE_SERVICO WHERE id_os = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Ordem de Serviço não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new OrdemDeServicoNotFoundException("Ordem de Serviço não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva uma nova Ordem de Serviço no banco de dados.
     *
     * @param ordem A instância de OrdemDeServico a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A Ordem de Serviço salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws OrdemDeServicoNotSavedException Se a Ordem de Serviço não puder ser salva.
     */
    @Override
    public OrdemDeServico save(OrdemDeServico ordem, Connection connection) throws SQLException, OrdemDeServicoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_ORDEM_DE_SERVICO(st_status, id_orcamento, id_funcionario, id_veiculo, dt_inicio, dt_fim, hr_inicio, hr_fim) VALUES(?, ?, ?, ?, ?, ?, ?, ?) RETURNING id_os INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, ordem.getStatus());
        call.setLong(2, ordem.getIdOrcamento());
        call.setLong(3, ordem.getIdFuncionario());
        call.setLong(4, ordem.getIdVeiculo());
        call.setDate(5, ordem.getDataInicio());
        call.setDate(6, ordem.getDataFim());
        call.setTimestamp(7, ordem.getHoraInicio());
        call.setTimestamp(8, ordem.getHoraFim());
        call.registerOutParameter(9, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(9);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new OrdemDeServicoNotSavedException("Erro ao salvar a ordem de serviço: verifique os dados fornecidos ou tente novamente.");
        }

        ordem.setIdOs(id);
        return ordem;
    }

    /**
     * Atualiza uma Ordem de Serviço existente no banco de dados.
     *
     * @param ordem A instância de OrdemDeServico com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A Ordem de Serviço atualizada.
     * @throws OrdemDeServicoNotFoundException Se a Ordem de Serviço com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public OrdemDeServico update(OrdemDeServico ordem, Connection connection) throws OrdemDeServicoNotFoundException, SQLException {
        final String sql = "UPDATE T_ORDEM_DE_SERVICO SET st_status = ?, id_orcamento = ?, id_funcionario = ?, id_veiculo = ?, dt_inicio = ?, dt_fim = ?, hr_inicio = ?, hr_fim = ? WHERE id_os = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, ordem.getStatus());
        stmt.setLong(2, ordem.getIdOrcamento());
        stmt.setLong(3, ordem.getIdFuncionario());
        stmt.setLong(4, ordem.getIdVeiculo());
        stmt.setDate(5, ordem.getDataInicio());
        stmt.setDate(6, ordem.getDataFim());
        stmt.setTimestamp(7, ordem.getHoraInicio());
        stmt.setTimestamp(8, ordem.getHoraFim());
        stmt.setLong(9, ordem.getIdOs());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Ordem de Serviço não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new OrdemDeServicoNotFoundException("Ordem de Serviço não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
        return ordem;
    }
}
