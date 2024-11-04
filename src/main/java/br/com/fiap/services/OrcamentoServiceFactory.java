package br.com.fiap.services;

import br.com.fiap.services.interfaces.OrcamentoService;

/**
 * Factory para criar instâncias de OrcamentoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link OrcamentoServiceImpl}
 * e retorna a interface {@link OrcamentoService} para operações de serviço em Orcamento.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class OrcamentoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link OrcamentoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private OrcamentoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link OrcamentoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link OrcamentoServiceImpl}
     * para operações de serviço de Orcamento no sistema.</p>
     *
     * @return Uma implementação de {@link OrcamentoService} para Orcamento.
     * @since 1.0
     */
    public static OrcamentoService create() {
        return new OrcamentoServiceImpl();
    }
}
