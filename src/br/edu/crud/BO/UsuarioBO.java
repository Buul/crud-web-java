package br.edu.crud.BO;

import javax.servlet.http.HttpServletRequest;

import br.edu.crud.dao.UsuarioDAO;
import br.edu.crud.dto.UsuarioDTO;
import br.edu.crud.exception.NegocioException;
import br.edu.crud.exception.PersistException;

public class UsuarioBO {

	public boolean validarUsuario(HttpServletRequest request) throws NegocioException {
		boolean isValido = true;
		try {
			String usuario = request.getParameter("usuario");
			String senha = request.getParameter("senha");

			UsuarioDTO usuarioDTO = new UsuarioDTO();
			usuarioDTO.setUsuario(usuario);
			usuarioDTO.setSenha(senha);

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			isValido = usuarioDAO.validarUsuario(usuarioDTO);
		} catch (PersistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;

	}

}
