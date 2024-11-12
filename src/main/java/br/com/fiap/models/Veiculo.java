package br.com.fiap.models;

/**
 * Classe que representa um Veículo.
 * Esta classe contém os atributos que descrevem um veículo, como marca, modelo, ano, cor, combustível, placa e cliente associado.
 * Inclui métodos para manipular e exibir as informações sobre o veículo, como getters e setters, além de funções para exibir detalhes e atualizar o cliente associado.
 *
 * @since 1.0
 * @version 1.2
 * @see Cliente
 */
public class Veiculo {
    private Long idVeiculo; // ID exclusivo para o veículo
    private String placa; // Placa única do veículo
    private String marca; // Marca do veículo
    private String modelo; // Modelo do veículo
    private int ano; // Ano de fabricação do veículo
    private String cor; // Cor do veículo
    private String combustivel; // Tipo de combustível do veículo
    private Long clienteId; // ID do cliente associado ao veículo

    /**
     * Construtor para inicializar um Veículo com todos os atributos.
     *
     * @param idVeiculo   O ID exclusivo do veículo.
     * @param placa       A placa única do veículo.
     * @param marca       A marca do veículo.
     * @param modelo      O modelo do veículo.
     * @param ano         O ano de fabricação do veículo.
     * @param cor         A cor do veículo.
     * @param combustivel O tipo de combustível do veículo.
     * @param clienteId   O ID do cliente associado ao veículo.
     */
    public Veiculo(Long idVeiculo, String placa, String marca, String modelo, int ano, String cor, String combustivel, Long clienteId) {
        this.idVeiculo = idVeiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.cor = cor;
        this.combustivel = combustivel;
        this.clienteId = clienteId;
    }

    // Getters e Setters

    /**
     * Retorna o ID exclusivo do veículo.
     *
     * @return O ID do veículo.
     */
    public Long getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * Define o ID exclusivo do veículo.
     *
     * @param idVeiculo O novo ID do veículo.
     */
    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    /**
     * Retorna a placa única do veículo.
     *
     * @return A placa do veículo.
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Define a placa única do veículo.
     *
     * @param placa A nova placa do veículo.
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Retorna a marca do veículo.
     *
     * @return A marca do veículo.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Define a marca do veículo.
     *
     * @param marca A nova marca do veículo.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Retorna o modelo do veículo.
     *
     * @return O modelo do veículo.
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Define o modelo do veículo.
     *
     * @param modelo O novo modelo do veículo.
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Retorna o ano de fabricação do veículo.
     *
     * @return O ano do veículo.
     */
    public int getAno() {
        return ano;
    }

    /**
     * Define o ano de fabricação do veículo.
     *
     * @param ano O novo ano do veículo.
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Retorna a cor do veículo.
     *
     * @return A cor do veículo.
     */
    public String getCor() {
        return cor;
    }

    /**
     * Define a cor do veículo.
     *
     * @param cor A nova cor do veículo.
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * Retorna o tipo de combustível do veículo.
     *
     * @return O tipo de combustível do veículo.
     */
    public String getCombustivel() {
        return combustivel;
    }

    /**
     * Define o tipo de combustível do veículo.
     *
     * @param combustivel O novo tipo de combustível do veículo.
     */
    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    /**
     * Retorna o ID do cliente associado ao veículo.
     *
     * @return O ID do cliente associado.
     */
    public Long getClienteId() {
        return clienteId;
    }

    /**
     * Define o ID do cliente associado ao veículo.
     *
     * @param clienteId O novo ID do cliente associado.
     */
    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    /**
     * Exibe os detalhes completos do veículo, incluindo marca, modelo, placa, ano, cor e combustível.
     */
    public void exibirDetalhes() {
        System.out.println("Veículo: " + marca + " " + modelo + ", Placa: " + placa + ", Ano: " + ano + ", Cor: " + cor + ", Combustível: " + combustivel);
    }

    /**
     * Atualiza o cliente associado ao veículo.
     *
     * @param clienteId O novo ID do cliente associado.
     */
    public void atualizarCliente(Long clienteId) {
        this.clienteId = clienteId;
        System.out.println("Cliente associado atualizado para o ID: " + clienteId);
    }

    /**
     * Sobrescreve o método toString para fornecer uma representação textual dos detalhes do veículo.
     *
     * @return Uma string contendo os detalhes do veículo.
     */
    @Override
    public String toString() {
        return "Veiculo{" +
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
