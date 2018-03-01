package br.edu.crud.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.crud.command.CadastroPessoaCommand;
import br.edu.crud.command.Command;
import br.edu.crud.command.LoginCommand;
import br.edu.crud.command.MontagemCadastroCommand;

@WebServlet("/main")
public class MainServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Map<String, Command> command = new HashMap<String, Command>();

	@Override
	public void init() throws ServletException {
		command.put("login", new LoginCommand());
		command.put("montagemCadastro", new MontagemCadastroCommand());
		command.put("cadastroPessoa", new CadastroPessoaCommand());
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String proxima = null;
		try {
			proxima = verificarCommand(acao).execute(request);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getRequestDispatcher(proxima).forward(request, response);
	}
	
	private Command verificarCommand(String acao){
		Command cExecute = null;
		for (String key : command.keySet()) {
			if (key.equalsIgnoreCase(acao))
				cExecute = command.get(key);
		}
		return cExecute;
	}
}
