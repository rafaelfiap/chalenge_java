package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.PecasDao;

/**
 * Factory para criar instâncias de PecasDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link PecasDaoImpl}
 * e retorna a interface {@link PecasDao} para operações de CRUD específicas de Pecas.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class PecasDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link PecasDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private PecasDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link PecasDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link PecasDaoImpl}
     * para operações de persistência de Pecas no banco de dados.</p>
     *
     * @return Uma implementação de {@link PecasDao} para Pecas.
     * @since 1.0
     */
    public static PecasDao create() {
        return new PecasDaoImpl();
    }
}
