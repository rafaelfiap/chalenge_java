package br.com.fiap.controller;

import br.com.fiap.dtos.FuncionarioDto;
import br.com.fiap.exceptions.FuncionarioNotFoundException;
import br.com.fiap.exceptions.FuncionarioNotSavedException;
import br.com.fiap.exceptions.UnsupportedServiceOperationException;
import br.com.fiap.models.Funcionario;
import br.com.fiap.services.interfaces.FuncionarioService;
import br.com.fiap.services.FuncionarioServiceFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Map;

@Path("/rest/funcionario")
public class FuncionarioController {

    // Instância de FuncionarioService obtida da factory
    private final FuncionarioService funcionarioService = FuncionarioServiceFactory.create();

    @POST
    @Path("/cadastre")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(FuncionarioDto input) throws UnsupportedServiceOperationException {
        if (input.getIdFuncionario() == null) {
            try {
                // Criação do objeto Funcionario com base no DTO recebido
                Funcionario funcionario = this.funcionarioService.create(
                        new Funcionario(
                                null,
                                input.getCpf(),
                                input.getNome(),
                                input.getSexo(),
                                input.getFuncao(),
                                input.getIdOficina()
                        )
                );
                return Response
                        .status(Response.Status.CREATED)
                        .entity(funcionario)
                        .build();
            } catch (SQLException | FuncionarioNotSavedException e) {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(Map.of("mensagem", "Erro inesperado ao tentar inserir funcionário"))
                        .build();
            }
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(Map.of("mensagem",
                            "Esse método só permite a criação de novos funcionários"))
                    .build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.status(Response.Status.OK)
                .entity(this.funcionarioService.findAll()).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id, FuncionarioDto input) {
        try {
            // Atualização do objeto Funcionario com base no DTO e ID recebidos
            Funcionario updated = this.funcionarioService.update(
                    new Funcionario(
                            id,
                            input.getCpf(),
                            input.getNome(),
                            input.getSexo(),
                            input.getFuncao(),
                            input.getIdOficina()
                    )
            );
            return Response.status(Response.Status.OK).entity(updated).build();
        } catch (FuncionarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar atualizar funcionário"))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            this.funcionarioService.deleteById(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (FuncionarioNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (SQLException s) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of("mensagem", "Erro inesperado ao tentar deletar funcionário"))
                    .build();
        }
    }
}
