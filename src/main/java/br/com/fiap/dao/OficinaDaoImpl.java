package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.OficinaDao;
import br.com.fiap.exceptions.OficinaNotFoundException;
import br.com.fiap.exceptions.OficinaNotSavedException;
import br.com.fiap.models.Oficina;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Oficina, gerenciando operações de CRUD.
 */
class OficinaDaoImpl implements OficinaDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Oficina no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link Oficina} encontradas no banco de dados.
     */
    @Override
    public List<Oficina> findAll() {
        final List<Oficina> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_OFICINA";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Oficina.
            while (rs.next()) {
                Oficina oficina = new Oficina(
                        rs.getLong("id_oficina"),
                        rs.getString("nr_cnpj"),
                        rs.getString("nm_oficina"),
                        rs.getString("ds_email")
                );
                all.add(oficina);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar oficinas: " + e.getMessage());
        }
        return all;
    }

    /**
     * Remove uma Oficina pelo ID no banco de dados.
     *
     * @param id O ID da Oficina a ser removida.
     * @param connection A conexão com o banco de dados.
     * @throws OficinaNotFoundException Se a Oficina com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws OficinaNotFoundException, SQLException {
        final String sql = "DELETE FROM T_OFICINA WHERE id_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Oficina não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new OficinaNotFoundException("Oficina não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva uma nova Oficina no banco de dados.
     *
     * @param oficina A instância de Oficina a ser salva.
     * @param connection A conexão com o banco de dados.
     * @return A instância de {@link Oficina} que foi salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws OficinaNotSavedException Se a Oficina não puder ser salva.
     */
    @Override
    public Oficina save(Oficina oficina, Connection connection) throws SQLException, OficinaNotSavedException {
        final String sql = "BEGIN INSERT INTO T_OFICINA (nr_cnpj, nm_oficina, ds_email) VALUES (?, ?, ?) RETURNING id_oficina INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, oficina.getCnpj());
        call.setString(2, oficina.getNome());
        call.setString(3, oficina.getEmail());
        call.registerOutParameter(4, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(4);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new OficinaNotSavedException("Erro ao salvar a oficina: verifique os dados fornecidos ou tente novamente.");
        }

        oficina.setIdOficina(id);
        return oficina;
    }

    /**
     * Atualiza uma Oficina existente no banco de dados.
     *
     * @param oficina A instância de Oficina com os dados atualizados.
     * @param connection A conexão com o banco de dados.
     * @return A instância de {@link Oficina} atualizada.
     * @throws OficinaNotFoundException Se a Oficina com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Oficina update(Oficina oficina, Connection connection) throws OficinaNotFoundException, SQLException {
        final String sql = "UPDATE T_OFICINA SET nr_cnpj = ?, nm_oficina = ?, ds_email = ? WHERE id_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, oficina.getCnpj());
        stmt.setString(2, oficina.getNome());
        stmt.setString(3, oficina.getEmail());
        stmt.setLong(4, oficina.getIdOficina());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Oficina não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new OficinaNotFoundException("Oficina não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
        return oficina;
    }
}
