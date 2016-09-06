package com.ipartek.formacion.service;

import com.ipartek.formacion.pojo.Usuario;

public interface LoginService {

	Usuario checkLogin(String nombre, String password);

}
