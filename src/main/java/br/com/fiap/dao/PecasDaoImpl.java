package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.PecasDao;
import br.com.fiap.exceptions.PecasNotFoundException;
import br.com.fiap.exceptions.PecasNotSavedException;
import br.com.fiap.models.Pecas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Pecas, gerenciando operações de CRUD.
 */
class PecasDaoImpl implements PecasDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private static final Logger logger = Logger.getLogger(PecasDaoImpl.class.getName());

    /**
     * Busca todas as instâncias de Pecas no banco de dados.
     *
     * @return Lista de instâncias de {@link Pecas} encontradas no banco de dados.
     */
    @Override
    public List<Pecas> findAll() {
        final List<Pecas> pecasList = new ArrayList<>();
        final String sql = "SELECT * FROM T_PECAS";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Pecas.
            while (rs.next()) {
                Pecas peca = new Pecas(
                        rs.getLong("id_peca"),
                        rs.getString("nm_marca"),
                        rs.getInt("qt_quantidade"),
                        rs.getDouble("vl_valor"),
                        rs.getString("ds_descricao"),
                        rs.getLong("id_orcamento"),
                        rs.getLong("id_servico")
                );
                pecasList.add(peca);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar peças: " + e.getMessage());
        }
        return pecasList;
    }

    /**
     * Remove uma Peça pelo ID no banco de dados.
     *
     * @param id O ID da Peça a ser removida.
     * @param connection Conexão com o banco de dados.
     * @throws PecasNotFoundException Se a Peça com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws PecasNotFoundException, SQLException {
        final String sql = "DELETE FROM T_PECAS WHERE id_peca = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Peça não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new PecasNotFoundException("Peça não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva uma nova Peça no banco de dados.
     *
     * @param peca A instância de Pecas a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A Peça salva com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws PecasNotSavedException Se a Peça não puder ser salva.
     */
    @Override
    public Pecas save(Pecas peca, Connection connection) throws SQLException, PecasNotSavedException {
        final String sql = "BEGIN INSERT INTO T_PECAS(nm_marca, qt_quantidade, vl_valor, ds_descricao, id_orcamento, id_servico) VALUES(?, ?, ?, ?, ?, ?) RETURNING id_peca INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, peca.getMarca());
        call.setInt(2, peca.getQuantidade());
        call.setDouble(3, peca.getValor());
        call.setString(4, peca.getDescricao());
        call.setLong(5, peca.getIdOrcamento());
        call.setLong(6, peca.getIdServico());
        call.registerOutParameter(7, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(7);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new PecasNotSavedException("Erro ao salvar a peça: verifique os dados fornecidos ou tente novamente.");
        }

        peca.setIdPeca(id);
        return peca;
    }

    /**
     * Atualiza uma Peça existente no banco de dados.
     *
     * @param peca A instância de Pecas com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A Peça atualizada.
     * @throws PecasNotFoundException Se a Peça com o ID fornecido não for encontrada.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Pecas update(Pecas peca, Connection connection) throws PecasNotFoundException, SQLException {
        final String sql = "UPDATE T_PECAS SET nm_marca = ?, qt_quantidade = ?, vl_valor = ?, ds_descricao = ?, id_orcamento = ?, id_servico = ? WHERE id_peca = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, peca.getMarca());
        stmt.setInt(2, peca.getQuantidade());
        stmt.setDouble(3, peca.getValor());
        stmt.setString(4, peca.getDescricao());
        stmt.setLong(5, peca.getIdOrcamento());
        stmt.setLong(6, peca.getIdServico());
        stmt.setLong(7, peca.getIdPeca());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que a Peça não foi encontrada.
        if (linhasAlteradas == 0) {
            throw new PecasNotFoundException("Peça não encontrada: verifique o ID fornecido ou se o registro existe.");
        }
        return peca;
    }
}
