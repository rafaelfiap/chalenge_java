package br.com.fiap.models;

import java.sql.Timestamp;
import java.util.List;

/**
 * Classe que representa um Serviço.
 * Contém informações sobre o tipo de serviço, descrição, valor, tempo estimado, peças, veículo, falhas e orçamento relacionado ao serviço.
 *
 * @since 1.0
 * @version 1.3
 * @see Pecas
 * @see Veiculo
 * @see Falhas
 * @see Orcamento
 */
public class Servicos {
    private Long idServico;          // Identificador único do serviço
    private String tipoServico;      // Tipo do serviço (ex: Mecânico, Elétrico)
    private String descricao;        // Descrição detalhada do serviço
    private double valorServico;     // Valor do serviço
    private Timestamp tempoEstimado; // Tempo estimado para a conclusão do serviço
    private Pecas pecas;             // Peças relacionadas ao serviço
    private Veiculo veiculo;         // Veículo relacionado ao serviço
    private Falhas falhas;           // Falhas relacionadas ao serviço
    private Long idOrcamento;         // Identificador do orçamento associado ao serviço

    /**
     * Construtor completo para inicializar um Serviço com todos os parâmetros.
     *
     * @param idServico      O identificador único do serviço.
     * @param tipoServico    O tipo do serviço.
     * @param descricao      A descrição do serviço.
     * @param valorServico   O valor do serviço.
     * @param tempoEstimado  O tempo estimado para o serviço.
     * @param pecas          As peças relacionadas ao serviço.
     * @param veiculo        O veículo relacionado ao serviço.
     * @param falhas         As falhas relacionadas ao serviço.
     * @param idOrcamento    O identificador do orçamento associado ao serviço.
     * @since 1.0
     * @version 1.3
     */
    public Servicos(Long idServico, String tipoServico, String descricao, double valorServico, Timestamp tempoEstimado, Pecas pecas, Veiculo veiculo, Falhas falhas, Long idOrcamento) {
        this.idServico = idServico;
        this.tipoServico = tipoServico;
        this.descricao = descricao;
        this.valorServico = valorServico;
        this.tempoEstimado = tempoEstimado;
        this.pecas = pecas;
        this.veiculo = veiculo;
        this.falhas = falhas;
        this.idOrcamento = idOrcamento;
    }

    /**
     * Construtor simplificado para inicializar um Serviço sem peças, veículo e falhas.
     *
     * @param idServico      O identificador único do serviço.
     * @param tipoServico    O tipo do serviço.
     * @param descricao      A descrição do serviço.
     * @param valorServico   O valor do serviço.
     * @param tempoEstimado  O tempo estimado para o serviço.
     * @param idOrcamento    O identificador do orçamento associado ao serviço.
     */
    public Servicos(Long idServico, String tipoServico, String descricao, double valorServico, Timestamp tempoEstimado, Long idOrcamento) {
        this(idServico, tipoServico, descricao, valorServico, tempoEstimado, null, null, null, null);
    }

    public Servicos(Object o, String tipoServico, String descricao, double valorServico, Timestamp tempoEstimado) {
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
     * Obtém o tempo estimado para a conclusão do serviço.
     *
     * @return O tempo estimado para o serviço.
     */
    public Timestamp getTempoEstimado() {
        return tempoEstimado;
    }

    /**
     * Define o tempo estimado para a conclusão do serviço.
     *
     * @param tempoEstimado O novo tempo estimado.
     */
    public void setTempoEstimado(Timestamp tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    /**
     * Obtém as peças relacionadas ao serviço.
     *
     * @return As peças do serviço.
     */
    public Pecas getPecas() {
        return pecas;
    }

    /**
     * Define as peças relacionadas ao serviço.
     *
     * @param pecas As novas peças do serviço.
     */
    public void setPecas(Pecas pecas) {
        this.pecas = pecas;
    }

    /**
     * Obtém o veículo relacionado ao serviço.
     *
     * @return O veículo do serviço.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo relacionado ao serviço.
     *
     * @param veiculo O novo veículo do serviço.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Obtém as falhas relacionadas ao serviço.
     *
     * @return As falhas do serviço.
     */
    public Falhas getFalhas() {
        return falhas;
    }

    /**
     * Define as falhas relacionadas ao serviço.
     *
     * @param falhas As novas falhas do serviço.
     */
    public void setFalhas(Falhas falhas) {
        this.falhas = falhas;
    }

    /**
     * Obtém o ID do orçamento associado ao serviço.
     *
     * @return O ID do orçamento.
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento associado ao serviço.
     *
     * @param idOrcamento O novo ID do orçamento.
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Exibe os detalhes completos do serviço.
     */
    public void exibirDetalhes() {
        System.out.println("Serviço ID: " + idServico + ", Tipo: " + tipoServico + ", Descrição: " + descricao + ", Valor: " + valorServico);
        System.out.println("Tempo estimado: " + tempoEstimado);
        if (pecas != null) {
            System.out.println("Peças: " + pecas.getDescricao());
        }
        if (veiculo != null) {
            System.out.println("Veículo: " + veiculo.getMarca() + " " + veiculo.getModelo() + ", Placa: " + veiculo.getPlaca());
        }
        if (falhas != null) {
            falhas.exibirDetalhes();
        }
    }

    /**
     * Representação textual do objeto Servicos.
     *
     * @return Detalhes do serviço em formato de string.
     */
    @Override
    public String toString() {
        return "Servicos{" +
                "idServico=" + idServico +
                ", tipoServico='" + tipoServico + '\'' +
                ", descricao='" + descricao + '\'' +
                ", tempoEstimado=" + tempoEstimado +
                ", valorServico=" + valorServico +
                ", idOrcamento=" + idOrcamento +
                '}';
    }

    /**
     * Calcula a duração total dos serviços em minutos.
     *
     * @param servicos A lista de serviços para calcular a duração.
     * @return A duração total dos serviços em minutos.
     */
    public long calcularDuracaoTotalServicos(List<Servicos> servicos) {
        long duracaoTotal = 0;
        for (Servicos servico : servicos) {
            duracaoTotal += servico.getTempoEstimado().getTime();
        }
        return duracaoTotal / (1000 * 60); // Retorna a duração total em minutos
    }
}
