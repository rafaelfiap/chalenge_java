package br.com.fiap.services;

import br.com.fiap.services.interfaces.TelefoneService;

/**
 * Factory para criar instâncias de TelefoneOficinaService.
 *
 * <p>Essa factory cria instâncias da implementação {@link TelefoneOficinaServiceImpl}
 * e retorna a interface {@link TelefoneService} para operações de serviço em TelefoneOficina.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public final class TelefoneOficinaServiceFactory {

    /**
     * Construtor privado para evitar a criação de instâncias da classe {@link TelefoneOficinaServiceFactory}.
     *
     * <p>Este construtor é privado para garantir que a factory seja utilizada apenas
     * através de seu método estático, seguindo o padrão Singleton implícito.</p>
     */
    private TelefoneOficinaServiceFactory() {
        // Construtor privado para impedir a criação de instâncias.
    }

    /**
     * Cria e retorna uma nova instância de {@link TelefoneOficinaServiceImpl}.
     *
     * <p>Este método é utilizado para obter uma instância da implementação de {@link TelefoneOficinaServiceImpl}
     * para operações de serviço de TelefoneOficina no sistema.</p>
     *
     * @return Uma implementação de {@link TelefoneService} para TelefoneOficina.
     * @since 1.0
     */
    public static TelefoneService create() {
        return new TelefoneOficinaServiceImpl();
    }
}
