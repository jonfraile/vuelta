package com.ipartek.formacion.model.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ipartek.formacion.pojo.Usuario;

public class UsuarioDAOSpringTest {

	static ApplicationContext context;
	static UsuarioDAOSpring daoUsuario;
	static Usuario usuario;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new ClassPathXmlApplicationContext("beans.xml");
		daoUsuario = (UsuarioDAOSpring) context.getBean("UsuarioDAOSpring");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context = null;
		daoUsuario = null;
	}

	@Before
	public void setUp() throws Exception {
		usuario = new Usuario();
		usuario.setEmail("eliminar@dummy.com");
		usuario.setNombre("eliminar");
		usuario.setPassword("123456");
	}

	@After
	public void tearDown() throws Exception {
		usuario = null;
	}

	@Test
	public void save() {

		// insertar usuario
		assertTrue("Usuario No insertado" + usuario, daoUsuario.save(usuario));
		final long id = usuario.getId();
		assertNotEquals("Deberia tener ID <> -1 ", -1, id);

		// modificar usuario
		usuario.setEmail("borrar@borrar.com");
		usuario.setNombre("borrar");
		usuario.setPassword("12345456");
		assertTrue("Usuario No Modificado" + usuario, daoUsuario.save(usuario));
		assertEquals("Deberia ser mismo ID", id, usuario.getId());

		assertTrue("No se puedo eliminar " + usuario, daoUsuario.borrar(id));

	}

	@Test
	public void borrar() {
		assertFalse("No se puedo eliminar Usuario Inexistente ", daoUsuario.borrar(-1));
	}

	@Test
	public void buscarPorId() {
		daoUsuario.save(usuario);
		final long id = usuario.getId();
		assertNotNull(daoUsuario.buscarPorId(id));
		daoUsuario.borrar(id);
	}

	@Test
	public void listar() {

		ArrayList<Usuario> lista = (ArrayList<Usuario>) daoUsuario.listar();
		final int numUsuarioInciales = lista.size();

		daoUsuario.save(usuario);
		lista = (ArrayList<Usuario>) daoUsuario.listar();

		assertEquals((numUsuarioInciales + 1), lista.size());

	}

	@Test
	public void testCheckLogin() {

		final Usuario uExsitente = daoUsuario.checkLogin("admin", "admin");
		assertNotNull("Este Usuario de existir", uExsitente);

		final Usuario uNull = daoUsuario.checkLogin("dfgdfg", "gdfsg");
		assertNull("Usuario debe ser Null", uNull);

	}

}
