package br.com.fiap.models;

/**
 * Classe que representa um Funcionario, herdando de Pessoa.
 * Além dos atributos herdados de Pessoa, o Funcionario possui uma função associada, um ID exclusivo e uma referência à oficina onde trabalha.
 *
 * @since 1.0
 * @version 1.4
 * @see Pessoa
 */
public class Funcionario extends Pessoa {
    private Long idFuncionario;  // Identificador único do funcionário
    private String funcao;       // Função desempenhada pelo funcionário
    private int idOficina;       // Identificador da oficina associada ao funcionário

    /**
     * Construtor completo para inicializar um Funcionario com todos os atributos.
     *
     * @param idFuncionario O ID exclusivo do funcionário.
     * @param cpf           O CPF do funcionário.
     * @param nome          O nome do funcionário.
     * @param endereco      O endereço do funcionário.
     * @param telefone      O telefone do funcionário.
     * @param email         O email do funcionário.
     * @param sexo          O sexo do funcionário ('M' para masculino, 'F' para feminino).
     * @param funcao        A função desempenhada pelo funcionário.
     * @param idOficina     O ID da oficina onde o funcionário trabalha.
     */
    public Funcionario(Long idFuncionario, String cpf, String nome, Endereco endereco, Telefone telefone, String email, char sexo, String funcao, int idOficina) {
        super(cpf, nome, endereco, telefone, email, sexo);
        this.idFuncionario = idFuncionario;
        this.funcao = funcao;
        this.idOficina = idOficina;
    }

    /**
     * Construtor para inicializar um Funcionario com atributos essenciais.
     *
     * @param idFuncionario O ID do funcionário.
     * @param cpf           O CPF do funcionário.
     * @param nome          O nome do funcionário.
     * @param sexo          O sexo do funcionário.
     * @param funcao        A função desempenhada pelo funcionário.
     * @param idOficina     O ID da oficina associada ao funcionário.
     */
    public Funcionario(Long idFuncionario, String cpf, String nome, char sexo, String funcao, int idOficina) {
        this(idFuncionario, cpf, nome, null, null, null, sexo, funcao, idOficina);
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do funcionário.
     *
     * @return O ID do funcionário.
     */
    public Long getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Define o ID exclusivo do funcionário.
     *
     * @param idFuncionario O novo ID do funcionário.
     */
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Obtém a função desempenhada pelo funcionário.
     *
     * @return A função do funcionário.
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * Define a função desempenhada pelo funcionário.
     *
     * @param funcao A nova função do funcionário.
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * Obtém o ID da oficina associada ao funcionário.
     *
     * @return O ID da oficina.
     */
    public int getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina associada ao funcionário.
     *
     * @param idOficina O novo ID da oficina.
     */
    public void setIdOficina(int idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Exibe os detalhes do funcionário.
     */
    @Override
    public void exibirDetalhes() {
        System.out.println("Funcionário: " + getNome() + ", CPF: " + getCpf() + ", Função: " + funcao + ", ID da Oficina: " + idOficina);
    }

    /**
     * Gera uma representação textual do objeto Funcionario.
     *
     * @return Uma string com os detalhes do funcionário.
     */
    @Override
    public String toString() {
        return "Funcionario{" +
                "idFuncionario=" + idFuncionario +
                ", cpf='" + getCpf() + '\'' +
                ", nome='" + getNome() + '\'' +
                ", funcao='" + funcao + '\'' +
                ", sexo=" + getSexo() +
                ", idOficina=" + idOficina +
                '}';
    }
}
