package com.ipartek.formacion.model.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ipartek.formacion.pojo.Usuario;

public class UsuarioDAOImplTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSave() {
		fail("Not yet implemented");
	}

	@Test
	public void testBorrar() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarPorId() {
		fail("Not yet implemented");
	}

	@Test
	public void testListar() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheckLogin() {

		final UsuarioDAOImpl dao = UsuarioDAOImpl.getInstance();

		final Usuario uExsitente = dao.checkLogin("admin", "admin");
		assertNotNull("Este Usuario de existir", uExsitente);

		final Usuario uNull = dao.checkLogin("dfgdfg", "gdfsg");
		assertNull("Usuario debe ser Null", uNull);

	}

}
