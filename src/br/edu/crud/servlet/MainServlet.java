package br.edu.crud.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.crud.BO.UsuarioBO;
import br.edu.crud.exception.NegocioException;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxima = null;

		switch (acao) {
		case "sair":
			proxima = "logout.jsp";
			break;
		case "consultas":
			proxima = "consultas.jsp";
			break;
		case "cadastros":
			proxima = "cadastros.jsp";
			break;
		case "login":
			try {
				new UsuarioBO().validarUsuario(request);
			} catch (NegocioException e) {
				request.setAttribute("msgErro", e.getMessage());
				proxima = "login.jsp";
			}
			proxima = "index.jsp";
			break;
		default:
			proxima = "index.jsp";
			break;
		}

		request.getRequestDispatcher(proxima).forward(request, response);

	}
}
