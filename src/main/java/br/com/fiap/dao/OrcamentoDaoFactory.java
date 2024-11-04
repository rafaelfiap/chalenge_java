package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.OrcamentoDao;

/**
 * Factory para criar instâncias de OrcamentoDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link OrcamentoDaoImpl}
 * e retorna a interface {@link OrcamentoDao} para operações de CRUD específicas de Orcamento.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OrcamentoDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OrcamentoDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private OrcamentoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OrcamentoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OrcamentoDaoImpl}
     * para operações de persistência de Orcamento no banco de dados.</p>
     *
     * @return Uma implementação de {@link OrcamentoDao} para Orcamento.
     * @since 1.0
     */
    public static OrcamentoDao create() {
        return new OrcamentoDaoImpl();
    }
}
