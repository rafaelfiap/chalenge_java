package br.com.fiap.models;

/**
 * Classe abstrata que representa uma Pessoa com atributos comuns como CPF, nome, endereço, telefone, email e sexo.
 * Essa classe é a base para outras entidades, como Cliente ou Funcionário.
 * @since 1.0
 * @version 1.3
 * @see Endereco
 * @see Telefone
 */
public abstract class Pessoa {
    private String cpf;  // CPF da pessoa
    private String nome;  // Nome da pessoa
    private Endereco endereco;  // Endereço da pessoa
    private Telefone telefone;  // Telefone da pessoa
    private String email;  // Email da pessoa
    private char sexo;  // Sexo da pessoa ('M' para Masculino, 'F' para Feminino)

    /**
     * Construtor para inicializar uma Pessoa com todos os atributos.
     * @param cpf O CPF da pessoa.
     * @param nome O nome da pessoa.
     * @param endereco O endereço da pessoa.
     * @param telefone O telefone da pessoa.
     * @param email O email da pessoa.
     * @param sexo O sexo da pessoa ('M' para masculino, 'F' para feminino).
     */
    public Pessoa(String cpf, String nome, Endereco endereco, Telefone telefone, String email, char sexo) {
        this.cpf = cpf;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
    }

    // Getters e Setters

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Método abstrato para exibir detalhes da pessoa.
     * As classes que herdam de Pessoa devem implementar esse método.
     */
    public abstract void exibirDetalhes();
}
