package br.com.fiap.dao;

import br.com.fiap.dao.interfaces.TelefoneDao;
import br.com.fiap.models.Telefone;

/**
 * Factory para criar instâncias de TelefoneOficinaDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link TelefoneOficinaDaoImpl}
 * e retorna uma interface {@link TelefoneDao} para operações de CRUD específicas de TelefoneOficina.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class TelefoneOficinaDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link TelefoneOficinaDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     *
     * @since 1.0
     */
    private TelefoneOficinaDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link TelefoneOficinaDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link TelefoneOficinaDaoImpl}
     * para operações de persistência de Telefone de oficina no banco de dados.</p>
     *
     * @return Uma implementação de {@link TelefoneDao} para Telefone de oficina.
     * @since 1.0
     */
    public static TelefoneDao<Telefone, Long> create() {
        return new TelefoneOficinaDaoImpl();
    }
}
