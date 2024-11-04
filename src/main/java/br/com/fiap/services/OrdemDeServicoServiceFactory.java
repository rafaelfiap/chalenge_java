package br.com.fiap.services;

import br.com.fiap.services.interfaces.OrdemDeServicoService;

/**
 * Factory para criar instâncias de OrdemDeServicoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link OrdemDeServicoServiceImpl}
 * e retorna a interface {@link OrdemDeServicoService} para operações de serviço em OrdemDeServico.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class OrdemDeServicoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OrdemDeServicoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private OrdemDeServicoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OrdemDeServicoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OrdemDeServicoServiceImpl}
     * para operações de serviço de OrdemDeServico no sistema.</p>
     *
     * @return Uma implementação de {@link OrdemDeServicoService} para OrdemDeServico.
     * @since 1.0
     */
    public static OrdemDeServicoService create() {
        return new OrdemDeServicoServiceImpl();
    }
}
