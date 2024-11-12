package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.AgendamentoDao;
import br.com.fiap.exceptions.AgendamentoNotFoundException;
import br.com.fiap.exceptions.AgendamentoNotSavedException;
import br.com.fiap.models.Agendamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Agendamento, gerenciando operações de CRUD.
 */
class AgendamentoDaoImpl implements AgendamentoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Agendamento no banco de dados.
     *
     * @return Lista de instâncias de {@link Agendamento} encontradas no banco de dados.
     */
    @Override
    public List<Agendamento> findAll() {
        final List<Agendamento> agendamentos = new ArrayList<>();
        final String sql = "SELECT * FROM T_AGENDAMENTO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Agendamento.
            while (rs.next()) {
                Agendamento agendamento = new Agendamento(
                        rs.getLong("id_agendamento"),
                        rs.getDate("dt_agendamento"),
                        rs.getTimestamp("hr_agendamento"),
                        rs.getLong("id_cliente"),
                        rs.getLong("id_oficina")
                );
                agendamentos.add(agendamento);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar agendamentos: " + e.getMessage());
        }
        return agendamentos;
    }

    /**
     * Remove um Agendamento pelo ID no banco de dados.
     *
     * @param id O ID do Agendamento a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws AgendamentoNotFoundException Se o Agendamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws AgendamentoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_AGENDAMENTO WHERE id_agendamento = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Agendamento não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new AgendamentoNotFoundException("Agendamento não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo Agendamento no banco de dados.
     *
     * @param agendamento A instância de Agendamento a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O Agendamento salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws AgendamentoNotSavedException Se o Agendamento não puder ser salvo.
     */
    @Override
    public Agendamento save(Agendamento agendamento, Connection connection) throws SQLException, AgendamentoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_AGENDAMENTO(dt_agendamento, hr_agendamento, id_cliente, id_oficina) VALUES (?, ?, ?, ?) RETURNING id_agendamento INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setDate(1, new java.sql.Date(agendamento.getDataAgendamento().getTime()));
        call.setTimestamp(2, agendamento.getHoraAgendamento());
        call.setLong(3, agendamento.getIdCliente());
        call.setLong(4, agendamento.getIdOficina());
        call.registerOutParameter(5, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new AgendamentoNotSavedException("Erro ao salvar o agendamento: verifique os dados fornecidos ou tente novamente.");
        }

        agendamento.setIdAgendamento(id);
        return agendamento;
    }

    /**
     * Atualiza um Agendamento existente no banco de dados.
     *
     * @param agendamento A instância de Agendamento com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O Agendamento atualizado.
     * @throws AgendamentoNotFoundException Se o Agendamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Agendamento update(Agendamento agendamento, Connection connection) throws AgendamentoNotFoundException, SQLException {
        final String sql = "UPDATE T_AGENDAMENTO SET dt_agendamento = ?, hr_agendamento = ?, id_cliente = ?, id_oficina = ? WHERE id_agendamento = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setDate(1, new java.sql.Date(agendamento.getDataAgendamento().getTime()));
        stmt.setTimestamp(2, agendamento.getHoraAgendamento());
        stmt.setLong(3, agendamento.getIdCliente());
        stmt.setLong(4, agendamento.getIdOficina());
        stmt.setLong(5, agendamento.getIdAgendamento());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Agendamento não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new AgendamentoNotFoundException("Agendamento não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return agendamento;
    }
}
