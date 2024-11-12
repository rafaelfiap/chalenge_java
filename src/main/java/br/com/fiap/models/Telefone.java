package br.com.fiap.models;

/**
 * Classe que representa um Telefone.
 * Contém informações como o número do telefone e o tipo de telefone.
 *
 * @since 1.0
 * @version 1.1
 */
public class Telefone {
    private Long idTelefone;  // ID exclusivo do telefone
    private String numero;     // Número do telefone
    private String tipo;       // Tipo do telefone (Residencial, Comercial, Celular)
    private int idReferencia;  // ID de referência (cliente ou oficina)

    /**
     * Construtor para inicializar um Telefone com todos os atributos.
     *
     * @param idTelefone  O ID exclusivo do telefone.
     * @param numero      O número do telefone.
     * @param tipo        O tipo do telefone (Residencial, Comercial, Celular).
     * @param idReferencia O ID de referência para o cliente ou oficina.
     * @version 1.1
     * @since 1.0
     */
    public Telefone(Long idTelefone, String numero, String tipo, int idReferencia) {
        this.idTelefone = idTelefone;
        this.numero = numero;
        this.tipo = tipo;
        this.idReferencia = idReferencia;
    }

    // Getters e Setters

    /**
     * Obtém o ID exclusivo do telefone.
     *
     * @return O ID do telefone.
     */
    public Long getIdTelefone() {
        return idTelefone;
    }

    /**
     * Define o ID exclusivo do telefone.
     *
     * @param idTelefone O novo ID do telefone.
     */
    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    /**
     * Obtém o número do telefone.
     *
     * @return O número do telefone.
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Define o número do telefone.
     *
     * @param numero O novo número do telefone.
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Obtém o tipo do telefone (Residencial, Comercial, Celular).
     *
     * @return O tipo do telefone.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Define o tipo do telefone.
     *
     * @param tipo O novo tipo do telefone.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtém o ID de referência associado ao telefone.
     *
     * @return O ID de referência (cliente ou oficina).
     */
    public int getIdReferencia() {
        return idReferencia;
    }

    /**
     * Define o ID de referência associado ao telefone.
     *
     * @param idReferencia O novo ID de referência.
     */
    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * Retorna uma representação textual do objeto Telefone.
     *
     * @return Uma string com os detalhes do telefone.
     */
    @Override
    public String toString() {
        return "Telefone{" +
                "idTelefone=" + idTelefone +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idReferencia=" + idReferencia +
                '}';
    }
}
