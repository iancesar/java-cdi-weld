package br.com.cdi.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.cdi.dao.UserDAO;
import br.com.cdi.model.Users;

@Path("/")
@Produces(value = MediaType.APPLICATION_JSON)
@Consumes(value = MediaType.APPLICATION_JSON)
public class UserController {

	@Inject
	private UserDAO dao;

	@GET
	public Response getAll() {
		List<Users> users = dao.findAll();

		System.out.println("Total de users " + users.size());

		return Response.ok(users).build();
	}

	@POST
	public Response save(Users user) {

		return Response.ok().build();
	}

}
