package br.com.fiap.services;

import br.com.fiap.services.interfaces.FalhasService;

/**
 * Factory para criar instâncias de FalhasService.
 *
 * <p>Essa factory cria instâncias da implementação {@link FalhasServiceImpl}
 * e retorna a interface {@link FalhasService} para operações de serviço em Falhas.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class FalhasServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link FalhasServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private FalhasServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link FalhasServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link FalhasServiceImpl}
     * para operações de serviço de Falhas no sistema.</p>
     *
     * @return Uma implementação de {@link FalhasService} para Falhas.
     * @since 1.0
     */
    public static FalhasService create() {
        return new FalhasServiceImpl();
    }
}
