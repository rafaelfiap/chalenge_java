package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Orcamento.
 *
 * <p>A classe `OrcamentoDto` é usada para transferir dados do orçamento entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, valor,
 * situação, e os IDs do veículo, oficina, serviço e peça associados ao orçamento.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class OrcamentoDto {

    // Identificador único do orçamento, utilizado para operações de atualização e deleção.
    private Long idOrcamento;

    // Valor total do orçamento.
    private double valorOrcamento;

    // Situação do orçamento (ex: Aprovado, Pendente, Cancelado).
    private String situacao;

    // Identificador do veículo associado ao orçamento.
    private Integer idVeiculo;

    // Identificador da oficina associada ao orçamento.
    private Integer idOficina;

    // Identificador do serviço associado ao orçamento.
    private Integer idServico;

    // Identificador da peça associada ao orçamento.
    private Integer idPeca;

    // Getters e Setters

    /**
     * Obtém o ID do orçamento.
     *
     * @return O ID do orçamento.
     * @since 1.0
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento.
     *
     * @param idOrcamento O novo ID do orçamento.
     * @since 1.0
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o valor total do orçamento.
     *
     * @return O valor total do orçamento.
     * @since 1.0
     */
    public double getValorOrcamento() {
        return valorOrcamento;
    }

    /**
     * Define o valor total do orçamento.
     *
     * @param valorOrcamento O novo valor total do orçamento.
     * @since 1.0
     */
    public void setValorOrcamento(double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    /**
     * Obtém a situação atual do orçamento.
     *
     * @return A situação do orçamento.
     * @since 1.0
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Define a situação do orçamento.
     *
     * @param situacao A nova situação do orçamento.
     * @since 1.0
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * Obtém o ID do veículo associado ao orçamento.
     *
     * @return O ID do veículo associado ao orçamento.
     * @since 1.0
     */
    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo associado ao orçamento.
     *
     * @param idVeiculo O novo ID do veículo associado.
     * @since 1.0
     */
    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém o ID da oficina associada ao orçamento.
     *
     * @return O ID da oficina associada.
     * @since 1.0
     */
    public Integer getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina associada ao orçamento.
     *
     * @param idOficina O novo ID da oficina associada.
     * @since 1.0
     */
    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Obtém o ID do serviço associado ao orçamento.
     *
     * @return O ID do serviço associado ao orçamento.
     * @since 1.0
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * Define o ID do serviço associado ao orçamento.
     *
     * @param idServico O novo ID do serviço associado.
     * @since 1.0
     */
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    /**
     * Obtém o ID da peça associada ao orçamento.
     *
     * @return O ID da peça associada ao orçamento.
     * @since 1.0
     */
    public Integer getIdPeca() {
        return idPeca;
    }

    /**
     * Define o ID da peça associada ao orçamento.
     *
     * @param idPeca O novo ID da peça associada.
     * @since 1.0
     */
    public void setIdPeca(Integer idPeca) {
        this.idPeca = idPeca;
    }

    /**
     * Retorna uma representação textual do objeto OrcamentoDto.
     *
     * @return Uma string com os detalhes do OrcamentoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "OrcamentoDto{" +
                "idOrcamento=" + idOrcamento +
                ", valorOrcamento=" + valorOrcamento +
                ", situacao='" + situacao + '\'' +
                ", idVeiculo=" + idVeiculo +
                ", idOficina=" + idOficina +
                ", idServico=" + idServico +
                ", idPeca=" + idPeca +
                '}';
    }
}
