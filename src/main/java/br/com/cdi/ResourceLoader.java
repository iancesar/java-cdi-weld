package br.com.cdi;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

import br.com.cdi.rest.UserController;

@ApplicationPath("/")
public class ResourceLoader extends ResourceConfig {

	public ResourceLoader() {
		register(UserController.class);
	}

}
