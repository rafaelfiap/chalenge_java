package br.com.fiap.dtos;

import java.sql.Timestamp;
import java.util.Date;

/**
 * DTO para transferência de dados da entidade Agendamento.
 *
 * <p>A classe `AgendamentoDto` é usada para transferir dados do agendamento entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, data, hora,
 * ID do cliente, ID da oficina e ID do veículo associado ao agendamento.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class AgendamentoDto {

    // Identificador único do agendamento, utilizado para operações de atualização e deleção.
    private Long idAgendamento;

    // Data do agendamento.
    private Date dataAgendamento;

    // Hora do agendamento.
    private Timestamp horaAgendamento;

    // Identificador do cliente associado ao agendamento.
    private Long idCliente;

    // Identificador da oficina onde o serviço será realizado.
    private Long idOficina;

    // Identificador do veículo associado ao agendamento.
    private Long idVeiculo;

    // Getters e Setters

    /**
     * Obtém o ID do agendamento.
     *
     * @return O ID do agendamento.
     * @since 1.0
     */
    public Long getIdAgendamento() {
        return idAgendamento;
    }

    /**
     * Define o ID do agendamento.
     *
     * @param idAgendamento O novo ID do agendamento.
     * @since 1.0
     */
    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    /**
     * Obtém a data do agendamento.
     *
     * @return A data do agendamento.
     * @since 1.0
     */
    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    /**
     * Define a data do agendamento.
     *
     * @param dataAgendamento A nova data do agendamento.
     * @since 1.0
     */
    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    /**
     * Obtém a hora do agendamento.
     *
     * @return A hora do agendamento.
     * @since 1.0
     */
    public Timestamp getHoraAgendamento() {
        return horaAgendamento;
    }

    /**
     * Define a hora do agendamento.
     *
     * @param horaAgendamento A nova hora do agendamento.
     * @since 1.0
     */
    public void setHoraAgendamento(Timestamp horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    /**
     * Obtém o ID do cliente associado ao agendamento.
     *
     * @return O ID do cliente.
     * @since 1.0
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID do cliente associado ao agendamento.
     *
     * @param idCliente O novo ID do cliente.
     * @since 1.0
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtém o ID da oficina onde o serviço será realizado.
     *
     * @return O ID da oficina.
     * @since 1.0
     */
    public Long getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina onde o serviço será realizado.
     *
     * @param idOficina O novo ID da oficina.
     * @since 1.0
     */
    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Obtém o ID do veículo associado ao agendamento.
     *
     * @return O ID do veículo.
     * @since 1.0
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo associado ao agendamento.
     *
     * @param idVeiculo O novo ID do veículo.
     * @since 1.0
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Retorna uma representação textual do objeto AgendamentoDto.
     *
     * @return Uma string com os detalhes do AgendamentoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "AgendamentoDto{" +
                "idAgendamento=" + idAgendamento +
                ", dataAgendamento=" + dataAgendamento +
                ", horaAgendamento=" + horaAgendamento +
                ", idCliente=" + idCliente +
                ", idOficina=" + idOficina +
                ", idVeiculo=" + idVeiculo +
                '}';
    }
}
