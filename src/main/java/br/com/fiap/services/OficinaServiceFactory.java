package br.com.fiap.services;

import br.com.fiap.services.interfaces.OficinaService;

/**
 * Factory para criar instâncias de OficinaService.
 *
 * <p>Essa factory cria instâncias da implementação {@link OficinaServiceImpl}
 * e retorna a interface {@link OficinaService} para operações de serviço em Oficina.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class OficinaServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OficinaServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private OficinaServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OficinaServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OficinaServiceImpl}
     * para operações de serviço de Oficina no sistema.</p>
     *
     * @return Uma implementação de {@link OficinaService} para Oficina.
     * @since 1.0
     */
    public static OficinaService create() {
        return new OficinaServiceImpl();
    }
}
