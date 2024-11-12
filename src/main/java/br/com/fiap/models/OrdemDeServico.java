package br.com.fiap.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Classe que representa uma Ordem de Serviço.
 * Contém informações sobre o status, veículo, orçamento, funcionário e os horários de início e fim da ordem.
 *
 * @since 1.0
 * @version 1.1
 * @see Veiculo
 * @see Orcamento
 * @see Funcionario
 */
public class OrdemDeServico {
    private Long idOs;             // Identificador único da ordem de serviço
    private String status;          // Status da ordem de serviço
    private Long idOrcamento;       // ID do orçamento associado à ordem de serviço
    private Long idFuncionario;     // ID do funcionário responsável
    private Long idVeiculo;         // ID do veículo relacionado à ordem de serviço
    private Date dataInicio;        // Data de início da ordem de serviço
    private Date dataFim;           // Data de término da ordem de serviço
    private Timestamp horaInicio;   // Hora de início da ordem de serviço
    private Timestamp horaFim;      // Hora de término da ordem de serviço

    /**
     * Construtor completo para inicializar uma Ordem de Serviço.
     *
     * @param idOs         O ID exclusivo da ordem de serviço.
     * @param status       O status da ordem de serviço.
     * @param idOrcamento  O ID do orçamento associado.
     * @param idFuncionario O ID do funcionário responsável.
     * @param idVeiculo    O ID do veículo associado.
     * @param dataInicio   A data de início da ordem de serviço.
     * @param dataFim      A data de fim da ordem de serviço (pode ser nula).
     * @param horaInicio   A hora de início da ordem de serviço.
     * @param horaFim      A hora de fim da ordem de serviço (pode ser nula).
     */
    public OrdemDeServico(Long idOs, String status, Long idOrcamento, Long idFuncionario, Long idVeiculo, Date dataInicio, Date dataFim, Timestamp horaInicio, Timestamp horaFim) {
        this.idOs = idOs;
        this.status = status;
        this.idOrcamento = idOrcamento;
        this.idFuncionario = idFuncionario;
        this.idVeiculo = idVeiculo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo da ordem de serviço.
     *
     * @return O ID da ordem de serviço.
     */
    public Long getIdOs() {
        return idOs;
    }

    /**
     * Define o ID exclusivo da ordem de serviço.
     *
     * @param idOs O novo ID da ordem de serviço.
     */
    public void setIdOs(Long idOs) {
        this.idOs = idOs;
    }

    /**
     * Obtém o status da ordem de serviço.
     *
     * @return O status da ordem de serviço.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status da ordem de serviço.
     *
     * @param status O novo status da ordem de serviço.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o ID do orçamento associado à ordem de serviço.
     *
     * @return O ID do orçamento.
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento associado à ordem de serviço.
     *
     * @param idOrcamento O novo ID do orçamento.
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o ID do funcionário responsável pela ordem de serviço.
     *
     * @return O ID do funcionário.
     */
    public Long getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Define o ID do funcionário responsável pela ordem de serviço.
     *
     * @param idFuncionario O novo ID do funcionário.
     */
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Obtém o ID do veículo relacionado à ordem de serviço.
     *
     * @return O ID do veículo.
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo relacionado à ordem de serviço.
     *
     * @param idVeiculo O novo ID do veículo.
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém a data de início da ordem de serviço.
     *
     * @return A data de início.
     */
    public java.sql.Date getDataInicio() {
        return (java.sql.Date) dataInicio;
    }

    /**
     * Define a data de início da ordem de serviço.
     *
     * @param dataInicio A nova data de início.
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Obtém a data de término da ordem de serviço.
     *
     * @return A data de término.
     */
    public java.sql.Date getDataFim() {
        return (java.sql.Date) dataFim;
    }

    /**
     * Define a data de término da ordem de serviço.
     *
     * @param dataFim A nova data de término.
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Obtém a hora de início da ordem de serviço.
     *
     * @return A hora de início.
     */
    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    /**
     * Define a hora de início da ordem de serviço.
     *
     * @param horaInicio A nova hora de início.
     */
    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtém a hora de término da ordem de serviço.
     *
     * @return A hora de término.
     */
    public Timestamp getHoraFim() {
        return horaFim;
    }

    /**
     * Define a hora de término da ordem de serviço.
     *
     * @param horaFim A nova hora de término.
     */
    public void setHoraFim(Timestamp horaFim) {
        this.horaFim = horaFim;
    }

    /**
     * Exibe os detalhes completos da ordem de serviço.
     */
    public void exibirDetalhes() {
        System.out.println("Ordem de Serviço ID: " + idOs + ", Status: " + status);
        System.out.println("ID do Orçamento: " + idOrcamento + ", ID do Funcionário: " + idFuncionario + ", ID do Veículo: " + idVeiculo);
        System.out.println("Data Início: " + dataInicio + ", Hora Início: " + horaInicio);
        if (dataFim != null) {
            System.out.println("Data Fim: " + dataFim + ", Hora Fim: " + horaFim);
        }
    }

    /**
     * Representação textual da Ordem de Serviço.
     *
     * @return Detalhes da ordem de serviço em formato de string.
     */
    @Override
    public String toString() {
        return "OrdemDeServico{" +
                "idOs=" + idOs +
                ", status='" + status + '\'' +
                ", idOrcamento=" + idOrcamento +
                ", idFuncionario=" + idFuncionario +
                ", idVeiculo=" + idVeiculo +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", horaInicio=" + horaInicio +
                ", horaFim=" + horaFim +
                '}';
    }

    /**
     * Finaliza a ordem de serviço, mudando seu status para "Concluído" e registrando a data de fim.
     */
    public void finalizarOrdem() {
        if (!"Aberto".equalsIgnoreCase(this.status)) {
            throw new IllegalStateException("Ordem já finalizada");
        }
        this.status = "Concluído";
        this.dataFim = new Date(System.currentTimeMillis());
        this.horaFim = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Verifica se a ordem de serviço foi concluída.
     *
     * @return true se a ordem estiver com status "Concluído", false caso contrário.
     */
    public boolean verificarConclusao() {
        return "Concluído".equalsIgnoreCase(this.status);
    }

    /**
     * Calcula o tempo total de execução da ordem de serviço.
     *
     * @return O tempo total de execução em minutos.
     */
    public long calcularTempoTotalExecucao() {
        if (horaFim != null && horaInicio != null) {
            return (horaFim.getTime() - horaInicio.getTime()) / (1000 * 60); // Tempo em minutos
        }
        return 0;
    }
}
