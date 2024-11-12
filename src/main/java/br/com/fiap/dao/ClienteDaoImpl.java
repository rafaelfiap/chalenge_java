package br.com.fiap.dao;

import br.com.fiap.config.DatabaseConnectionFactory;
import br.com.fiap.dao.interfaces.ClienteDao;
import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Implementação de DAO para a entidade Cliente, gerenciando operações de CRUD.
 */
class ClienteDaoImpl implements ClienteDao {

    // Logger para registrar mensagens e eventos, utilizado para fins de depuração e monitoramento.
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    /**
     * Busca todas as instâncias de Cliente no banco de dados.
     *
     * @return Lista de instâncias de {@link Cliente} encontradas no banco de dados.
     */
    @Override
    public List<Cliente> findAll() {
        final List<Cliente> clientes = new ArrayList<>();
        final String sql = "SELECT * FROM T_CLIENTE";
        try (Connection conn = DatabaseConnectionFactory.create().get()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Itera pelos resultados e cria instâncias de Cliente.
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getLong("id_cliente"),
                        rs.getString("nr_cpf"),
                        rs.getString("nm_cliente"),
                        rs.getString("ds_email"),
                        rs.getString("sx_sexo").charAt(0)
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            logger.warning("Erro ao recuperar clientes: " + e.getMessage());
        }
        return clientes;
    }

    /**
     * Remove um Cliente pelo ID no banco de dados.
     *
     * @param id O ID do Cliente a ser removido.
     * @param connection Conexão com o banco de dados.
     * @throws ClienteNotFoundException Se o Cliente com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public void deleteById(Long id, Connection connection) throws ClienteNotFoundException, SQLException {
        final String sql = "DELETE FROM T_CLIENTE WHERE id_cliente = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setLong(1, id);
        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Cliente não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new ClienteNotFoundException("Cliente não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
    }

    /**
     * Salva um novo Cliente no banco de dados.
     *
     * @param cliente A instância de Cliente a ser salva.
     * @param connection Conexão com o banco de dados.
     * @return O Cliente salvo com o ID gerado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     * @throws ClienteNotSavedException Se o Cliente não puder ser salvo.
     */
    @Override
    public Cliente save(Cliente cliente, Connection connection) throws SQLException, ClienteNotSavedException {
        final String sql = "BEGIN INSERT INTO T_CLIENTE(nr_cpf, nm_cliente, ds_email, sx_sexo) VALUES (?, ?, ?, ?) RETURNING id_cliente INTO ?; END;";
        CallableStatement call = connection.prepareCall(sql);
        call.setString(1, cliente.getCpf());
        call.setString(2, cliente.getNome());
        call.setString(3, cliente.getEmail());
        call.setString(4, String.valueOf(cliente.getSexo()));
        call.registerOutParameter(5, Types.NUMERIC);

        int linhasAlteradas = call.executeUpdate();
        long id = call.getLong(5);

        // Verifica se a inserção foi bem-sucedida. Se não, lança exceção indicando falha ao salvar.
        if (linhasAlteradas == 0 || id == 0) {
            throw new ClienteNotSavedException("Erro ao salvar o cliente: verifique os dados fornecidos ou tente novamente.");
        }

        cliente.setIdCliente(id);
        return cliente;
    }

    /**
     * Atualiza um Cliente existente no banco de dados.
     *
     * @param cliente A instância de Cliente com os dados atualizados.
     * @param connection Conexão com o banco de dados.
     * @return O Cliente atualizado.
     * @throws ClienteNotFoundException Se o Cliente com o ID fornecido não for encontrado.
     * @throws SQLException Se ocorrer um erro ao acessar o banco de dados.
     */
    @Override
    public Cliente update(Cliente cliente, Connection connection) throws ClienteNotFoundException, SQLException {
        final String sql = "UPDATE T_CLIENTE SET nr_cpf = ?, nm_cliente = ?, ds_email = ?, sx_sexo = ? WHERE id_cliente = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, cliente.getCpf());
        stmt.setString(2, cliente.getNome());
        stmt.setString(3, cliente.getEmail());
        stmt.setString(4, String.valueOf(cliente.getSexo()));
        stmt.setLong(5, cliente.getIdCliente());

        int linhasAlteradas = stmt.executeUpdate();

        // Verifica se alguma linha foi alterada. Se não, lança exceção indicando que o Cliente não foi encontrado.
        if (linhasAlteradas == 0) {
            throw new ClienteNotFoundException("Cliente não encontrado: verifique o ID fornecido ou se o registro existe.");
        }
        return cliente;
    }
}
