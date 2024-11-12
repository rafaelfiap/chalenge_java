package br.com.fiap.models;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Classe que representa um Agendamento.
 * Contém informações sobre a data, hora, cliente, oficina e veículo.
 * Permite operações de confirmação e cancelamento de agendamentos, além de verificação
 * de agendamentos futuros.
 *
 * @since 1.0
 * @version 1.2
 * @see Veiculo
 * @see Oficina
 * @see Cliente
 */
public class Agendamento {
    private Long idAgendamento; // Identificador único do agendamento
    private Date dataAgendamento; // Data do agendamento
    private Timestamp horaAgendamento; // Hora do agendamento
    private Veiculo veiculo; // Veículo relacionado ao agendamento
    private Long idCliente; // Identificador do cliente
    private Long idOficina; // Identificador da oficina

    /**
     * Construtor para inicializar um Agendamento com todos os parâmetros.
     *
     * @param idAgendamento   O ID exclusivo do agendamento.
     * @param dataAgendamento A data em que o agendamento ocorrerá.
     * @param horaAgendamento A hora do agendamento.
     * @param veiculo         O veículo relacionado ao agendamento.
     * @param idCliente       O ID do cliente associado ao agendamento.
     * @param idOficina       O ID da oficina onde o serviço será realizado.
     * @since 1.0
     * @version 1.2
     */
    public Agendamento(Long idAgendamento, Date dataAgendamento, Timestamp horaAgendamento, Veiculo veiculo, Long idCliente, Long idOficina) {
        this.idAgendamento = idAgendamento;
        this.dataAgendamento = dataAgendamento;
        this.horaAgendamento = horaAgendamento;
        this.veiculo = veiculo;
        this.idCliente = idCliente;
        this.idOficina = idOficina;
    }

    /**
     * Construtor para inicializar um Agendamento sem o veículo.
     * Utiliza outro construtor passando null para o veículo.
     *
     * @param idAgendamento   O ID exclusivo do agendamento.
     * @param dataAgendamento A data em que o agendamento ocorrerá.
     * @param horaAgendamento A hora do agendamento.
     * @param idCliente       O ID do cliente associado ao agendamento.
     * @param idOficina       O ID da oficina onde o serviço será realizado.
     * @since 1.0
     * @version 1.2
     */
    public Agendamento(Long idAgendamento, Date dataAgendamento, Timestamp horaAgendamento, Long idCliente, Long idOficina) {
        this(idAgendamento, dataAgendamento, horaAgendamento, null, idCliente, idOficina);  // Passa null automaticamente para o veículo
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do agendamento.
     *
     * @return O ID do agendamento.
     */
    public Long getIdAgendamento() {
        return idAgendamento;
    }

    /**
     * Define o ID exclusivo do agendamento.
     *
     * @param idAgendamento O novo ID do agendamento.
     */
    public void setIdAgendamento(Long idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    /**
     * Obtém a data do agendamento.
     *
     * @return A data do agendamento.
     */
    public Date getDataAgendamento() {
        return dataAgendamento;
    }

    /**
     * Define a data do agendamento.
     *
     * @param dataAgendamento A nova data do agendamento.
     */
    public void setDataAgendamento(Date dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    /**
     * Obtém a hora do agendamento.
     *
     * @return A hora do agendamento.
     */
    public Timestamp getHoraAgendamento() {
        return horaAgendamento;
    }

    /**
     * Define a hora do agendamento.
     *
     * @param horaAgendamento A nova hora do agendamento.
     */
    public void setHoraAgendamento(Timestamp horaAgendamento) {
        this.horaAgendamento = horaAgendamento;
    }

    /**
     * Obtém o veículo relacionado ao agendamento.
     *
     * @return O veículo do agendamento.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo relacionado ao agendamento.
     *
     * @param veiculo O novo veículo do agendamento.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Obtém o ID do cliente que fez o agendamento.
     *
     * @return O ID do cliente.
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID do cliente que fez o agendamento.
     *
     * @param idCliente O novo ID do cliente.
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtém o ID da oficina onde será realizado o serviço.
     *
     * @return O ID da oficina.
     */
    public Long getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina onde será realizado o serviço.
     *
     * @param idOficina O novo ID da oficina.
     */
    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Confirma o agendamento.
     * Exibe uma mensagem indicando que o agendamento foi confirmado.
     */
    public void confirmarAgendamento() {
        System.out.println("Agendamento confirmado para " + dataAgendamento + " às " + horaAgendamento + " para o veículo " + (veiculo != null ? veiculo.getModelo() : "sem veículo"));
    }

    /**
     * Cancela o agendamento.
     * Exibe uma mensagem indicando que o agendamento foi cancelado.
     */
    public void cancelarAgendamento() {
        System.out.println("Agendamento número " + idAgendamento + " foi cancelado.");
    }

    /**
     * Retorna uma representação em String do agendamento.
     * Inclui todos os campos principais: ID, data, hora, cliente e oficina.
     *
     * @return Representação em string do agendamento.
     */
    @Override
    public String toString() {
        return "Agendamento{" +
                "idAgendamento=" + idAgendamento +
                ", dataAgendamento=" + dataAgendamento +
                ", horaAgendamento=" + horaAgendamento +
                ", idCliente=" + idCliente +
                ", idOficina=" + idOficina +
                '}';
    }

    /**
     * Verifica se o agendamento é para uma data futura.
     *
     * @return true se o agendamento for no futuro, false caso contrário.
     */
    public boolean verificarAgendamentoFuturo() {
        return this.dataAgendamento.after(new Date());
    }

}
