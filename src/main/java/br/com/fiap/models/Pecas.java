package br.com.fiap.models;

/**
 * Classe que representa uma Peça.
 * Contém informações sobre a marca, quantidade, valor, descrição e os identificadores de orçamento e serviço associados.
 *
 * @since 1.0
 * @version 1.1
 * @see Orcamento
 * @see Servicos
 */
public class Pecas {
    private Long idPeca;          // Identificador único da peça
    private String marca;         // Marca da peça
    private int quantidade;       // Quantidade da peça
    private double valor;         // Valor unitário da peça
    private String descricao;     // Descrição adicional sobre a peça
    private Long idOrcamento;     // Identificador do orçamento associado
    private Long idServico;       // Identificador do serviço associado

    /**
     * Construtor para inicializar uma Peça com todos os parâmetros.
     *
     * @param idPeca       O identificador único da peça.
     * @param marca        A marca da peça.
     * @param quantidade   A quantidade da peça.
     * @param valor        O valor unitário da peça.
     * @param descricao    A descrição adicional sobre a peça.
     * @param idOrcamento  O identificador do orçamento associado.
     * @param idServico    O identificador do serviço associado.
     */
    public Pecas(Long idPeca, String marca, int quantidade, double valor, String descricao, Long idOrcamento, Long idServico) {
        this.idPeca = idPeca;
        this.marca = marca;
        this.quantidade = quantidade;
        this.valor = valor;
        this.descricao = descricao;
        this.idOrcamento = idOrcamento;
        this.idServico = idServico;
    }

    // Getters e Setters

    /**
     * Obtém o ID da peça.
     *
     * @return O ID da peça.
     */
    public Long getIdPeca() {
        return idPeca;
    }

    /**
     * Define o ID da peça.
     *
     * @param idPeca O novo ID da peça.
     */
    public void setIdPeca(Long idPeca) {
        this.idPeca = idPeca;
    }

    /**
     * Obtém a marca da peça.
     *
     * @return A marca da peça.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca da peça.
     *
     * @param marca A nova marca da peça.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém a quantidade da peça.
     *
     * @return A quantidade da peça.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade da peça.
     *
     * @param quantidade A nova quantidade da peça.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém o valor unitário da peça.
     *
     * @return O valor unitário da peça.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Define o valor unitário da peça.
     *
     * @param valor O novo valor unitário da peça.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtém a descrição da peça.
     *
     * @return A descrição da peça.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição da peça.
     *
     * @param descricao A nova descrição da peça.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o ID do orçamento associado à peça.
     *
     * @return O ID do orçamento.
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento associado à peça.
     *
     * @param idOrcamento O novo ID do orçamento.
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o ID do serviço associado à peça.
     *
     * @return O ID do serviço.
     */
    public Long getIdServico() {
        return idServico;
    }

    /**
     * Define o ID do serviço associado à peça.
     *
     * @param idServico O novo ID do serviço.
     */
    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    /**
     * Exibe os detalhes da peça, incluindo marca, quantidade, valor e identificadores associados.
     */
    public void exibirDetalhes() {
        System.out.println("Peça ID: " + idPeca + ", Marca: " + marca + ", Quantidade: " + quantidade + ", Valor: " + valor);
        System.out.println("Descrição: " + descricao + ", ID Orçamento: " + idOrcamento + ", ID Serviço: " + idServico);
    }

    /**
     * Retorna uma representação textual dos detalhes da peça.
     *
     * @return String com os detalhes da peça.
     */
    @Override
    public String toString() {
        return "Pecas{" +
                "idPeca=" + idPeca +
                ", marca='" + marca + '\'' +
                ", quantidade=" + quantidade +
                ", valor=" + valor +
                ", descricao='" + descricao + '\'' +
                ", idOrcamento=" + idOrcamento +
                ", idServico=" + idServico +
                '}';
    }

    /**
     * Calcula o valor total da peça com base na quantidade e no valor unitário.
     *
     * @return O valor total calculado.
     */
    public double calcularValorTotal() {
        return this.valor * this.quantidade;
    }
}
