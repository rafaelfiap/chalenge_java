package br.com.fiap.services;

import br.com.fiap.services.interfaces.VeiculoService;

/**
 * Factory para criar instâncias de VeiculoService.
 *
 * <p>Essa factory cria instâncias da implementação {@link VeiculoServiceImpl}
 * e retorna a interface {@link VeiculoService} para operações de serviço em Veiculo.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class VeiculoServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link VeiculoServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private VeiculoServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link VeiculoServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link VeiculoServiceImpl}
     * para operações de serviço de Veiculo no sistema.</p>
     *
     * @return Uma implementação de {@link VeiculoService} para Veiculo.
     * @since 1.0
     */
    public static VeiculoService create() {
        return new VeiculoServiceImpl();
    }
}
