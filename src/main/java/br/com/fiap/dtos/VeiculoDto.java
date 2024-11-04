package br.com.fiap.dtos;

/**
 * DTO para transferência de dados da entidade Veiculo.
 *
 * <p>A classe `VeiculoDto` é usada para transferir dados do veículo entre as camadas da aplicação,
 * geralmente entre a camada de serviço e a camada de apresentação. Inclui informações como o ID, placa,
 * marca, modelo, ano, cor, combustível e o ID do cliente associado ao veículo.</p>
 *
 * @version 1.0
 * @since 1.0
 */
public class VeiculoDto {

    // Identificador único do veículo, utilizado para operações de atualização e deleção.
    private Long idVeiculo;

    // Placa única do veículo.
    private String placa;

    // Marca do veículo.
    private String marca;

    // Modelo do veículo.
    private String modelo;

    // Ano de fabricação do veículo.
    private int ano;

    // Cor do veículo.
    private String cor;

    // Tipo de combustível do veículo.
    private String combustivel;

    // Identificador do cliente associado ao veículo.
    private Long clienteId;

    // Getters e Setters

    /**
     * Obtém o ID do veículo.
     *
     * @return O ID do veículo.
     * @since 1.0
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID do veículo.
     *
     * @param idVeiculo O novo ID do veículo.
     * @since 1.0
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Obtém a placa do veículo.
     *
     * @return A placa do veículo.
     * @since 1.0
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa do veículo.
     *
     * @param placa A nova placa do veículo.
     * @since 1.0
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Obtém a marca do veículo.
     *
     * @return A marca do veículo.
     * @since 1.0
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do veículo.
     *
     * @param marca A nova marca do veículo.
     * @since 1.0
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtém o modelo do veículo.
     *
     * @return O modelo do veículo.
     * @since 1.0
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do veículo.
     *
     * @param modelo O novo modelo do veículo.
     * @since 1.0
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Obtém o ano de fabricação do veículo.
     *
     * @return O ano do veículo.
     * @since 1.0
     */
    public int getAno() {
        return ano;
    }

    /**
     * Define o ano de fabricação do veículo.
     *
     * @param ano O novo ano do veículo.
     * @since 1.0
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Obtém a cor do veículo.
     *
     * @return A cor do veículo.
     * @since 1.0
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor do veículo.
     *
     * @param cor A nova cor do veículo.
     * @since 1.0
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Obtém o tipo de combustível do veículo.
     *
     * @return O tipo de combustível do veículo.
     * @since 1.0
     */
    public String getCombustivel() {
        return combustivel;
    }

    /**
     * Define o tipo de combustível do veículo.
     *
     * @param combustivel O novo tipo de combustível do veículo.
     * @since 1.0
     */
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    /**
     * Obtém o ID do cliente associado ao veículo.
     *
     * @return O ID do cliente associado.
     * @since 1.0
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * Define o ID do cliente associado ao veículo.
     *
     * @param clienteId O novo ID do cliente associado.
     * @since 1.0
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Retorna uma representação textual do objeto VeiculoDto.
     *
     * @return Uma string com os detalhes do VeiculoDto.
     * @since 1.0
     */
    @Override
    public String toString() {
        return "VeiculoDto{" +
                "idVeiculo=" + idVeiculo +
                ", placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", cor='" + cor + '\'' +
                ", combustivel='" + combustivel + '\'' +
                ", clienteId=" + clienteId +
                '}';
    }
}
