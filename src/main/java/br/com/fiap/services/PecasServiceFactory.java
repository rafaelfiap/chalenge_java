package br.com.fiap.services;

import br.com.fiap.services.interfaces.PecasService;

/**
 * Factory para criar instâncias de PecasService.
 *
 * <p>Essa factory cria instâncias da implementação {@link PecasServiceImpl}
 * e retorna a interface {@link PecasService} para operações de serviço em Pecas.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class PecasServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link PecasServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private PecasServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link PecasServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link PecasServiceImpl}
     * para operações de serviço de Pecas no sistema.</p>
     *
     * @return Uma implementação de {@link PecasService} para Pecas.
     * @since 1.0
     */
    public static PecasService create() {
        return new PecasServiceImpl();
    }
}
