package br.com.fiap.dtos;

/**
 * Data Transfer Object (DTO) para a entidade Telefone.
 *
 * <p>Este DTO é utilizado para transferir dados relacionados a Telefone entre camadas
 * da aplicação e também para comunicação via APIs REST.</p>
 *
 * @since 1.0
 * @version 1.0
 */
public class TelefoneDto {

    private Long idTelefone;   // ID do telefone
    private String numero;     // Número do telefone
    private String tipo;       // Tipo do telefone (Residencial, Comercial, Celular)
    private int idReferencia;  // ID da entidade associada ao telefone (Cliente, Oficina, etc.)

    /**
     * Construtor padrão.
     */
    public TelefoneDto() {
    }

    /**
     * Construtor completo para inicializar todos os atributos do TelefoneDto.
     *
     * @param idTelefone   O ID do telefone.
     * @param numero       O número do telefone.
     * @param tipo         O tipo do telefone (Residencial, Comercial, Celular).
     * @param idReferencia O ID de referência da entidade associada.
     */
    public TelefoneDto(Long idTelefone, String numero, String tipo, int idReferencia) {
        this.idTelefone = idTelefone;
        this.numero = numero;
        this.tipo = tipo;
        this.idReferencia = idReferencia;
    }

    // Getters e Setters

    public Long getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Long idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(int idReferencia) {
        this.idReferencia = idReferencia;
    }

    /**
     * Gera uma representação em String do objeto TelefoneDto.
     *
     * @return Uma string com os detalhes do telefone.
     */
    @Override
    public String toString() {
        return "TelefoneDto{" +
                "idTelefone=" + idTelefone +
                ", numero='" + numero + '\'' +
                ", tipo='" + tipo + '\'' +
                ", idReferencia=" + idReferencia +
                '}';
    }
}
