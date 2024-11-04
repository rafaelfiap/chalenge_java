package br.com.fiap.dtos;

/**
 * Data Transfer Object (DTO) para a entidade Endereco.
 *
 * <p>Este DTO é utilizado para transferir dados relacionados a Endereco entre camadas
 * da aplicação e também para comunicação via APIs REST.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public class EnderecoDto {

    private Long idEndereco;      // ID do endereço
    private String logradouro;    // Nome da rua ou avenida
    private int numero;           // Número do endereço
    private String cep;           // Código postal (CEP)
    private String bairro;        // Nome do bairro
    private String cidade;        // Nome da cidade
    private String uf;            // Unidade federativa (estado)
    private int idReferencia;     // ID da entidade associada ao endereço (Cliente, Oficina, etc.)

    /**
     * Construtor padrão.
     */
    public EnderecoDto() {
    }

    /**
     * Construtor completo para inicializar todos os atributos do EnderecoDto.
     *
     * @param idEndereco   O ID do endereço.
     * @param logradouro   O nome da rua ou avenida.
     * @param numero       O número do endereço.
     * @param cep          O código postal (CEP).
     * @param bairro       O nome do bairro.
     * @param cidade       O nome da cidade.
     * @param uf           A unidade federativa (estado).
     * @param idReferencia O ID de referência da entidade associada.
     */
    public EnderecoDto(Long idEndereco, String logradouro, int numero, String cep, String bairro, String cidade, String uf, int idReferencia) {
        this.idEndereco = idEndereco;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.idReferencia = idReferencia;
    }

    // Getters e Setters

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * Gera uma representação em String do objeto EnderecoDto.
     *
     * @return Uma string com os detalhes do endereço.
     */
    @Override
    public String toString() {
        return "EnderecoDto{" +
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
