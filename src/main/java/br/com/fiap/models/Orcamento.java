package br.com.fiap.models;

/**
 * Classe que representa um Orçamento.
 * Contém informações sobre o valor, situação, veículo, oficina, serviço e peça.
 *
 * @since 1.0
 * @version 1.4
 */
public class Orcamento {
    private Long idOrcamento; // Identificador único do orçamento
    private double valorOrcamento; // Valor total do orçamento
    private String situacao; // Situação do orçamento (ex: Aprovado, Pendente, Cancelado)
    private int idVeiculo; // ID do veículo relacionado ao orçamento
    private int idOficina; // ID da oficina responsável pelo orçamento
    private int idServico; // ID do serviço relacionado ao orçamento
    private int idPeca; // ID da peça relacionada ao orçamento

    /**
     * Construtor para inicializar um Orçamento com todos os parâmetros.
     *
     * @param idOrcamento    O ID exclusivo do orçamento.
     * @param valorOrcamento O valor total do orçamento.
     * @param situacao       A situação atual do orçamento.
     * @param idVeiculo      O ID do veículo associado ao orçamento.
     * @param idOficina      O ID da oficina associada ao orçamento.
     * @param idServico      O ID do serviço associado ao orçamento.
     * @param idPeca         O ID da peça associada ao orçamento.
     * @since 1.0
     * @version 1.4
     */
    public Orcamento(Long idOrcamento, double valorOrcamento, String situacao, int idVeiculo, int idOficina, int idServico, int idPeca) {
        this.idOrcamento = idOrcamento;
        this.valorOrcamento = valorOrcamento;
        this.situacao = situacao;
        this.idVeiculo = idVeiculo;
        this.idOficina = idOficina;
        this.idServico = idServico;
        this.idPeca = idPeca;
    }

    /**
     * Construtor alternativo que permite criar um orçamento sem especificar serviço e peça.
     *
     * @param idOrcamento    O ID exclusivo do orçamento.
     * @param valorOrcamento O valor total do orçamento.
     * @param situacao       A situação atual do orçamento.
     * @param idVeiculo      O ID do veículo associado ao orçamento.
     * @param idOficina      O ID da oficina associada ao orçamento.
     */
    public Orcamento(Long idOrcamento, double valorOrcamento, String situacao, int idVeiculo, int idOficina) {
        this(idOrcamento, valorOrcamento, situacao, idVeiculo, idOficina, 0, 0);  // Define serviço e peça como 0 (não especificados)
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do orçamento.
     *
     * @return O ID do orçamento.
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID exclusivo do orçamento.
     *
     * @param idOrcamento O novo ID do orçamento.
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o valor total do orçamento.
     *
     * @return O valor total do orçamento.
     */
    public double getValorOrcamento() {
        return valorOrcamento;
    }

    /**
     * Define o valor total do orçamento.
     *
     * @param valorOrcamento O novo valor total do orçamento.
     */
    public void setValorOrcamento(double valorOrcamento) {
        this.valorOrcamento = valorOrcamento;
    }

    /**
     * Obtém a situação atual do orçamento.
     *
     * @return A situação do orçamento (ex: Aprovado, Pendente).
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * Define a situação do orçamento.
     *
     * @param situacao A nova situação do orçamento.
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * Obtém o ID do veículo relacionado ao orçamento.
     *
     * @return O ID do veículo associado ao orçamento.
     */
    public int getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo relacionado ao orçamento.
     *
     * @param idVeiculo O novo ID do veículo.
     */
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém o ID da oficina responsável pelo orçamento.
     *
     * @return O ID da oficina responsável pelo orçamento.
     */
    public int getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina responsável pelo orçamento.
     *
     * @param idOficina O novo ID da oficina.
     */
    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Obtém o ID do serviço associado ao orçamento.
     *
     * @return O ID do serviço associado ao orçamento.
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * Define o ID do serviço associado ao orçamento.
     *
     * @param idServico O novo ID do serviço.
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * Obtém o ID da peça associada ao orçamento.
     *
     * @return O ID da peça associada ao orçamento.
     */
    public int getIdPeca() {
        return idPeca;
    }

    /**
     * Define o ID da peça associada ao orçamento.
     *
     * @param idPeca O novo ID da peça.
     */
    public void setIdPeca(int idPeca) {
        this.idPeca = idPeca;
    }

    /**
     * Exibe os detalhes completos do orçamento.
     */
    public void exibirDetalhes() {
        System.out.println("Orçamento ID: " + idOrcamento + ", Valor: " + valorOrcamento + ", Situação: " + situacao);
    }

    /**
     * Representação textual do objeto Orçamento.
     *
     * @return String com os detalhes do orçamento.
     */
    @Override
    public String toString() {
        return "Orcamento{" +
                "idOrcamento=" + idOrcamento +
                ", valorOrcamento=" + valorOrcamento +
                ", situacao='" + situacao + '\'' +
                ", idVeiculo=" + idVeiculo +
                ", idOficina=" + idOficina +
                '}';
    }

    /**
     * Aplica um desconto ao orçamento com base em um percentual fornecido.
     *
     * @param percentualDesconto O percentual de desconto a ser aplicado (entre 0 e 100).
     * @return O novo valor do orçamento após o desconto.
     */
    public double aplicarDesconto(double percentualDesconto) {
        if (percentualDesconto < 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("Desconto inválido");
        }
        this.valorOrcamento -= this.valorOrcamento * (percentualDesconto / 100);
        return this.valorOrcamento;
    }
}
