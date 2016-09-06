package com.ipartek.formacion.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.ipartek.formacion.model.DataBaseConnectionImpl;
import com.ipartek.formacion.pojo.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	private static UsuarioDAOImpl INSTANCE = null;
	private static DataBaseConnectionImpl db;
	private Connection conn;

	/**
	 * Constructor privado para patron Singleton
	 */
	private UsuarioDAOImpl() {
		db = DataBaseConnectionImpl.getInstance();
	}

	public static UsuarioDAOImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new UsuarioDAOImpl();
		}
		return INSTANCE;
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
	public Usuario checkLogin(final String nombre, final String pass) {
		Usuario u = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		final String sql = "select id, nombre, email, password from usuario where nombre = ? AND password = ?;";

		try {
			this.conn = db.getConexion();
			pst = this.conn.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, pass);

			rs = pst.executeQuery();
			while (rs.next()) {
				u = new Usuario();
				u.setId(rs.getLong("id"));
				u.setNombre(rs.getString("nombre"));
				u.setEmail(rs.getString("email"));
				u.setPassword(rs.getString("password"));
			}

		} catch (final Exception e) {
			e.printStackTrace();
		} finally {

			try {

				if (rs != null) {
					rs.close();
				}

				if (pst != null) {
					pst.close();
				}

				this.db.desconectar();

			} catch (final SQLException e) {
				e.printStackTrace();
			}

		}

		return u;
	}

	@Override
	public void setDataSource(DataSource ds) {
		// TODO CAmbiar conexion por DATASOURCE

	}

}
