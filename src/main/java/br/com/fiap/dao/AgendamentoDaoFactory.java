package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.AgendamentoDao;

/**
 * Factory para criar instâncias de AgendamentoDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link AgendamentoDaoImpl}
 * e retorna a interface {@link AgendamentoDao} para operações de CRUD específicas de Agendamento.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class AgendamentoDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link AgendamentoDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private AgendamentoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link AgendamentoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link AgendamentoDaoImpl}
     * para operações de persistência de Agendamento no banco de dados.</p>
     *
     * @return Uma implementação de {@link AgendamentoDao} para Agendamento.
     * @since 1.0
     */
    public static AgendamentoDao create() {
        return new AgendamentoDaoImpl();
    }
}
