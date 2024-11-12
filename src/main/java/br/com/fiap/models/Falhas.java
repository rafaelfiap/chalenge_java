package br.com.fiap.models;

/**
 * Classe que representa uma Falha detectada em um veículo.
 * Contém informações sobre a falha, solução, orçamento relacionado, veículo e gravidade da falha.
 *
 * @since 1.0
 * @version 1.2
 * @see Veiculo
 * @see Orcamento
 */
public class Falhas {
    private Long idFalha; // Identificador único da falha
    private String descricaoFalha; // Descrição da falha detectada
    private String descricaoSolucao; // Solução proposta para a falha
    private Long idOrcamento; // Identificador do orçamento relacionado à falha
    private Long idVeiculo; // Identificador do veículo associado à falha
    private String gravidade; // Gravidade da falha (Baixa, Média, Alta)

    /**
     * Construtor completo para inicializar uma Falha com todos os atributos.
     *
     * @param idFalha         O identificador único da falha.
     * @param descricaoFalha  A descrição da falha detectada.
     * @param descricaoSolucao A solução proposta para a falha.
     * @param idOrcamento     O identificador do orçamento relacionado à falha.
     * @param idVeiculo       O identificador do veículo associado à falha.
     * @param gravidade       A gravidade da falha (Baixa, Média, Alta).
     * @since 1.0
     * @version 1.2
     */
    public Falhas(Long idFalha, String descricaoFalha, String descricaoSolucao, Long idOrcamento, Long idVeiculo, String gravidade) {
        this.idFalha = idFalha;
        this.descricaoFalha = descricaoFalha;
        this.descricaoSolucao = descricaoSolucao;
        this.idOrcamento = idOrcamento;
        this.idVeiculo = idVeiculo;
        this.gravidade = gravidade;
    }

    // Getters e Setters

    /**
     * Obtém o identificador único da falha.
     *
     * @return O ID da falha.
     */
    public Long getIdFalha() {
        return idFalha;
    }

    /**
     * Define o identificador único da falha.
     *
     * @param idFalha O novo ID da falha.
     */
    public void setIdFalha(Long idFalha) {
        this.idFalha = idFalha;
    }

    /**
     * Obtém a descrição da falha detectada.
     *
     * @return A descrição da falha.
     */
    public String getDescricaoFalha() {
        return descricaoFalha;
    }

    /**
     * Define a descrição da falha detectada.
     *
     * @param descricaoFalha A nova descrição da falha.
     */
    public void setDescricaoFalha(String descricaoFalha) {
        this.descricaoFalha = descricaoFalha;
    }

    /**
     * Obtém a solução proposta para a falha.
     *
     * @return A solução da falha.
     */
    public String getDescricaoSolucao() {
        return descricaoSolucao;
    }

    /**
     * Define a solução proposta para a falha.
     *
     * @param descricaoSolucao A nova solução da falha.
     */
    public void setDescricaoSolucao(String descricaoSolucao) {
        this.descricaoSolucao = descricaoSolucao;
    }

    /**
     * Obtém o identificador do orçamento relacionado à falha.
     *
     * @return O ID do orçamento.
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o identificador do orçamento relacionado à falha.
     *
     * @param idOrcamento O novo ID do orçamento.
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o identificador do veículo associado à falha.
     *
     * @return O ID do veículo.
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o identificador do veículo associado à falha.
     *
     * @param idVeiculo O novo ID do veículo.
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém a gravidade da falha (Baixa, Média, Alta).
     *
     * @return A gravidade da falha.
     */
    public String getGravidade() {
        return gravidade;
    }

    /**
     * Define a gravidade da falha (Baixa, Média, Alta).
     *
     * @param gravidade A nova gravidade da falha.
     */
    public void setGravidade(String gravidade) {
        this.gravidade = gravidade;
    }

    /**
     * Exibe os detalhes da falha.
     */
    public void exibirDetalhes() {
        System.out.println("Falha: " + descricaoFalha + ", Solução: " + descricaoSolucao + ", Gravidade: " + gravidade);
    }

    /**
     * Gera uma representação textual do objeto Falha.
     *
     * @return Uma string com os detalhes da falha.
     */
    @Override
    public String toString() {
        return "Falhas{" +
                "idFalha=" + idFalha +
                ", descricaoFalha='" + descricaoFalha + '\'' +
                ", descricaoSolucao='" + descricaoSolucao + '\'' +
                ", idOrcamento=" + idOrcamento +
                ", idVeiculo=" + idVeiculo +
                ", gravidade='" + gravidade + '\'' +
                '}';
    }

    /**
     * Verifica se a gravidade da falha é alta.
     *
     * @return true se a gravidade for "Alta", false caso contrário.
     */
    public boolean verificarGravidadeAlta() {
        return "Alta".equalsIgnoreCase(this.gravidade);
    }

}
