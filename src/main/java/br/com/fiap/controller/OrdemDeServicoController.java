package br.com.fiap.controller;

import br.com.fiap.dtos.OrdemDeServicoDto;
import br.com.fiap.exceptions.OrdemDeServicoNotFoundException;
import br.com.fiap.exceptions.OrdemDeServicoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.OrdemDeServico;
import br.com.fiap.services.interfaces.OrdemDeServicoService;
import br.com.fiap.services.OrdemDeServicoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/ordemdeservico")
public class OrdemDeServicoController {

    // Instância de OrdemDeServicoService obtida da factory
    private final OrdemDeServicoService ordemDeServicoService = OrdemDeServicoServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(OrdemDeServicoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdOs() == null) {
            try {
                // Criação do objeto OrdemDeServico com base no DTO recebido
                OrdemDeServico ordemDeServico = this.ordemDeServicoService.create(
                        new OrdemDeServico(
                                null,
                                input.getStatus(),
                                input.getIdOrcamento(),
                                input.getIdFuncionario(),
                                input.getIdVeiculo(),
                                input.getDataInicio(),
                                input.getDataFim(),
                                input.getHoraInicio(),
                                input.getHoraFim()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(ordemDeServico)
                        .build();
            } catch (SQLException | OrdemDeServicoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir ordem de serviço"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novas ordens de serviço"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.ordemDeServicoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, OrdemDeServicoDto input) {
        try {
            // Atualização do objeto OrdemDeServico com base no DTO e ID recebidos
            OrdemDeServico updated = this.ordemDeServicoService.update(
                    new OrdemDeServico(
                            id,
                            input.getStatus(),
                            input.getIdOrcamento(),
                            input.getIdFuncionario(),
                            input.getIdVeiculo(),
                            input.getDataInicio(),
                            input.getDataFim(),
                            input.getHoraInicio(),
                            input.getHoraFim()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (OrdemDeServicoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar ordem de serviço"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.ordemDeServicoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (OrdemDeServicoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar ordem de serviço"))
                    .build();
        }
    }
}
