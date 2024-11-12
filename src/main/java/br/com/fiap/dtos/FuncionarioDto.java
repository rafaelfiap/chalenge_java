package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Funcionario.
 *
 * <p>A classe `FuncionarioDto` é usada para transferir dados do funcionário entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, CPF, nome,
 * sexo, função e ID da oficina associada ao funcionário.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class FuncionarioDto {

    // Identificador único do funcionário, utilizado para operações de atualização e deleção.
    private Long idFuncionario;

    // CPF do funcionário, utilizado em operações de identificação.
    private String cpf;

    // Nome do funcionário, utilizado em operações de criação e atualização.
    private String nome;

    // Sexo do funcionário ('M' para masculino, 'F' para feminino).
    private char sexo;

    // Função desempenhada pelo funcionário.
    private String funcao;

    // Identificador da oficina associada ao funcionário.
    private Integer idOficina;

    // Getters e Setters

    /**
     * Obtém o ID do funcionário.
     *
     * @return O ID do funcionário.
     * @since 1.0
     */
    public Long getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Define o ID do funcionário.
     *
     * @param idFuncionario O novo ID do funcionário.
     * @since 1.0
     */
    public void setIdFuncionario(Long idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Obtém o CPF do funcionário.
     *
     * @return O CPF do funcionário.
     * @since 1.0
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Define o CPF do funcionário.
     *
     * @param cpf O novo CPF do funcionário.
     * @since 1.0
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Obtém o nome do funcionário.
     *
     * @return O nome do funcionário.
     * @since 1.0
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do funcionário.
     *
     * @param nome O novo nome do funcionário.
     * @since 1.0
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o sexo do funcionário.
     *
     * @return O sexo do funcionário ('M' para masculino, 'F' para feminino).
     * @since 1.0
     */
    public char getSexo() {
        return sexo;
    }

    /**
     * Define o sexo do funcionário.
     *
     * @param sexo O novo sexo do funcionário.
     * @since 1.0
     */
    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    /**
     * Obtém a função do funcionário.
     *
     * @return A função do funcionário.
     * @since 1.0
     */
    public String getFuncao() {
        return funcao;
    }

    /**
     * Define a função do funcionário.
     *
     * @param funcao A nova função do funcionário.
     * @since 1.0
     */
    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    /**
     * Obtém o ID da oficina associada ao funcionário.
     *
     * @return O ID da oficina associada.
     * @since 1.0
     */
    public Integer getIdOficina() {
        return idOficina;
    }

    /**
     * Define o ID da oficina associada ao funcionário.
     *
     * @param idOficina O novo ID da oficina associada.
     * @since 1.0
     */
    public void setIdOficina(Integer idOficina) {
        this.idOficina = idOficina;
    }

    /**
     * Retorna uma representação textual do objeto FuncionarioDto.
     *
     * @return Uma string com os detalhes do FuncionarioDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "FuncionarioDto{" +
                "idFuncionario=" + idFuncionario +
                ", cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo=" + sexo +
                ", funcao='" + funcao + '\'' +
                ", idOficina=" + idOficina +
                '}';
    }
}
