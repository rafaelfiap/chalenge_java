package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.EnderecoDao;
import br.com.fiap.exceptions.EnderecoNotFoundException;
import br.com.fiap.exceptions.EnderecoNotSavedException;
import br.com.fiap.models.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade EnderecoOficina, gerenciando operações de CRUD.
 */
class EnderecoOficinaDaoImpl implements EnderecoDao<Endereco, Long> {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de EnderecoOficina no banco de dados.
     *
     * @return Uma lista contendo todas as instâncias de {@link Endereco} encontradas no banco de dados.
     */
    @Override
    public List<Endereco> findAll() {
        final List<Endereco> all = new ArrayList<>();
        final String sql = "SELECT * FROM T_ENDERECO_OFICINA";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Endereco.
            while (rs.next()) {
                Endereco endereco = new Endereco(
                        rs.getLong("id_endereco_oficina"),
                        rs.getString("ds_logradouro"),
                        rs.getInt("nr_numero"),
                        rs.getString("nr_cep"),
                        rs.getString("nm_bairro"),
                        rs.getString("nm_cidade"),
                        rs.getString("sg_uf"),
                        rs.getInt("id_oficina")
                );
                all.add(endereco);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar endereços de oficinas: " + e.getMessage());
        }
        return all;
    }

    /**
     * Remove um EnderecoOficina pelo ID no banco de dados.
     *
     * @param id O ID do EnderecoOficina a ser removido.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @throws EnderecoNotFoundException Se o EnderecoOficina com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws EnderecoNotFoundException, SQLException {
        final String sql = "DELETE FROM T_ENDERECO_OFICINA WHERE id_endereco_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Endereço de Oficina não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new EnderecoNotFoundException("Endereço de oficina não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo EnderecoOficina no banco de dados.
     *
     * @param endereco A instância de EnderecoOficina a ser salva.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Endereco} que foi salva, com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws EnderecoNotSavedException Se o EnderecoOficina não puder ser salvo.
     */
    @Override
    public Endereco save(Endereco endereco, Connection connection) throws SQLException, EnderecoNotSavedException {
        final String sql = "BEGIN INSERT INTO T_ENDERECO_OFICINA(ds_logradouro, nr_numero, nr_cep, nm_bairro, nm_cidade, sg_uf, id_oficina) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id_endereco_oficina INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, endereco.getLogradouro());
        call.setInt(2, endereco.getNumero());
        call.setString(3, endereco.getCep());
        call.setString(4, endereco.getBairro());
        call.setString(5, endereco.getCidade());
        call.setString(6, endereco.getUf());
        call.setInt(7, endereco.getIdReferencia());
        call.registerOutParameter(8, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(8);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new EnderecoNotSavedException("Erro ao salvar o endereço de oficina: verifique os dados fornecidos ou tente novamente.");
        }

        endereco.setIdEndereco(id);
        return endereco;
    }

    /**
     * Atualiza um EnderecoOficina existente no banco de dados.
     *
     * @param endereco A instância de EnderecoOficina com os dados atualizados.
     * @param connection A conexão com o banco de dados a ser utilizada para a operação.
     * @return A instância de {@link Endereco} atualizada.
     * @throws EnderecoNotFoundException Se o EnderecoOficina com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Endereco update(Endereco endereco, Connection connection) throws EnderecoNotFoundException, SQLException {
        final String sql = "UPDATE T_ENDERECO_OFICINA SET ds_logradouro = ?, nr_numero = ?, nr_cep = ?, nm_bairro = ?, nm_cidade = ?, sg_uf = ?, id_oficina = ? WHERE id_endereco_oficina = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, endereco.getLogradouro());
        stmt.setInt(2, endereco.getNumero());
        stmt.setString(3, endereco.getCep());
        stmt.setString(4, endereco.getBairro());
        stmt.setString(5, endereco.getCidade());
        stmt.setString(6, endereco.getUf());
        stmt.setInt(7, endereco.getIdReferencia());
        stmt.setLong(8, endereco.getIdEndereco());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Endereço de Oficina não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new EnderecoNotFoundException("Endereço de oficina não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return endereco;
    }
}
