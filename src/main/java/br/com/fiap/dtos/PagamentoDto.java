package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Pagamento.
 *
 * <p>A classe `PagamentoDto` é usada para transferir dados do pagamento entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID do pagamento,
 * forma de pagamento, tipo de pagamento, valor de desconto e ID da ordem de serviço associada.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class PagamentoDto {

    // Identificador único do pagamento, utilizado para operações de atualização e deleção.
    private Long idPagamento;

    // Forma de pagamento (ex: crédito, débito).
    private String formaPagamento;

    // Tipo de pagamento (ex: à vista, parcelado).
    private String tipoPagamento;

    // Valor do desconto aplicado ao pagamento.
    private double desconto;

    // Identificador da ordem de serviço associada ao pagamento.
    private Long idOrdemDeServico;

    // Getters e Setters

    /**
     * Obtém o ID do pagamento.
     *
     * @return O ID do pagamento.
     * @since 1.0
     */
    public Long getIdPagamento() {
        return idPagamento;
    }

    /**
     * Define o ID do pagamento.
     *
     * @param idPagamento O novo ID do pagamento.
     * @since 1.0
     */
    public void setIdPagamento(Long idPagamento) {
        this.idPagamento = idPagamento;
    }

    /**
     * Obtém a forma de pagamento.
     *
     * @return A forma de pagamento.
     * @since 1.0
     */
    public String getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * Define a forma de pagamento.
     *
     * @param formaPagamento A nova forma de pagamento.
     * @since 1.0
     */
    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * Obtém o tipo de pagamento.
     *
     * @return O tipo de pagamento.
     * @since 1.0
     */
    public String getTipoPagamento() {
        return tipoPagamento;
    }

    /**
     * Define o tipo de pagamento.
     *
     * @param tipoPagamento O novo tipo de pagamento.
     * @since 1.0
     */
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    /**
     * Obtém o valor do desconto aplicado.
     *
     * @return O valor do desconto.
     * @since 1.0
     */
    public double getDesconto() {
        return desconto;
    }

    /**
     * Define o valor do desconto aplicado.
     *
     * @param desconto O novo valor de desconto.
     * @since 1.0
     */
    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    /**
     * Obtém o ID da ordem de serviço associada ao pagamento.
     *
     * @return O ID da ordem de serviço.
     * @since 1.0
     */
    public Long getIdOrdemDeServico() {
        return idOrdemDeServico;
    }

    /**
     * Define o ID da ordem de serviço associada ao pagamento.
     *
     * @param idOrdemDeServico O novo ID da ordem de serviço.
     * @since 1.0
     */
    public void setIdOrdemDeServico(Long idOrdemDeServico) {
        this.idOrdemDeServico = idOrdemDeServico;
    }

    /**
     * Retorna uma representação textual do objeto PagamentoDto.
     *
     * @return Uma string com os detalhes do PagamentoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "PagamentoDto{" +
                "idPagamento=" + idPagamento +
                ", formaPagamento='" + formaPagamento + '\'' +
                ", tipoPagamento='" + tipoPagamento + '\'' +
                ", desconto=" + desconto +
                ", idOrdemDeServico=" + idOrdemDeServico +
                '}';
    }
}
