package br.com.fiap.controller;

import br.com.fiap.dtos.VeiculoDto;
import br.com.fiap.exceptions.VeiculoNotFoundException;
import br.com.fiap.exceptions.VeiculoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Veiculo;
import br.com.fiap.services.interfaces.VeiculoService;
import br.com.fiap.services.VeiculoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/veiculo")
public class VeiculoController {

    // Instância de VeiculoService obtida da factory
    private final VeiculoService veiculoService = VeiculoServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(VeiculoDto input) throws UnsupportedServiceOperationException {
        if (input.getIdVeiculo() == null) {
            try {
                // Criação do objeto Veiculo com base no DTO recebido
                Veiculo veiculo = this.veiculoService.create(
                        new Veiculo(
                                null,
                                input.getPlaca(),
                                input.getMarca(),
                                input.getModelo(),
                                input.getAno(),
                                input.getCor(),
                                input.getCombustivel(),
                                input.getClienteId()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(veiculo)
                        .build();
            } catch (SQLException | VeiculoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir veículo"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos veículos"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.veiculoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, VeiculoDto input) {
        try {
            // Atualização do objeto Veiculo com base no DTO e ID recebidos
            Veiculo updated = this.veiculoService.update(
                    new Veiculo(
                            id,
                            input.getPlaca(),
                            input.getMarca(),
                            input.getModelo(),
                            input.getAno(),
                            input.getCor(),
                            input.getCombustivel(),
                            input.getClienteId()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (VeiculoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar veículo"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.veiculoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (VeiculoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar veículo"))
                    .build();
        }
    }
}
