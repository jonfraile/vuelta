package com.ipartek.formacion.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnectionImpl implements DataBaseConnection {

	private Connection conexion;
	private static DataBaseConnectionImpl INSTANCE = null;

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/vc16";
	private static final String USER = "root";
	private static final String PASS = "";

	private DataBaseConnectionImpl() {
		this.conexion = null;
		conectar();
	}

	public static DataBaseConnectionImpl getInstance() {
		if (INSTANCE == null) {
			createInstance();
		}

		return INSTANCE;
	}

	private synchronized static void createInstance() {
		if (INSTANCE == null) {
			INSTANCE = new DataBaseConnectionImpl();
		}
	}

	private void conectar() {

		if (this.conexion == null) {
			try {
				Class.forName(DRIVER);
				this.conexion = DriverManager.getConnection(URL, USER, PASS);
				this.conexion.setAutoCommit(false);
			} catch (final ClassNotFoundException e) {
				System.out.println("Falta libreria para driver " + DRIVER);
				e.printStackTrace();
			} catch (final SQLException e) {
				System.out.println("No se puede establecer conexion " + URL);
				e.printStackTrace();
			}
		}

	}

	@Override
	public void desconectar() {
		if (this.conexion != null) {
			try {
				this.conexion.close();
				this.conexion = null;
			} catch (final SQLException e) {
				System.out.println("No se pudo cerrar conexion");
				e.printStackTrace();
			}
		}

	}

	@Override
	public Connection getConexion() {
		conectar();
		return this.conexion;
	}

	@Override
	public void commit() throws SQLException {
		this.conexion.commit();
	}

	@Override
	public void rollback() throws SQLException {
		this.conexion.rollback();

	}

}