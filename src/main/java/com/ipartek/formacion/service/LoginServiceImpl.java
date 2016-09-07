package com.ipartek.formacion.service;

import com.ipartek.formacion.model.dao.UsuarioDAOImpl;
import com.ipartek.formacion.pojo.Usuario;

public class LoginServiceImpl implements LoginService {

	private static LoginServiceImpl INSTANCE = null;

	private LoginServiceImpl() {

	}

	public static LoginServiceImpl getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LoginServiceImpl();
		}
		return INSTANCE;
	}

	@Override
	public Usuario checkLogin(String nombre, String password) {
		// TODO inyectar DAO DE SPRING
		final UsuarioDAOImpl daoUsuario = UsuarioDAOImpl.getInstance();
		return daoUsuario.checkLogin(nombre, password);
	}

}
