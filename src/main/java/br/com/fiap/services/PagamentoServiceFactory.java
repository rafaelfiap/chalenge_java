package br.com.fiap.services;

import br.com.fiap.services.interfaces.PagamentoService;

/**
 * Factory para criar instâncias de PagamentoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link PagamentoServiceImpl}
 * e retorna a interface {@link PagamentoService} para operações de serviço em Pagamento.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class PagamentoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link PagamentoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private PagamentoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link PagamentoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link PagamentoServiceImpl}
     * para operações de serviço de Pagamento no sistema.</p>
     *
     * @return Uma implementação de {@link PagamentoService} para Pagamento.
     * @since 1.0
     */
    public static PagamentoService create() {
        return new PagamentoServiceImpl();
    }
}
