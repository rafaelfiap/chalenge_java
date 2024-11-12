package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.TelefoneDao;
import br.com.fiap.exceptions.TelefoneNotFoundException;
import br.com.fiap.exceptions.TelefoneNotSavedException;
import br.com.fiap.models.Telefone;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade TelefoneOficina, gerenciando operações de CRUD.
 */
class TelefoneOficinaDaoImpl implements TelefoneDao<Telefone, Long> {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de TelefoneOficina no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link Telefone} encontradas no banco de dados.
     */
    @Override
    public List<Telefone> findAll() {
        final List<Telefone> telefones = new ArrayList<>();
        final String sql = "SELECT * FROM T_TELEFONE_OFICINA";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Telefone.
            while (rs.next()) {
                Telefone telefone = new Telefone(
                        rs.getLong("id_telefone_oficina"),
                        rs.getString("nr_telefone"),
                        rs.getString("tp_telefone"),
                        rs.getInt("id_oficina")
                );
                telefones.add(telefone);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar telefones de oficinas: " + e.getMessage());
        }
        return telefones;
    }

    /**
     * Remove um TelefoneOficina pelo ID no banco de dados.
     *
     * @param id O ID do TelefoneOficina a ser removido.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @throws TelefoneNotFoundException Se o TelefoneOficina com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws TelefoneNotFoundException, SQLException {
        final String sql = "DELETE FROM T_TELEFONE_OFICINA WHERE id_telefone_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Telefone não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new TelefoneNotFoundException("Telefone de oficina com ID " + id + " não encontrado.");
        }
    }

    /**
     * Salva um novo TelefoneOficina no banco de dados.
     *
     * @param telefone A instância de Telefone a ser salva.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Telefone} que foi salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws TelefoneNotSavedException Se o Telefone não puder ser salvo por algum motivo.
     */
    @Override
    public Telefone save(Telefone telefone, Connection connection) throws SQLException, TelefoneNotSavedException {
        final String sql = "BEGIN INSERT INTO T_TELEFONE_OFICINA(nr_telefone, tp_telefone, id_oficina) VALUES (?, ?, ?) RETURNING id_telefone_oficina INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, telefone.getNumero());
        call.setString(2, telefone.getTipo());
        call.setInt(3, telefone.getIdReferencia()); // ID de referência para oficina
        call.registerOutParameter(4, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(4);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new TelefoneNotSavedException("Erro ao salvar o telefone de oficina.");
        }

        telefone.setIdTelefone(id);
        return telefone;
    }

    /**
     * Atualiza um TelefoneOficina existente no banco de dados.
     *
     * @param telefone A instância de Telefone com os dados atualizados.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Telefone} atualizada.
     * @throws TelefoneNotFoundException Se o TelefoneOficina com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Telefone update(Telefone telefone, Connection connection) throws TelefoneNotFoundException, SQLException {
        final String sql = "UPDATE T_TELEFONE_OFICINA SET nr_telefone = ?, tp_telefone = ?, id_oficina = ? WHERE id_telefone_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, telefone.getNumero());
        stmt.setString(2, telefone.getTipo());
        stmt.setInt(3, telefone.getIdReferencia()); // ID de referência para oficina
        stmt.setLong(4, telefone.getIdTelefone());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Telefone não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new TelefoneNotFoundException("Telefone de oficina com ID " + telefone.getIdTelefone() + " não encontrado.");
        }
        return telefone;
    }
}
