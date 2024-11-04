package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Peça.
 *
 * <p>A classe `PecasDto` é usada para transferir dados da peça entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, marca,
 * quantidade, valor, descrição, e os identificadores de orçamento e serviço associados.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class PecasDto {

    // Identificador único da peça, utilizado para operações de atualização e deleção.
    private Long idPeca;

    // Marca da peça.
    private String marca;

    // Quantidade da peça.
    private int quantidade;

    // Valor unitário da peça.
    private double valor;

    // Descrição adicional sobre a peça.
    private String descricao;

    // Identificador do orçamento associado à peça.
    private Long idOrcamento;

    // Identificador do serviço associado à peça.
    private Long idServico;

    // Getters e Setters

    /**
     * Obtém o ID da peça.
     *
     * @return O ID da peça.
     * @since 1.0
     */
    public Long getIdPeca() {
        return idPeca;
    }

    /**
     * Define o ID da peça.
     *
     * @param idPeca O novo ID da peça.
     * @since 1.0
     */
    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    /**
     * Obtém a marca da peça.
     *
     * @return A marca da peça.
     * @since 1.0
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca da peça.
     *
     * @param marca A nova marca da peça.
     * @since 1.0
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém a quantidade da peça.
     *
     * @return A quantidade da peça.
     * @since 1.0
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade da peça.
     *
     * @param quantidade A nova quantidade da peça.
     * @since 1.0
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém o valor unitário da peça.
     *
     * @return O valor unitário da peça.
     * @since 1.0
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor unitário da peça.
     *
     * @param valor O novo valor unitário da peça.
     * @since 1.0
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtém a descrição da peça.
     *
     * @return A descrição da peça.
     * @since 1.0
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da peça.
     *
     * @param descricao A nova descrição da peça.
     * @since 1.0
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o ID do orçamento associado à peça.
     *
     * @return O ID do orçamento.
     * @since 1.0
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento associado à peça.
     *
     * @param idOrcamento O novo ID do orçamento.
     * @since 1.0
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o ID do serviço associado à peça.
     *
     * @return O ID do serviço.
     * @since 1.0
     */
    public Long getIdServico() {
        return idServico;
    }

    /**
     * Define o ID do serviço associado à peça.
     *
     * @param idServico O novo ID do serviço.
     * @since 1.0
     */
    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    /**
     * Retorna uma representação textual do objeto PecasDto.
     *
     * @return Uma string com os detalhes da PecasDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "PecasDto{" +
                "idPeca=" + idPeca +
                ", marca='" + marca + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", idOrcamento=" + idOrcamento +
                ", idServico=" + idServico +
                '}';
    }
}
