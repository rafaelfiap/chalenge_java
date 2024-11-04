package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.ServicosDao;

/**
 * Factory para criar instâncias de ServicosDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link ServicosDaoImpl}
 * e retorna a interface {@link ServicosDao} para operações de CRUD específicas de Servicos.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class ServicosDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link ServicosDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private ServicosDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link ServicosDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link ServicosDaoImpl}
     * para operações de persistência de Servicos no banco de dados.</p>
     *
     * @return Uma implementação de {@link ServicosDao} para Servicos.
     * @since 1.0
     */
    public static ServicosDao create() {
        return new ServicosDaoImpl();
    }
}
