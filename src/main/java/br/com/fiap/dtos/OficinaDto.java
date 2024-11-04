package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Oficina.
 *
 * <p>A classe `OficinaDto` é usada para transferir dados da oficina entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, CNPJ, nome,
 * email, telefone e endereço associados à oficina.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class OficinaDto {

    // Identificador único da oficina, utilizado para operações de atualização e deleção.
    private Long idOficina;

    // CNPJ da oficina, utilizado em operações de identificação.
    private String cnpj;

    // Nome da oficina, utilizado em operações de criação e atualização.
    private String nome;

    // Email da oficina.
    private String email;

    // Identificador do telefone associado à oficina.
    private Long idTelefone;

    // Identificador do endereço associado à oficina.
    private Long idEndereco;

    // Getters e Setters

    /**
     * Obtém o ID da oficina.
     *
     * @return O ID da oficina.
     * @since 1.0
     */
    public Long getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina.
     *
     * @param idOficina O novo ID da oficina.
     * @since 1.0
     */
    public void setIdOficina(Long idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Obtém o CNPJ da oficina.
     *
     * @return O CNPJ da oficina.
     * @since 1.0
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * Define o CNPJ da oficina.
     *
     * @param cnpj O novo CNPJ da oficina.
     * @since 1.0
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * Obtém o nome da oficina.
     *
     * @return O nome da oficina.
     * @since 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da oficina.
     *
     * @param nome O novo nome da oficina.
     * @since 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o email da oficina.
     *
     * @return O email da oficina.
     * @since 1.0
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define o email da oficina.
     *
     * @param email O novo email da oficina.
     * @since 1.0
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtém o ID do telefone associado à oficina.
     *
     * @return O ID do telefone associado.
     * @since 1.0
     */
    public Long getIdTelefone() {
        return idTelefone;
    }

    /**
     * Define o ID do telefone associado à oficina.
     *
     * @param idTelefone O novo ID do telefone associado.
     * @since 1.0
     */
    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    /**
     * Obtém o ID do endereço associado à oficina.
     *
     * @return O ID do endereço associado.
     * @since 1.0
     */
    public Long getIdEndereco() {
        return idEndereco;
    }

    /**
     * Define o ID do endereço associado à oficina.
     *
     * @param idEndereco O novo ID do endereço associado.
     * @since 1.0
     */
    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * Retorna uma representação textual do objeto OficinaDto.
     *
     * @return Uma string com os detalhes do OficinaDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "OficinaDto{" +
                "idOficina=" + idOficina +
                ", cnpj='" + cnpj + '\'' +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", idTelefone=" + idTelefone +
                ", idEndereco=" + idEndereco +
                '}';
    }
}
