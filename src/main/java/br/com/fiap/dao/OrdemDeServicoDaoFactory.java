package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.OrdemDeServicoDao;

/**
 * Factory para criar instâncias de OrdemDeServicoDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link OrdemDeServicoDaoImpl}
 * e retorna a interface {@link OrdemDeServicoDao} para operações de CRUD específicas de OrdemDeServico.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OrdemDeServicoDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OrdemDeServicoDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private OrdemDeServicoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OrdemDeServicoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OrdemDeServicoDaoImpl}
     * para operações de persistência de OrdemDeServico no banco de dados.</p>
     *
     * @return Uma implementação de {@link OrdemDeServicoDao} para OrdemDeServico.
     * @since 1.0
     */
    public static OrdemDeServicoDao create() {
        return new OrdemDeServicoDaoImpl();
    }
}
