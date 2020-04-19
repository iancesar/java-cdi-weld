package br.com.cdi.factories;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.sql2o.Sql2o;

@ApplicationScoped
public class Sql2oFactory {

	private Sql2o sql2o;

	@Produces
	public Sql2o createConnection() {
		if (sql2o == null) {
			sql2o = new Sql2o("jdbc:mysql://localhost:3307/cdi", "root", "root");
		}

		return sql2o;
	}

}
