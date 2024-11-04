package br.com.fiap.services;

import br.com.fiap.services.interfaces.EnderecoService;

/**
 * Factory para criar instâncias de EnderecoClienteService.
 *
 * <p>Essa factory cria instâncias da implementação {@link EnderecoClienteServiceImpl}
 * e retorna a interface {@link EnderecoService} para operações de serviço em EnderecoCliente.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class EnderecoClienteServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EnderecoClienteServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private EnderecoClienteServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EnderecoClienteServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EnderecoClienteServiceImpl}
     * para operações de serviço de EnderecoCliente no sistema.</p>
     *
     * @return Uma implementação de {@link EnderecoService} para EnderecoCliente.
     * @since 1.0
     */
    public static EnderecoService create() {
        return new EnderecoClienteServiceImpl();
    }
}
