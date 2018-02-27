package br.edu.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.edu.crud.bo.UsuarioBO;
import br.edu.crud.dto.UsuarioDTO;
import br.edu.crud.exception.NegocioException;

public class LoginCommand implements Command {

	private UsuarioBO usuarioBO;

	private String proxima;

	public String execute(HttpServletRequest request) {
		proxima = "login.jsp";
		usuarioBO = new UsuarioBO();

		String usuario = request.getParameter("login");
		String senha = request.getParameter("senha");

		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setUsuario(usuario);
		usuarioDTO.setSenha(senha);

		try {
			if (usuarioBO.validarUsuario(usuarioDTO))
				proxima = "index.jsp";
		} catch (NegocioException e) {
			e.printStackTrace();
			request.setAttribute("msgErro", e.getMessage());
		}
		request.getSession().setAttribute("usuario", usuarioDTO);
		return proxima;
	}
}
