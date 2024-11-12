package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.FuncionarioDao;
import br.com.fiap.exceptions.FuncionarioNotFoundException;
import br.com.fiap.exceptions.FuncionarioNotSavedException;
import br.com.fiap.models.Funcionario;

import oracle.jdbc.OracleType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Funcionario, gerenciando operações de CRUD.
 */
class FuncionarioDaoImpl implements FuncionarioDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Funcionario no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link Funcionario} encontradas no banco de dados.
     */
    @Override
    public List<Funcionario> findAll() {
        final List<Funcionario> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_FUNCIONARIO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Funcionario.
            while (rs.next()) {
                Funcionario funcionario = new Funcionario(
                        rs.getLong("id_funcionario"),
                        rs.getString("nr_cpf"),
                        rs.getString("nm_funcionario"),
                        rs.getString("sx_sexo").charAt(0),
                        rs.getString("ds_funcao"),
                        rs.getInt("id_oficina")
                );
                all.add(funcionario);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar funcionários: " + e.getMessage());
        }
        return all;
    }

    /**
     * Remove um Funcionario pelo ID no banco de dados.
     *
     * @param id O ID do Funcionario a ser removido.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @throws FuncionarioNotFoundException Se o Funcionario com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws FuncionarioNotFoundException, SQLException {
        final String sql = "DELETE FROM T_FUNCIONARIO WHERE id_funcionario = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Funcionario não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new FuncionarioNotFoundException("Funcionário não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo Funcionario no banco de dados.
     *
     * @param funcionario A instância de Funcionario a ser salva.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Funcionario} que foi salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws FuncionarioNotSavedException Se o Funcionario não puder ser salvo por algum motivo.
     */
    @Override
    public Funcionario save(Funcionario funcionario, Connection connection) throws SQLException, FuncionarioNotSavedException {
        final String sql = "BEGIN INSERT INTO T_FUNCIONARIO(nr_cpf, nm_funcionario, sx_sexo, ds_funcao, id_oficina) VALUES (?, ?, ?, ?, ?) RETURNING id_funcionario INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, funcionario.getCpf());
        call.setString(2, funcionario.getNome());
        call.setString(3, String.valueOf(funcionario.getSexo()));
        call.setString(4, funcionario.getFuncao());
        call.setInt(5, funcionario.getIdOficina());
        call.registerOutParameter(6, OracleType.NUMBER);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(6);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new FuncionarioNotSavedException();
        }

        funcionario.setIdFuncionario(id);
        return funcionario;
    }

    /**
     * Atualiza um Funcionario existente no banco de dados.
     *
     * @param funcionario A instância de Funcionario com os dados atualizados.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Funcionario} atualizada.
     * @throws FuncionarioNotFoundException Se o Funcionario com o ‘ID’ fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Funcionario update(Funcionario funcionario, Connection connection) throws FuncionarioNotFoundException, SQLException {
        final String sql = "UPDATE T_FUNCIONARIO SET nr_cpf = ?, nm_funcionario = ?, sx_sexo = ?, ds_funcao = ?, id_oficina = ? WHERE id_funcionario = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, funcionario.getCpf());
        stmt.setString(2, funcionario.getNome());
        stmt.setString(3, String.valueOf(funcionario.getSexo()));
        stmt.setString(4, funcionario.getFuncao());
        stmt.setInt(5, funcionario.getIdOficina());
        stmt.setLong(6, funcionario.getIdFuncionario());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Funcionario não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new FuncionarioNotFoundException("Funcionário não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return funcionario;
    }

}
