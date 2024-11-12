package br.com.fiap.models;

/**
 * Classe que representa um Cliente, herdando de Pessoa.
 * Além dos atributos herdados de Pessoa, o Cliente possui um ID exclusivo e um Veículo associado.
 *
 * @since 1.0
 * @version 1.3
 * @see Pessoa
 * @see Veiculo
 */
public class Cliente extends Pessoa {
    private Long idCliente; // Identificador único do cliente
    private Veiculo veiculo; // Veículo associado ao cliente

    /**
     * Construtor completo para inicializar um Cliente com todos os atributos.
     *
     * @param idCliente O ID exclusivo do cliente.
     * @param cpf       O CPF do cliente.
     * @param nome      O nome do cliente.
     * @param endereco  O endereço do cliente.
     * @param telefone  O telefone do cliente.
     * @param email     O email do cliente.
     * @param sexo      O sexo do cliente ('M' para masculino, 'F' para feminino).
     * @param veiculo   O veículo associado ao cliente.
     */
    public Cliente(Long idCliente, String cpf, String nome, Endereco endereco, Telefone telefone, String email, char sexo, Veiculo veiculo) {
        super(cpf, nome, endereco, telefone, email, sexo);
        this.idCliente = idCliente;
        this.veiculo = veiculo;
    }

    /**
     * Construtor para inicializar um Cliente com atributos essenciais.
     *
     * @param idCliente O ID do cliente.
     * @param cpf       O CPF do cliente.
     * @param nome      O nome do cliente.
     * @param email     O email do cliente.
     * @param sexo      O sexo do cliente.
     */
    public Cliente(Long idCliente, String cpf, String nome, String email, char sexo) {
        this(idCliente, cpf, nome, null, null, email, sexo, null);
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do cliente.
     *
     * @return O ID do cliente.
     */
    public Long getIdCliente() {
        return idCliente;
    }

    /**
     * Define o ID exclusivo do cliente.
     *
     * @param idCliente O novo ID do cliente.
     */
    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtém o veículo associado ao cliente.
     *
     * @return O veículo do cliente.
     */
    public Veiculo getVeiculo() {
        return veiculo;
    }

    /**
     * Define o veículo associado ao cliente.
     *
     * @param veiculo O novo veículo do cliente.
     */
    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    /**
     * Exibe os detalhes do cliente.
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("Cliente: " + getNome() + ", CPF: " + getCpf() + ", ID: " + idCliente + ", Email: " + getEmail() + ", Sexo: " + getSexo());
    }

    /**
     * Gera uma representação textual do objeto Cliente.
     *
     * @return Uma string com os detalhes do cliente.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", sexo=" + getSexo() +
                ", veiculo=" + (veiculo != null ? veiculo.toString() : "Nenhum") +
                '}';
    }
}
