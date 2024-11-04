package br.com.fiap.services;

import br.com.fiap.services.interfaces.EnderecoService;

/**
 * Factory para criar instâncias de EnderecoOficinaService.
 *
 * <p>Essa factory cria instâncias da implementação {@link EnderecoOficinaServiceImpl}
 * e retorna a interface {@link EnderecoService} para operações de serviço em EnderecoOficina.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class EnderecoOficinaServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link EnderecoOficinaServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private EnderecoOficinaServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link EnderecoOficinaServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link EnderecoOficinaServiceImpl}
     * para operações de serviço de EnderecoOficina no sistema.</p>
     *
     * @return Uma implementação de {@link EnderecoService} para EnderecoOficina.
     * @since 1.0
     */
    public static EnderecoService create() {
        return new EnderecoOficinaServiceImpl();
    }
}
