package com.ipartek.formacion.controller.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

/**
 * Servlet implementation class CheckEmailServlet
 */
@WebServlet("/checkemail")
public class CheckEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProccess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doProccess(request, response);
	}

	private void doProccess(HttpServletRequest request, HttpServletResponse response) throws IOException {

		// recoger parametros
		final String emailAbuscar = request.getParameter("email");

		// charset utf-8
		response.setCharacterEncoding("utf-8");
		// response-type JSON
		response.setContentType("application/json");

		// TODO llamar servicio
		boolean encontrado = false;
		final String emailHardcoded = "pepe@pepe.com";
		if (emailHardcoded.equals(emailAbuscar)) {
			encontrado = true;
		}

		// conseguir out
		final PrintWriter out = response.getWriter();
		// pintar respuesta
		final JSONObject json = new JSONObject();
		json.put("encontrado", encontrado);
		json.put("email", emailAbuscar);

		out.print(json.toString());

	}

}