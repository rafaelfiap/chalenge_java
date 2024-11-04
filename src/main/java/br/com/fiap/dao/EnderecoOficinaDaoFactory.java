package br.com.fiap.dao;

import br.com.fiap.dao.interfaces.EnderecoDao;
import br.com.fiap.models.Endereco;

/**
 * Factory para criar instâncias de EnderecoOficinaDao.
 *
 * <p>Essa factory cria instâncias da implementação {@link EnderecoOficinaDaoImpl}
 * e retorna uma interface {@link EnderecoDao} para operações de CRUD específicas de EnderecoOficina.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class EnderecoOficinaDaoFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EnderecoOficinaDaoFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seus métodos estáticos, seguindo o padrão Singleton implícito.</p>
     *
     * @since 1.0
     */
    private EnderecoOficinaDaoFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EnderecoOficinaDaoImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EnderecoOficinaDaoImpl}
     * para operações de persistência de Endereco de oficina no banco de dados.</p>
     *
     * @return Uma implementação de {@link EnderecoDao} para Endereco de oficina.
     * @since 1.0
     */
    public static EnderecoDao<Endereco, Long> create() {
        return new EnderecoOficinaDaoImpl();
    }
}
