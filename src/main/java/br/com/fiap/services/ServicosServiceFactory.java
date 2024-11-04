package br.com.fiap.services;

import br.com.fiap.services.interfaces.ServicosService;

/**
 * Factory para criar instâncias de ServicosService.
 *
 * <p>Essa factory cria instâncias da implementação {@link ServicosServiceImpl}
 * e retorna a interface {@link ServicosService} para operações de serviço em Servicos.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class ServicosServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link ServicosServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private ServicosServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link ServicosServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link ServicosServiceImpl}
     * para operações de serviço de Servicos no sistema.</p>
     *
     * @return Uma implementação de {@link ServicosService} para Servicos.
     * @since 1.0
     */
    public static ServicosService create() {
        return new ServicosServiceImpl();
    }
}
