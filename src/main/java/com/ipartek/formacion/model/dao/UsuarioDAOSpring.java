package com.ipartek.formacion.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ipartek.formacion.model.mapper.UsuarioMapper;
import com.ipartek.formacion.pojo.Usuario;

public class UsuarioDAOSpring implements UsuarioDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	@Override
	public void setDataSource(DataSource ds) {
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(this.dataSource);
	}

	@Override
	public boolean save(final Usuario u) {
		boolean resul = false;
		int affectedRows = -1;

		// insert
		if (-1 == u.getId()) {

			final KeyHolder keyHolder = new GeneratedKeyHolder();
			final String sqlInsert = "INSERT INTO `usuario` ( `nombre`, `password`, `email`) VALUES ( ? , ? , ? );";
			affectedRows = this.jdbcTemplateObject.update(new PreparedStatementCreator() {
				@Override
				public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
					final PreparedStatement ps = conn.prepareStatement(sqlInsert);
					ps.setString(1, u.getNombre());
					ps.setString(2, u.getPassword());
					ps.setString(3, u.getEmail());
					return ps;
				}
			}, keyHolder);

			u.setId(keyHolder.getKey().longValue());

			// modificar
		} else {
			final String sqlInsert = "UPDATE `usuario` SET `nombre`=?, `password`=?, `email`=? WHERE  `id`= ?;";
			final Object[] args = { u.getNombre(), u.getPassword(), u.getEmail(), u.getId() };
			affectedRows = this.jdbcTemplateObject.update(sqlInsert, args);
		}

		if (affectedRows == 1) {
			resul = true;
		}
		return resul;
	}

	@Override
	public boolean borrar(long id) {
		boolean resul = false;
		final String sql = "DELETE FROM `usuario` WHERE  `id`= ?;";
		if (1 == this.jdbcTemplateObject.update(sql, id)) {
			resul = true;
		}
		return resul;
	}

	@Override
	public Usuario buscarPorId(long id) {
		Usuario resul = null;
		final String SQL = "select id, nombre, email, password from Usuario where id = ?";
		try {
			resul = this.jdbcTemplateObject.queryForObject(SQL, new Object[] { id }, new UsuarioMapper());
		} catch (final EmptyResultDataAccessException e) {
			resul = null;
		}
		return resul;
	}

	@Override
	public List<Usuario> listar() {
		ArrayList<Usuario> resul = null;
		final String SQL = "select id, nombre, email, password from Usuario order by id desc  limit 1000";
		try {
			resul = (ArrayList<Usuario>) this.jdbcTemplateObject.query(SQL, new UsuarioMapper());
		} catch (final EmptyResultDataAccessException e) {
			resul = new ArrayList<Usuario>();
		}
		return resul;
	}

	@Override
	public Usuario checkLogin(String nombre, String pass) {
		Usuario resul;
		final String SQL = "select id, nombre, email, password from Usuario where nombre = ? and password = ?";
		final Object[] args = new Object[] { nombre, pass };
		try {
			resul = this.jdbcTemplateObject.queryForObject(SQL, args, new UsuarioMapper());
		} catch (final EmptyResultDataAccessException e) {
			resul = null;
		}
		return resul;
	}

}
