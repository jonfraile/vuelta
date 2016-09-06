package com.ipartek.formacion.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.pojo.Usuario;
import com.ipartek.formacion.service.LoginService;
import com.ipartek.formacion.service.LoginServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService = null;
	private RequestDispatcher dispatch = null;
	private HttpSession session = null;

	private final String VIEW_LOGIN = "index.jsp";
	private final String VIEW_BACK_INDEX = "backoffice/index.jsp";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		this.loginService = LoginServiceImpl.getInstance();
	}

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
			this.session = request.getSession();

			// recoger parametros del formulario
			final String pNombre = request.getParameter("nombre");
			final String pPass = request.getParameter("pass");
			System.out.println("parametros: [" + pNombre + "," + pPass + "]");

			final Usuario u = this.loginService.checkLogin(pNombre, pPass);
			String mensaje = "";
			if (u == null) {
				mensaje = "Nombre o contraseña incorrecto";
				this.dispatch = request.getRequestDispatcher(this.VIEW_LOGIN);
			} else {
				mensaje = "Ongi Etorri " + u.getNombre();
				this.dispatch = request.getRequestDispatcher(this.VIEW_BACK_INDEX);
				this.session.setAttribute("usuario", u);
			}

			request.setAttribute("mensaje", mensaje);
			this.dispatch.forward(request, response);

		} catch (final Exception e) {

			e.printStackTrace();
		}

	}

}
