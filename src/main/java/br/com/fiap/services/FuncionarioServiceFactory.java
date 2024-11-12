package br.com.fiap.services;

import br.com.fiap.services.interfaces.FuncionarioService;

/**
 * Factory para criar instâncias de FuncionarioService.
 */
public final class FuncionarioServiceFactory {

    private FuncionarioServiceFactory() {
        // Construtor privado para evitar instanciação
    }

    /**
     * Cria uma nova instância de FuncionarioServiceImpl.
     *
     * @return Uma implementação de FuncionarioService para gerenciar Funcionario.
     */
    public static FuncionarioService create() {
        return new FuncionarioServiceImpl();
    }
}
