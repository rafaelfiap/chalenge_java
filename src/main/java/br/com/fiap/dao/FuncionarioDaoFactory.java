package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.FuncionarioDao;

/**
 * Factory para criar instâncias de FuncionarioDao.
 *
 * @version 1.0
 * @since 1.0
 */
public final class FuncionarioDaoFactory {

    private FuncionarioDaoFactory() {
        // Construtor privado para evitar instanciação
    }

    /**
     * Cria uma nova instância de FuncionarioDaoImpl.
     *
     * @return Uma implementação de FuncionarioDao.
     */
    public static FuncionarioDao create() {
        return new FuncionarioDaoImpl();
    }
}
