package br.com.fiap.models;

/**
 * Classe que representa um Pagamento.
 * Contém informações sobre a forma de pagamento, tipo de pagamento, valor de desconto e ID da ordem de serviço associada.
 *
 * @since 1.0
 * @version 1.1
 * @see OrdemDeServico
 */
public class Pagamento {
    private Long idPagamento;              // Identificador único do pagamento
    private String formaPagamento;         // Forma de pagamento (ex: crédito, débito)
    private String tipoPagamento;          // Tipo de pagamento (ex: à vista, parcelado)
    private double desconto;               // Valor do desconto aplicado
    private Long idOrdemDeServico;         // Identificador da ordem de serviço associada

    /**
     * Construtor para inicializar um Pagamento com todos os parâmetros.
     *
     * @param idPagamento       O ID exclusivo do pagamento.
     * @param formaPagamento    A forma de pagamento (ex: crédito, débito).
     * @param tipoPagamento     O tipo de pagamento (ex: à vista, parcelado).
     * @param desconto          O valor de desconto aplicado ao pagamento.
     * @param idOrdemDeServico  O ID da ordem de serviço associada ao pagamento.
     */
    public Pagamento(Long idPagamento, String formaPagamento, String tipoPagamento, double desconto, Long idOrdemDeServico) {
        this.idPagamento = idPagamento;
        this.formaPagamento = formaPagamento;
        this.tipoPagamento = tipoPagamento;
        this.desconto = desconto;
        this.idOrdemDeServico = idOrdemDeServico;
    }

    /**
     * Construtor para inicializar um Pagamento sem ID de pagamento.
     *
     * @param formaPagamento    A forma de pagamento (ex: crédito, débito).
     * @param tipoPagamento     O tipo de pagamento (ex: à vista, parcelado).
     * @param desconto          O valor de desconto aplicado ao pagamento.
     * @param idOrdemDeServico  O ID da ordem de serviço associada ao pagamento.
     */
    public Pagamento(String formaPagamento, String tipoPagamento, double desconto, Long idOrdemDeServico) {
        this(null, formaPagamento, tipoPagamento, desconto, idOrdemDeServico);
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do pagamento.
     *
     * @return O ID do pagamento.
     */
    public Long getIdPagamento() {
        return idPagamento;
    }

    /**
     * Define o ID exclusivo do pagamento.
     *
     * @param idPagamento O novo ID do pagamento.
     */
    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    /**
     * Obtém a forma de pagamento.
     *
     * @return A forma de pagamento.
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Define a forma de pagamento.
     *
     * @param formaPagamento A nova forma de pagamento.
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * Obtém o tipo de pagamento.
     *
     * @return O tipo de pagamento.
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }

    /**
     * Define o tipo de pagamento.
     *
     * @param tipoPagamento O novo tipo de pagamento.
     */
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * Obtém o valor do desconto aplicado.
     *
     * @return O valor do desconto.
     */
    public double getDesconto() {
        return desconto;
    }

    /**
     * Define o valor do desconto.
     *
     * @param desconto O novo valor de desconto.
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    /**
     * Obtém o ID da ordem de serviço associada ao pagamento.
     *
     * @return O ID da ordem de serviço.
     */
    public Long getIdOrdemDeServico() {
        return idOrdemDeServico;
    }

    /**
     * Define o ID da ordem de serviço associada ao pagamento.
     *
     * @param idOrdemDeServico O novo ID da ordem de serviço.
     */
    public void setIdOrdemDeServico(Long idOrdemDeServico) {
        this.idOrdemDeServico = idOrdemDeServico;
    }

    /**
     * Retorna uma representação textual do pagamento.
     *
     * @return Uma string com os detalhes do pagamento.
     */
    @Override
    public String toString() {
        return "Pagamento{" +
                "idPagamento=" + idPagamento +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", desconto=" + desconto +
                ", idOrdemDeServico=" + idOrdemDeServico +
                '}';
    }
}
