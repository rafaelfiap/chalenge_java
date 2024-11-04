package br.com.fiap.dtos;

import java.sql.Timestamp;

/**
 * DTO para transferência de dados da entidade Serviço.
 *
 * <p>A classe `ServicosDto` é usada para transferir dados do serviço entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o tipo, descrição,
 * valor do serviço, tempo estimado, peças, veículo, falhas e orçamento relacionado ao serviço.</p>
 *
 * @version 1.3
 * @since 1.0
 */
public class ServicosDto {

    private Long idServico;         // Identificador único do serviço
    private String tipoServico;     // Tipo do serviço (ex: Mecânico, Elétrico)
    private String descricao;       // Descrição detalhada do serviço
    private double valorServico;    // Valor do serviço
    private Timestamp tempoEstimado; // Tempo estimado para a conclusão do serviço
    private int idPecas;            // Identificador das peças relacionadas ao serviço
    private int idVeiculo;          // Identificador do veículo relacionado ao serviço
    private int idFalhas;           // Identificador das falhas relacionadas ao serviço
    private int idOrcamento;        // Identificador do orçamento associado ao serviço

    // Construtor

    /**
     * Construtor para inicializar um DTO de Serviço com todos os parâmetros.
     *
     * @param idServico     O identificador único do serviço.
     * @param tipoServico   O tipo do serviço.
     * @param descricao     A descrição do serviço.
     * @param valorServico  O valor do serviço.
     * @param tempoEstimado O tempo estimado para o serviço.
     * @param idPecas       O identificador das peças associadas ao serviço.
     * @param idVeiculo     O identificador do veículo relacionado ao serviço.
     * @param idFalhas      O identificador das falhas relacionadas ao serviço.
     * @param idOrcamento   O identificador do orçamento associado ao serviço.
     */
    public ServicosDto(Long idServico, String tipoServico, String descricao, double valorServico, Timestamp tempoEstimado, int idPecas, int idVeiculo, int idFalhas, int idOrcamento) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.descricao = descricao;
        this.valorServico = valorServico;
        this.tempoEstimado = tempoEstimado;
        this.idPecas = idPecas;
        this.idVeiculo = idVeiculo;
        this.idFalhas = idFalhas;
        this.idOrcamento = idOrcamento;
    }

    // Getters e Setters

    /**
     * Obtém o ID do serviço.
     *
     * @return O ID do serviço.
     */
    public Long getIdServico() {
        return idServico;
    }

    /**
     * Define o ID do serviço.
     *
     * @param idServico O novo ID do serviço.
     */
    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    /**
     * Obtém o tipo do serviço.
     *
     * @return O tipo do serviço.
     */
    public String getTipoServico() {
        return tipoServico;
    }

    /**
     * Define o tipo do serviço.
     *
     * @param tipoServico O novo tipo do serviço.
     */
    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    /**
     * Obtém a descrição do serviço.
     *
     * @return A descrição do serviço.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Define a descrição do serviço.
     *
     * @param descricao A nova descrição do serviço.
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Obtém o valor do serviço.
     *
     * @return O valor do serviço.
     */
    public double getValorServico() {
        return valorServico;
    }

    /**
     * Define o valor do serviço.
     *
     * @param valorServico O novo valor do serviço.
     */
    public void setValorServico(double valorServico) {
        this.valorServico = valorServico;
    }

    /**
     * Obtém o tempo estimado para o serviço.
     *
     * @return O tempo estimado para o serviço.
     */
    public Timestamp getTempoEstimado() {
        return tempoEstimado;
    }

    /**
     * Define o tempo estimado para o serviço.
     *
     * @param tempoEstimado O novo tempo estimado.
     */
    public void setTempoEstimado(Timestamp tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    /**
     * Obtém o ID das peças associadas ao serviço.
     *
     * @return O ID das peças.
     */
    public int getIdPecas() {
        return idPecas;
    }

    /**
     * Define o ID das peças associadas ao serviço.
     *
     * @param idPecas O novo ID das peças.
     */
    public void setIdPecas(int idPecas) {
        this.idPecas = idPecas;
    }

    /**
     * Obtém o ID do veículo associado ao serviço.
     *
     * @return O ID do veículo.
     */
    public int getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo associado ao serviço.
     *
     * @param idVeiculo O novo ID do veículo.
     */
    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém o ID das falhas associadas ao serviço.
     *
     * @return O ID das falhas.
     */
    public int getIdFalhas() {
        return idFalhas;
    }

    /**
     * Define o ID das falhas associadas ao serviço.
     *
     * @param idFalhas O novo ID das falhas.
     */
    public void setIdFalhas(int idFalhas) {
        this.idFalhas = idFalhas;
    }

    /**
     * Obtém o ID do orçamento associado ao serviço.
     *
     * @return O ID do orçamento.
     */
    public int getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento associado ao serviço.
     *
     * @param idOrcamento O novo ID do orçamento.
     */
    public void setIdOrcamento(int idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Representação textual do DTO de Serviço.
     *
     * @return Detalhes do serviço em formato de string.
     */
    @Override
    public String toString() {
        return "ServicosDto{" +
                "idServico=" + idServico +
                ", tipoServico='" + tipoServico + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valorServico=" + valorServico +
                ", tempoEstimado=" + tempoEstimado +
                ", idPecas=" + idPecas +
                ", idVeiculo=" + idVeiculo +
                ", idFalhas=" + idFalhas +
                ", idOrcamento=" + idOrcamento +
                '}';
    }
}
