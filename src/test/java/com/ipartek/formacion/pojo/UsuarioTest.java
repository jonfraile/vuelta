package com.ipartek.formacion.pojo;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UsuarioTest {

	@BeforeClass()
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass()
	public static void tearDownAfterClass() throws Exception {
	}

	@Before()
	public void setUp() throws Exception {
	}

	@After()
	public void tearDown() throws Exception {
	}

	@Test()
	public void constructorPorDefecto() {
		Usuario u = new Usuario();
		assertEquals(-1, u.getId());
		assertEquals("", u.getNombre());
		assertEquals("", u.getPassword());
		assertEquals("", u.getEmail());		
	}

}
