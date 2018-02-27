package br.edu.crud.bo;

import java.util.HashMap;
import java.util.Map;

import br.edu.crud.dao.UsuarioDAO;
import br.edu.crud.dto.UsuarioDTO;
import br.edu.crud.exception.NegocioException;
import br.edu.crud.util.Constants;
import br.edu.crud.validator.LoginValidator;

public class UsuarioBO {

	public boolean validarUsuario(UsuarioDTO usuarioDTO) throws NegocioException {
		boolean isValido = true;
		try {
			Map<String, Object> valoresCampo = new HashMap<>();
			valoresCampo.put("Usuário", usuarioDTO.getUsuario());
			valoresCampo.put("Senha", usuarioDTO.getSenha());
			if (new LoginValidator().validar(valoresCampo)) {
				isValido = true;
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			isValido = usuarioDAO.validarUsuario(usuarioDTO);
			if (!isValido)
				throw new NegocioException(Constants.MSG_ERR_USUARIO_SENHA_INVALIDO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		return isValido;
	}
}
