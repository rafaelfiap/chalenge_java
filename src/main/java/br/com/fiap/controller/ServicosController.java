package br.com.fiap.controller;

import br.com.fiap.dtos.ServicosDto;
import br.com.fiap.exceptions.ServicosNotFoundException;
import br.com.fiap.exceptions.ServicosNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Servicos;
import br.com.fiap.services.interfaces.ServicosService;
import br.com.fiap.services.ServicosServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

/**
 * Controlador REST para a entidade Servicos.
 * Define endpoints para criar, listar, atualizar e deletar serviços.
 *
 * @since 1.0
 */
@Path("/rest/servicos")
public class ServicosController {

    // Instância de ServicosService obtida da factory
    private final ServicosService servicosService = ServicosServiceFactory.create();

    /**
     * Endpoint para criar um novo serviço.
     *
     * @param input DTO de entrada para criação de um serviço.
     * @return Resposta HTTP indicando sucesso ou erro.
     * @throws UnsupportedServiceOperationException Se ocorrer um erro na operação.
     */
    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ServicosDto input) throws UnsupportedServiceOperationException {
        if (input.getIdServico() == null) {
            try {
                // Criação do objeto Servicos com base no DTO recebido
                Servicos servico = this.servicosService.create(
                        new Servicos(
                                null,
                                input.getTipoServico(),
                                input.getDescricao(),
                                input.getValorServico(),
                                input.getTempoEstimado()
                        )
                );
                return Response.status(Response.Status.CREATED)
                        .entity(servico)
                        .build();
            } catch (SQLException | ServicosNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir serviço"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos serviços"))
                    .build();
        }
    }

    /**
     * Endpoint para listar todos os serviços.
     *
     * @return Resposta HTTP com a lista de todos os serviços.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.servicosService.findAll())
                .build();
    }

    /**
     * Endpoint para atualizar um serviço existente.
     *
     * @param id    ID do serviço a ser atualizado.
     * @param input DTO de entrada para atualização do serviço.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ServicosDto input) {
        try {
            // Atualização do objeto Servicos com base no DTO e ID recebidos
            Servicos updated = this.servicosService.update(
                    new Servicos(
                            id,
                            input.getTipoServico(),
                            input.getDescricao(),
                            input.getValorServico(),
                            input.getTempoEstimado()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (ServicosNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar serviço"))
                    .build();
        }
    }

    /**
     * Endpoint para excluir um serviço existente.
     *
     * @param id ID do serviço a ser excluído.
     * @return Resposta HTTP indicando sucesso ou erro.
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.servicosService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ServicosNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar serviço"))
                    .build();
        }
    }
}
