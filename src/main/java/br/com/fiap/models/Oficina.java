package br.com.fiap.models;

/**
 * Classe que representa uma Oficina.
 * Contém informações sobre o nome da oficina, telefone e o endereço.
 *
 * @since 1.0
 * @version 1.2
 */
public class Oficina {
    private Long idOficina;  // ID exclusivo da oficina
    private String cnpj;     // CNPJ da oficina
    private String nome;     // Nome da oficina
    private String email;    // Email da oficina
    private Telefone telefone; // Telefone da oficina
    private Endereco endereco; // Endereço associado à oficina

    /**
     * Construtor completo para inicializar uma Oficina com todos os atributos.
     *
     * @param idOficina O ID exclusivo da oficina.
     * @param cnpj O CNPJ da oficina.
     * @param nome O nome da oficina.
     * @param email O email da oficina.
     * @param telefone O telefone da oficina.
     * @param endereco O endereço da oficina.
     * @version 1.2
     * @since 1.0
     */
    public Oficina(Long idOficina, String cnpj, String nome, String email, Telefone telefone, Endereco endereco) {
        this.idOficina = idOficina;
        this.cnpj = cnpj;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    /**
     * Construtor alternativo para inicializar uma Oficina sem telefone e endereço.
     *
     * @param idOficina O ID exclusivo da oficina.
     * @param cnpj O CNPJ da oficina.
     * @param nome O nome da oficina.
     * @param email O email da oficina.
     */
    public Oficina(Long idOficina, String cnpj, String nome, String email) {
        this(idOficina, cnpj, nome, email, null, null);  // Atribui null para telefone e endereço
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo da oficina.
     * @return ID da oficina.
     */
    public Long getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID exclusivo da oficina.
     * @param idOficina O novo ID da oficina.
     */
    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Obtém o CNPJ da oficina.
     * @return CNPJ da oficina.
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ da oficina.
     * @param cnpj O novo CNPJ da oficina.
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Obtém o nome da oficina.
     * @return Nome da oficina.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da oficina.
     * @param nome O novo nome da oficina.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email da oficina.
     * @return Email da oficina.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email da oficina.
     * @param email O novo email da oficina.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o telefone da oficina.
     * @return Telefone da oficina.
     */
    public Telefone getTelefone() {
        return telefone;
    }

    /**
     * Define o telefone da oficina.
     * @param telefone O novo telefone da oficina.
     */
    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    /**
     * Obtém o endereço da oficina.
     * @return Endereço da oficina.
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * Define o endereço da oficina.
     * @param endereco O novo endereço da oficina.
     */
    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    /**
     * Exibe os detalhes completos da oficina.
     * Inclui nome, CNPJ, email, telefone e, se disponível, o endereço.
     */
    public void exibirDetalhes() {
        System.out.println("Oficina: " + nome + ", CNPJ: " + cnpj + ", Email: " + email + ", Telefone: " + (telefone != null ? telefone.getNumero() : "Não informado"));
        if (endereco != null) {
            endereco.exibirDetalhes();
        } else {
            System.out.println("Endereço não informado.");
        }
    }

    /**
     * Gera uma representação textual do objeto Oficina.
     * @return String com os principais atributos da oficina.
     */
    @Override
    public String toString() {
        return "Oficina{" +
                "idOficina=" + idOficina +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
