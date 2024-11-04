package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.FalhasDao;

/**
 * Factory para criar instâncias de FalhasDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link FalhasDaoImpl}
 * e retorna a interface {@link FalhasDao} para operações de CRUD específicas de Falhas.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class FalhasDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link FalhasDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     */
    private FalhasDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link FalhasDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link FalhasDaoImpl}
     * para operações de persistência de Falhas no banco de dados.</p>
     *
     * @return Uma implementação de {@link FalhasDao} para Falhas.
     * @since 1.0
     */
    public static FalhasDao create() {
        return new FalhasDaoImpl();
    }
}
