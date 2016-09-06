package com.ipartek.formacion.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipartek.formacion.pojo.Usuario;

public class UsuarioDAOSpringTest {

	@Test
	public void testCheckLogin() {

		final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		final UsuarioDAOSpring daoUsuario = (UsuarioDAOSpring) context.getBean("UsuarioDAOSpring");

		final Usuario uExsitente = daoUsuario.checkLogin("admin", "admin");
		assertNotNull("Este Usuario de existir", uExsitente);

		final Usuario uNull = daoUsuario.checkLogin("dfgdfg", "gdfsg");
		assertNull("Usuario debe ser Null", uNull);

	}

}
