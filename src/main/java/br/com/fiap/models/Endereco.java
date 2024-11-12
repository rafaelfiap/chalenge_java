package br.com.fiap.models;

/**
 * Classe que representa o Endereço.
 * Contém informações como logradouro, número, CEP, bairro, cidade e estado, além de um ID exclusivo e uma referência a uma entidade associada.
 *
 * @since 1.0
 * @version 1.2
 */
public class Endereco {
    private Long idEndereco;  // Identificador único do endereço
    private String logradouro;  // Nome da rua ou avenida
    private int numero;  // Número do local
    private String cep;  // Código postal
    private String bairro;  // Nome do bairro
    private String cidade;  // Nome da cidade
    private String uf;  // Unidade federativa (estado)
    private int idReferencia;  // ID que referencia a entidade associada ao endereço (ex: Cliente, Funcionário, etc.)

    /**
     * Construtor completo para inicializar um Endereço com todos os atributos.
     *
     * @param idEndereco   O ID exclusivo do endereço.
     * @param logradouro   O nome da rua ou avenida.
     * @param numero       O número do local.
     * @param cep          O código postal (CEP).
     * @param bairro       O nome do bairro.
     * @param cidade       O nome da cidade.
     * @param uf           A unidade federativa (estado).
     * @param idReferencia O ID da entidade associada ao endereço (Cliente, Oficina, etc.)
     * @version 1.2
     * @since 1.0
     */
    public Endereco(Long idEndereco, String logradouro, int numero, String cep, String bairro, String cidade, String uf, int idReferencia) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.idReferencia = idReferencia;
    }

    /**
     * Construtor para inicializar um Endereço com atributos essenciais.
     *
     * @param idEndereco O ID do endereço.
     * @param logradouro O nome da rua ou avenida.
     * @param cidade     O nome da cidade.
     * @param uf         A unidade federativa (estado).
     */
    public Endereco(Long idEndereco, String logradouro, String cidade, String uf) {
        this(idEndereco, logradouro, 0, null, null, cidade, uf, 0);
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do endereço.
     *
     * @return O ID do endereço.
     */
    public Long getIdEndereco() {
        return idEndereco;
    }

    /**
     * Define o ID exclusivo do endereço.
     *
     * @param idEndereco O novo ID do endereço.
     */
    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * Obtém o logradouro (nome da rua ou avenida).
     *
     * @return O logradouro.
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * Define o logradouro (nome da rua ou avenida).
     *
     * @param logradouro O novo logradouro.
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * Obtém o número do endereço.
     *
     * @return O número.
     */
    public int getNumero() {
        return numero;
    }

    /**
     * Define o número do endereço.
     *
     * @param numero O novo número.
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * Obtém o código postal (CEP).
     *
     * @return O CEP.
     */
    public String getCep() {
        return cep;
    }

    /**
     * Define o código postal (CEP).
     *
     * @param cep O novo CEP.
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Obtém o bairro.
     *
     * @return O bairro.
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Define o bairro.
     *
     * @param bairro O novo bairro.
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Obtém a cidade.
     *
     * @return A cidade.
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Define a cidade.
     *
     * @param cidade A nova cidade.
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Obtém a unidade federativa (estado).
     *
     * @return O estado (UF).
     */
    public String getUf() {
        return uf;
    }

    /**
     * Define a unidade federativa (estado).
     *
     * @param uf A nova unidade federativa (estado).
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

    /**
     * Obtém o ID de referência da entidade associada ao endereço.
     *
     * @return O ID de referência.
     */
    public int getIdReferencia() {
        return idReferencia;
    }

    /**
     * Define o ID de referência da entidade associada ao endereço.
     *
     * @param idReferencia O novo ID de referência.
     */
    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * Exibe os detalhes completos do endereço.
     * Inclui logradouro, número, CEP, bairro, cidade e estado.
     */
    public void exibirDetalhes() {
        System.out.println("Endereço: " + logradouro + ", " + numero + ", CEP: " + cep + ", Bairro: " + bairro + ", Cidade: " + cidade + ", UF: " + uf);
    }

    /**
     * Retorna uma representação em String do endereço, incluindo todos os seus atributos.
     *
     * @return Uma String que representa o endereço.
     */
    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", uf='" + uf + '\'' +
                ", idReferencia=" + idReferencia +
                '}';
    }
}
