package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response) {

		// TODO trazas de LOG4J
		// TODO filtro para backoffice

		try {

			// recoger parametros del formulario
			final String pNombre = request.getParameter("nombre");
			final String pPass = request.getParameter("pass");

			System.out.println("parametros: [" + pNombre + "," + pPass + "]");

			request.setAttribute("mensaje", "Usario y contraseña incorrectos");
			request.getRequestDispatcher("index.jsp").forward(request, response);

		} catch (final Exception e) {

			e.printStackTrace();
		}

	}

}
