package br.com.fiap.dtos;

import java.sql.Timestamp;
import java.util.Date;

/**
 * DTO para transferência de dados da entidade Ordem de Serviço.
 *
 * <p>A classe `OrdemDeServicoDto` é usada para transferir dados da ordem de serviço entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, status, IDs associados
 * de orçamento, funcionário, veículo, data e hora de início e fim.</p>
 *
 * @version 1.1
 * @since 1.0
 */
public class OrdemDeServicoDto {

    // Identificador único da ordem de serviço
    private Long idOs;

    // Status da ordem de serviço (e.g., "Aberto", "Concluído")
    private String status;

    // Identificador do orçamento associado à ordem de serviço
    private Long idOrcamento;

    // Identificador do funcionário responsável pela ordem de serviço
    private Long idFuncionario;

    // Identificador do veículo associado à ordem de serviço
    private Long idVeiculo;

    // Data de início da ordem de serviço
    private Date dataInicio;

    // Data de término da ordem de serviço
    private Date dataFim;

    // Hora de início da ordem de serviço
    private Timestamp horaInicio;

    // Hora de término da ordem de serviço
    private Timestamp horaFim;

    // Getters e Setters

    /**
     * Obtém o ID da ordem de serviço.
     *
     * @return O ID da ordem de serviço.
     * @since 1.0
     */
    public Long getIdOs() {
        return idOs;
    }

    /**
     * Define o ID da ordem de serviço.
     *
     * @param idOs O novo ID da ordem de serviço.
     * @since 1.0
     */
    public void setIdOs(Long idOs) {
        this.idOs = idOs;
    }

    /**
     * Obtém o status da ordem de serviço.
     *
     * @return O status da ordem de serviço.
     * @since 1.0
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define o status da ordem de serviço.
     *
     * @param status O novo status da ordem de serviço.
     * @since 1.0
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Obtém o ID do orçamento associado à ordem de serviço.
     *
     * @return O ID do orçamento.
     * @since 1.0
     */
    public Long getIdOrcamento() {
        return idOrcamento;
    }

    /**
     * Define o ID do orçamento associado à ordem de serviço.
     *
     * @param idOrcamento O novo ID do orçamento.
     * @since 1.0
     */
    public void setIdOrcamento(Long idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    /**
     * Obtém o ID do funcionário responsável pela ordem de serviço.
     *
     * @return O ID do funcionário.
     * @since 1.0
     */
    public Long getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Define o ID do funcionário responsável pela ordem de serviço.
     *
     * @param idFuncionario O novo ID do funcionário.
     * @since 1.0
     */
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Obtém o ID do veículo associado à ordem de serviço.
     *
     * @return O ID do veículo.
     * @since 1.0
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo associado à ordem de serviço.
     *
     * @param idVeiculo O novo ID do veículo.
     * @since 1.0
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém a data de início da ordem de serviço.
     *
     * @return A data de início.
     * @since 1.0
     */
    public Date getDataInicio() {
        return dataInicio;
    }

    /**
     * Define a data de início da ordem de serviço.
     *
     * @param dataInicio A nova data de início.
     * @since 1.0
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Obtém a data de término da ordem de serviço.
     *
     * @return A data de término.
     * @since 1.0
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * Define a data de término da ordem de serviço.
     *
     * @param dataFim A nova data de término.
     * @since 1.0
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Obtém a hora de início da ordem de serviço.
     *
     * @return A hora de início.
     * @since 1.0
     */
    public Timestamp getHoraInicio() {
        return horaInicio;
    }

    /**
     * Define a hora de início da ordem de serviço.
     *
     * @param horaInicio A nova hora de início.
     * @since 1.0
     */
    public void setHoraInicio(Timestamp horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * Obtém a hora de término da ordem de serviço.
     *
     * @return A hora de término.
     * @since 1.0
     */
    public Timestamp getHoraFim() {
        return horaFim;
    }

    /**
     * Define a hora de término da ordem de serviço.
     *
     * @param horaFim A nova hora de término.
     * @since 1.0
     */
    public void setHoraFim(Timestamp horaFim) {
        this.horaFim = horaFim;
    }

    /**
     * Representação textual do DTO de Ordem de Serviço.
     *
     * @return Detalhes da ordem de serviço em formato de string.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "OrdemDeServicoDto{" +
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
}
