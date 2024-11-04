package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Falhas.
 *
 * <p>A classe `FalhasDto` é usada para transferir dados da Falhas detectada entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, descrição da Falhas,
 * descrição da solução, ID do orçamento, ID do veículo e a gravidade da Falhas.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class FalhasDto {

    // Identificador único da Falha, utilizado para operações de atualização e deleção.
    private Long idFalha;

    // Descrição da Falha detectada.
    private String descricaoFalha;

    // Solução proposta para a Falha.
    private String descricaoSolucao;

    // Identificador do orçamento relacionado à Falha.
    private Long idOrcamento;

    // Identificador do veículo associado à Falha.
    private Long idVeiculo;

    // Gravidade da Falha (Baixa, Média, Alta).
    private String gravidade;

    // Getters e Setters

    /**
     * Obtém o ID da Falha.
     *
     * @return O ID da Falha.
     * @since 1.0
     */
    public Long getIdFalha() {
        return idFalha;
    }

    /**
     * Define o ID da Falhas.
     *
     * @param idFalha O novo ID da Falha.
     * @since 1.0
     */
    public void setIdFalha(Long idFalha) {
        this.idFalha = idFalha;
    }

    /**
     * Obtém a descrição da Falhas detectada.
     *
     * @return A descrição da Falhas.
     * @since 1.0
     */
    public String getDescricaoFalha() {
        return descricaoFalha;
    }

    /**
     * Define a descrição da Falhas detectada.
     *
     * @param descricaoFalha A nova descrição da Falhas.
     * @since 1.0
     */
    public void setDescricaoFalhs(String descricaoFalha) {
        this.descricaoFalha = descricaoFalha;
    }

    /**
     * Obtém a solução proposta para a Falhas.
     *
     * @return A solução da Falhas.
     * @since 1.0
     */
    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    /**
     * Define a solução proposta para a Falhas.
     *
     * @param descricaoSolucao A nova solução da Falhas.
     * @since 1.0
     */
    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    /**
     * Obtém o ID do orçamento relacionado à Falhas.
     *
     * @return O ID do orçamento.
     * @since 1.0
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento relacionado à Falhas.
     *
     * @param idOrcamento O novo ID do orçamento.
     * @since 1.0
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o ID do veículo associado à Falhas.
     *
     * @return O ID do veículo.
     * @since 1.0
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo associado à Falhas.
     *
     * @param idVeiculo O novo ID do veículo.
     * @since 1.0
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém a gravidade da Falhas.
     *
     * @return A gravidade da Falhas (Baixa, Média, Alta).
     * @since 1.0
     */
    public String getGravidade() {
        return gravidade;
    }

    /**
     * Define a gravidade da Falhas.
     *
     * @param gravidade A nova gravidade da Falhas.
     * @since 1.0
     */
    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    /**
     * Retorna uma representação textual do objeto FalhasDto.
     *
     * @return Uma string com os detalhes do FalhasDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "FalhasDto{" +
                "idFalhas=" + idFalha +
                ", descricaoFalhas='" + descricaoFalha + '\'' +
                ", descricaoSolucao='" + descricaoSolucao + '\'' +
                ", idOrcamento=" + idOrcamento +
                ", idVeiculo=" + idVeiculo +
                ", gravidade='" + gravidade + '\'' +
                '}';
    }
}
