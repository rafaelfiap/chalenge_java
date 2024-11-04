package br.com.fiap.services;

import br.com.fiap.services.interfaces.ClienteService;

/**
 * Factory para criar instâncias de ClienteService.
 *
 * <p>Essa factory cria instâncias da implementação {@link ClienteServiceImpl}
 * e retorna a interface {@link ClienteService} para operações de serviço em Cliente.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class ClienteServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link ClienteServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private ClienteServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link ClienteServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link ClienteServiceImpl}
     * para operações de serviço de Cliente no sistema.</p>
     *
     * @return Uma implementação de {@link ClienteService} para Cliente.
     * @since 1.0
     */
    public static ClienteService create() {
        return new ClienteServiceImpl();
    }
}
