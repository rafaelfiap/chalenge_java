package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.PagamentoDao;
import br.com.fiap.exceptions.PagamentoNotFoundException;
import br.com.fiap.exceptions.PagamentoNotSavedException;
import br.com.fiap.models.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Pagamento, gerenciando operações de CRUD.
 */
public class PagamentoDaoImpl implements PagamentoDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private static final Logger logger = Logger.getLogger(PagamentoDaoImpl.class.getName());

    /**
     * Busca todas as instâncias de Pagamento no banco de dados.
     *
     * @return Lista de instâncias de {@link Pagamento} encontradas no banco de dados.
     */
    @Override
    public List<Pagamento> findAll() {
        final List<Pagamento> pagamentos = new ArrayList<>();
        final String sql = "SELECT * FROM T_METODO_PAGAMENTO";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Pagamento.
            while (rs.next()) {
                Pagamento pagamento = new Pagamento(
                        rs.getLong("id_pagamento"),
                        rs.getString("st_forma_pagamento"),
                        rs.getString("st_tipo_pagamento"),
                        rs.getDouble("vl_desconto"),
                        rs.getLong("id_os")
                );
                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar pagamentos: " + e.getMessage());
        }
        return pagamentos;
    }

    /**
     * Remove um Pagamento pelo ID no banco de dados.
     *
     * @param id O ID do Pagamento a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws PagamentoNotFoundException Se o Pagamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws PagamentoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_METODO_PAGAMENTO WHERE id_pagamento = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Pagamento não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new PagamentoNotFoundException("Pagamento não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo Pagamento no banco de dados.
     *
     * @param pagamento A instância de Pagamento a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Pagamento} que foi salva.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws PagamentoNotSavedException Se o Pagamento não puder ser salvo.
     */
    @Override
    public Pagamento save(Pagamento pagamento, Connection connection) throws SQLException, PagamentoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_METODO_PAGAMENTO(st_forma_pagamento, st_tipo_pagamento, vl_desconto, id_os) VALUES(?, ?, ?, ?) RETURNING id_pagamento INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, pagamento.getFormaPagamento());
        call.setString(2, pagamento.getTipoPagamento());
        call.setDouble(3, pagamento.getDesconto());
        call.setLong(4, pagamento.getIdOrdemDeServico());
        call.registerOutParameter(5, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new PagamentoNotSavedException("Erro ao salvar o pagamento: verifique os dados fornecidos ou tente novamente.");
        }

        pagamento.setIdPagamento(id);
        return pagamento;
    }

    /**
     * Atualiza um Pagamento existente no banco de dados.
     *
     * @param pagamento A instância de Pagamento com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return A instância de {@link Pagamento} atualizada.
     * @throws PagamentoNotFoundException Se o Pagamento com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Pagamento update(Pagamento pagamento, Connection connection) throws PagamentoNotFoundException, SQLException {
        final String sql = "UPDATE T_METODO_PAGAMENTO SET st_forma_pagamento = ?, st_tipo_pagamento = ?, vl_desconto = ?, id_os = ? WHERE id_pagamento = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pagamento.getFormaPagamento());
        stmt.setString(2, pagamento.getTipoPagamento());
        stmt.setDouble(3, pagamento.getDesconto());
        stmt.setLong(4, pagamento.getIdOrdemDeServico());
        stmt.setLong(5, pagamento.getIdPagamento());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Pagamento não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new PagamentoNotFoundException("Pagamento não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return pagamento;
    }
}
