package br.com.fiap.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

/**
 * DTO para transferência de dados da entidade Cliente.
 *
 * <p>A classe `ClienteDto` é usada para transferir dados do cliente entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, CPF, nome,
 * email, sexo e o veículo associado ao cliente.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class ClienteDto {

    // Identificador único do cliente, utilizado para operações de atualização e deleção.
    private Long idCliente;

    // CPF do cliente, utilizado em operações de identificação.
    private String cpf;

    // Nome do cliente, utilizado em operações de criação e atualização.
    private String nome;

    // Email do cliente.
    private String email;

    // Sexo do cliente ('M' para masculino, 'F' para feminino).
    private char sexo;

    // Identificador do veículo associado ao cliente.
    private Long idVeiculo;

    // Getters e Setters

    /**
     * Obtém o ID do cliente.
     *
     * @return O ID do cliente.
     * @since 1.0
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID do cliente.
     *
     * @param idCliente O novo ID do cliente.
     * @since 1.0
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtém o CPF do cliente.
     *
     * @return O CPF do cliente.
     * @since 1.0
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do cliente.
     *
     * @param cpf O novo CPF do cliente.
     * @since 1.0
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o nome do cliente.
     *
     * @return O nome do cliente.
     * @since 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do cliente.
     *
     * @param nome O novo nome do cliente.
     * @since 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email do cliente.
     *
     * @return O email do cliente.
     * @since 1.0
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email do cliente.
     *
     * @param email O novo email do cliente.
     * @since 1.0
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o sexo do cliente.
     *
     * @return O sexo do cliente ('M' para masculino, 'F' para feminino).
     * @since 1.0
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Define o sexo do cliente.
     *
     * @param sexo O novo sexo do cliente.
     * @since 1.0
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtém o ID do veículo associado ao cliente.
     *
     * @return O ID do veículo associado.
     * @since 1.0
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo associado ao cliente.
     *
     * @param idVeiculo O novo ID do veículo associado.
     * @since 1.0
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Retorna uma representação textual do objeto ClienteDto.
     *
     * @return Uma string com os detalhes do ClienteDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "ClienteDto{" +
                "idCliente=" + idCliente +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", sexo=" + sexo +
                ", idVeiculo=" + idVeiculo +
                '}';
    }
}
