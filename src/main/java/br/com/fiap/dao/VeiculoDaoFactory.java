package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.VeiculoDao;

/**
 * Factory para criar instâncias de VeiculoDao.
 *
 * <p>Essa factory fornece uma instância da implementação {@link VeiculoDaoImpl}
 * que pode ser usada para operações de CRUD relacionadas à entidade Veiculo no banco de dados.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class VeiculoDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link VeiculoDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private VeiculoDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link VeiculoDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância de {@link VeiculoDaoImpl}
     * com uma conexão ativa com o banco de dados.</p>
     *
     * @return Uma instância de {@link VeiculoDao} conectada ao banco de dados.
     * @since 1.0
     */
    public static VeiculoDao create() {
        return new VeiculoDaoImpl();
    }
}
