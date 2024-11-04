package br.com.fiap.dao;


import br.com.fiap.dao.interfaces.OficinaDao;

/**
 * Factory para criar instâncias de OficinaDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link OficinaDaoImpl}
 * e retorna a interface {@link OficinaDao} para operações de DAO em Oficina.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class OficinaDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OficinaDaoFactory}.
     */
    private OficinaDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OficinaDaoImpl}.
     *
     * @return Uma implementação de {@link OficinaDao} para Oficina.
     * @since 1.0
     */
    public static OficinaDao create() {
        return new OficinaDaoImpl();
    }
}
