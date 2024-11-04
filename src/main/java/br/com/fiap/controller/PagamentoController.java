package br.com.fiap.controller;

import br.com.fiap.dtos.PagamentoDto;
import br.com.fiap.exceptions.PagamentoNotFoundException;
import br.com.fiap.exceptions.PagamentoNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Pagamento;
import br.com.fiap.services.interfaces.PagamentoService;
import br.com.fiap.services.PagamentoServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/pagamento")
public class PagamentoController {

    // Instância de PagamentoService obtida da factory
    private final PagamentoService pagamentoService = PagamentoServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(PagamentoDto input) {
        if (input.getIdPagamento() == null) {
            try {
                // Criação do objeto Pagamento com base no DTO recebido
                Pagamento pagamento = pagamentoService.create(
                        new Pagamento(
                                null,
                                input.getFormaPagamento(),
                                input.getTipoPagamento(),
                                input.getDesconto(),
                                input.getIdOrdemDeServico()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(pagamento)
                        .build();
            } catch (SQLException | PagamentoNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir pagamento"))
                        .build();
            } catch (UnsupportedServiceOperationException e) {
                throw new RuntimeException(e);
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem", "Esse método só permite a criação de novos pagamentos"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.pagamentoService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, PagamentoDto input) {
        try {
            // Atualização do objeto Pagamento com base no DTO e ID recebidos
            Pagamento updated = pagamentoService.update(
                    new Pagamento(
                            id,
                            input.getFormaPagamento(),
                            input.getTipoPagamento(),
                            input.getDesconto(),
                            input.getIdOrdemDeServico()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (PagamentoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar pagamento"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            pagamentoService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (PagamentoNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar pagamento"))
                    .build();
        }
    }
}
