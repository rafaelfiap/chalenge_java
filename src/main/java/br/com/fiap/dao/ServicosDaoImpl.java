package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.ServicosDao;
import br.com.fiap.exceptions.ServicosNotFoundException;
import br.com.fiap.exceptions.ServicosNotSavedException;
import br.com.fiap.models.Servicos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Servicos, gerenciando operações de CRUD.
 */
class ServicosDaoImpl implements ServicosDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private static final Logger logger = Logger.getLogger(ServicosDaoImpl.class.getName());

    /**
     * Busca todas as instâncias de Servicos no banco de dados.
     *
     * @return Lista de instâncias de {@link Servicos} encontradas no banco de dados.
     */
    @Override
    public List<Servicos> findAll() {
        final List<Servicos> servicosList = new ArrayList<>();
        final String sql = "SELECT * FROM T_SERVICOS";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Servicos.
            while (rs.next()) {
                Servicos servico = new Servicos(
                        rs.getLong("id_servico"),
                        rs.getString("st_tipo_servico"),
                        rs.getString("ds_servico"),
                        rs.getDouble("vl_custo"),
                        rs.getTimestamp("hr_tempo_estimado"),
                        rs.getLong("id_orcamento")
                );
                servicosList.add(servico);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar serviços: " + e.getMessage());
        }
        return servicosList;
    }

    /**
     * Remove um Serviço pelo ID no banco de dados.
     *
     * @param id O ID do Serviço a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws ServicosNotFoundException Se o Serviço com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws ServicosNotFoundException, SQLException {
        final String sql = "DELETE FROM T_SERVICOS WHERE id_servico = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Serviço não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new ServicosNotFoundException("Serviço não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo Serviço no banco de dados.
     *
     * @param servico A instância de Servicos a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O Serviço salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws ServicosNotSavedException Se o Serviço não puder ser salvo.
     */
    @Override
    public Servicos save(Servicos servico, Connection connection) throws SQLException, ServicosNotSavedException {
        final String sql = "BEGIN INSERT INTO T_SERVICOS(st_tipo_servico, ds_servico, hr_tempo_estimado, vl_custo, id_orcamento) VALUES(?, ?, ?, ?, ?) RETURNING id_servico INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, servico.getTipoServico());
        call.setString(2, servico.getDescricao());
        call.setTimestamp(3, servico.getTempoEstimado());
        call.setDouble(4, servico.getValorServico());
        call.setLong(5, servico.getIdOrcamento());
        call.registerOutParameter(6, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new ServicosNotSavedException("Erro ao salvar o serviço: verifique os dados fornecidos ou tente novamente.");
        }

        servico.setIdServico(id);
        return servico;
    }

    /**
     * Atualiza um Serviço existente no banco de dados.
     *
     * @param servico A instância de Servicos com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O Serviço atualizado.
     * @throws ServicosNotFoundException Se o Serviço com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Servicos update(Servicos servico, Connection connection) throws ServicosNotFoundException, SQLException {
        final String sql = "UPDATE T_SERVICOS SET st_tipo_servico = ?, ds_servico = ?, hr_tempo_estimado = ?, vl_custo = ?, id_orcamento = ? WHERE id_servico = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, servico.getTipoServico());
        stmt.setString(2, servico.getDescricao());
        stmt.setTimestamp(3, servico.getTempoEstimado());
        stmt.setDouble(4, servico.getValorServico());
        stmt.setLong(5, servico.getIdOrcamento());
        stmt.setLong(6, servico.getIdServico());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Serviço não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new ServicosNotFoundException("Serviço não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return servico;
    }
}
