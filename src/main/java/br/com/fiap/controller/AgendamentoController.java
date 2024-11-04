package br.com.fiap.controller;

import br.com.fiap.dtos.AgendamentoDto;
import br.com.fiap.exceptions.AgendamentoNotFoundException;
import br.com.fiap.exceptions.AgendamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Agendamento;
import br.com.fiap.services.interfaces.AgendamentoService;
import br.com.fiap.services.AgendamentoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/agendamento")
public class AgendamentoController {

    // Instância de AgendamentoService obtida da factory
    private final AgendamentoService agendamentoService = AgendamentoServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(AgendamentoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdAgendamento() == null) {
            try {
                // Criação do objeto Agendamento com base no DTO recebido
                Agendamento agendamento = this.agendamentoService.create(
                        new Agendamento(
                                null,
                                input.getDataAgendamento(),
                                input.getHoraAgendamento(),
                                input.getIdCliente(),
                                input.getIdOficina()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(agendamento)
                        .build();
            } catch (SQLException | AgendamentoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir agendamento"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos agendamentos"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.agendamentoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, AgendamentoDto input) {
        try {
            // Atualização do objeto Agendamento com base no DTO e ID recebidos
            Agendamento updated = this.agendamentoService.update(
                    new Agendamento(
                            id,
                            input.getDataAgendamento(),
                            input.getHoraAgendamento(),
                            input.getIdCliente(),
                            input.getIdOficina()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (AgendamentoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar agendamento"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.agendamentoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (AgendamentoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar agendamento"))
                    .build();
        }
    }
}
