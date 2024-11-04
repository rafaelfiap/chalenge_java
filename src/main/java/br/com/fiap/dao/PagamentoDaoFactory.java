package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.PagamentoDao;

/**
 * Factory para criar instâncias de PagamentoDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link PagamentoDaoImpl}
 * e retorna a interface {@link PagamentoDao} para operações de CRUD específicas de Pagamento.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class PagamentoDaoFactory {

    private PagamentoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link PagamentoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link PagamentoDaoImpl}
     * para operações de persistência de Pagamento no banco de dados.</p>
     *
     * @return Uma implementação de {@link PagamentoDao} para Pagamento.
     * @since 1.0
     */
    public static PagamentoDao create() {
        return new PagamentoDaoImpl();
    }
}
