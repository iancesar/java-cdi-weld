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

	public Users save(Users user) {

		String insert = "insert into users (name) values (:name)";

		try (Connection con = sql.beginTransaction()) {
			Integer id = con.createQuery(insert).addParameter("name", user.getName()).executeUpdate().getKey(Integer.class);
			con.commit();
			user.setId(id);
			return user;
		}

	}

}
