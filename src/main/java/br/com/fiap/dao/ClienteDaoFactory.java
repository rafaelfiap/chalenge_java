package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.ClienteDao;

/**
 * Factory para criar instâncias de ClienteDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link ClienteDaoImpl}
 * e retorna a interface {@link ClienteDao} para operações de CRUD específicas de Cliente.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class ClienteDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link ClienteDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private ClienteDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link ClienteDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link ClienteDaoImpl}
     * para operações de persistência de Cliente no banco de dados.</p>
     *
     * @return Uma implementação de {@link ClienteDao} para Cliente.
     * @since 1.0
     */
    public static ClienteDao create() {
        return new ClienteDaoImpl();
    }
}
