package com.ipartek.formacion.model.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

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
	public boolean save(Usuario u) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean borrar(long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Usuario buscarPorId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario checkLogin(String nombre, String pass) {
		Usuario resul;
		final String SQL = "select * from Usuario where nombre = ? and password = ?";
		final Object[] args = new Object[] { nombre, pass };
		try {
			resul = this.jdbcTemplateObject.queryForObject(SQL, args, new UsuarioMapper());
		} catch (final EmptyResultDataAccessException e) {
			resul = null;
		}
		return resul;
	}

}
