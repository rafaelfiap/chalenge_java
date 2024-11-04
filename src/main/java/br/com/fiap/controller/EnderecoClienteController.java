package br.com.fiap.controller;

import br.com.fiap.dtos.EnderecoDto;
import br.com.fiap.exceptions.EnderecoNotFoundException;
import br.com.fiap.exceptions.EnderecoNotSavedException;
import br.com.fiap.models.Endereco;
import br.com.fiap.services.interfaces.EnderecoService;
import br.com.fiap.services.EnderecoClienteServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/endereco-cliente")
public class EnderecoClienteController {

    // Instância de EnderecoService obtida da factory
    private final EnderecoService enderecoService = EnderecoClienteServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(EnderecoDto input) {
        if (input.getIdEndereco() == null) {
            try {
                // Criação do objeto Endereco com base no DTO recebido
                Endereco endereco = this.enderecoService.create(
                        new Endereco(
                                null,
                                input.getLogradouro(),
                                input.getNumero(),
                                input.getCep(),
                                input.getBairro(),
                                input.getCidade(),
                                input.getUf(),
                                input.getIdReferencia()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(endereco)
                        .build();
            } catch (SQLException | EnderecoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir endereço"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos endereços"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.enderecoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, EnderecoDto input) {
        try {
            // Atualização do objeto Endereco com base no DTO e ID recebidos
            Endereco updated = this.enderecoService.update(
                    new Endereco(
                            id,
                            input.getLogradouro(),
                            input.getNumero(),
                            input.getCep(),
                            input.getBairro(),
                            input.getCidade(),
                            input.getUf(),
                            input.getIdReferencia()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (EnderecoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar endereço"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.enderecoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (EnderecoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar endereço"))
                    .build();
        }
    }
}
