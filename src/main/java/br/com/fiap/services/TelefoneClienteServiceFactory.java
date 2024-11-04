package br.com.fiap.services;

import br.com.fiap.services.interfaces.TelefoneService;

/**
 * Factory para criar instâncias de TelefoneClienteService.
 *
 * <p>Essa factory cria instâncias da implementação {@link TelefoneClienteServiceImpl}
 * e retorna a interface {@link TelefoneService} para operações de serviço em TelefoneCliente.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public final class TelefoneClienteServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link TelefoneClienteServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private TelefoneClienteServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link TelefoneClienteServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link TelefoneClienteServiceImpl}
     * para operações de serviço de TelefoneCliente no sistema.</p>
     *
     * @return Uma implementação de {@link TelefoneService} para TelefoneCliente.
     * @since 1.0
     */
    public static TelefoneService create() {
        return new TelefoneClienteServiceImpl();
    }
}
