package br.com.fiap.controller;

import br.com.fiap.dtos.ClienteDto;
import br.com.fiap.exceptions.ClienteNotFoundException;
import br.com.fiap.exceptions.ClienteNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Cliente;
import br.com.fiap.services.interfaces.ClienteService;
import br.com.fiap.services.ClienteServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/cliente")
public class ClienteController {

    // Instância de ClienteService obtida da factory
    private final ClienteService clienteService = ClienteServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ClienteDto input) throws UnsupportedServiceOperationException {
        if (input.getIdCliente() == null) {
            try {
                // Criação do objeto Cliente com base no DTO recebido
                Cliente cliente = this.clienteService.create(
                        new Cliente(
                                null,
                                input.getCpf(),
                                input.getNome(),
                                input.getEmail(),
                                input.getSexo()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(cliente)
                        .build();
            } catch (SQLException | ClienteNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir cliente"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos clientes"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.clienteService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, ClienteDto input) {
        try {
            // Atualização do objeto Cliente com base no DTO e ID recebidos
            Cliente updated = this.clienteService.update(
                    new Cliente(
                            id,
                            input.getCpf(),
                            input.getNome(),
                            input.getEmail(),
                            input.getSexo()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (ClienteNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar cliente"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.clienteService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (ClienteNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar cliente"))
                    .build();
        }
    }
}
