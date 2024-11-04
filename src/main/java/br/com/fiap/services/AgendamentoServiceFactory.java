package br.com.fiap.services;

import br.com.fiap.services.interfaces.AgendamentoService;

/**
 * Factory para criar instâncias de AgendamentoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link AgendamentoServiceImpl}
 * e retorna a interface {@link AgendamentoService} para operações de serviço em Agendamento.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class AgendamentoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link AgendamentoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private AgendamentoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link AgendamentoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link AgendamentoServiceImpl}
     * para operações de serviço de Agendamento no sistema.</p>
     *
     * @return Uma implementação de {@link AgendamentoService} para Agendamento.
     * @since 1.0
     */
    public static AgendamentoService create() {
        return new AgendamentoServiceImpl();
    }
}
