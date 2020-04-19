package br.com.cdi.dao;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import br.com.cdi.model.Users;

@Named
public class UserDAO {

	@Inject
	private Sql2o sql;

	public List<Users> findAll() {
		try (Connection con = sql.open()) {
			return con.createQuery("select * from users").executeAndFetch(Users.class);
		}
	}

}
