package br.edu.crud.validator;

import java.util.Map;

import br.edu.crud.exception.ValidationException;
import br.edu.crud.util.Constants;
import br.edu.crud.util.Util;

public class CPFValidator implements Validator {

	@Override
	public boolean validar(Map<String, Object> valores) throws br.edu.crud.exception.ValidationException {
		String msgErro = "";
		for (String key : valores.keySet()) {
			String cpf = (String) valores.get(key);

			if (!"".equals(cpf)) {
				if (!Util.isCPF(cpf)) {
					msgErro += Constants.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
				}
				if (cpf.length() < 11) {
					msgErro += Constants.MSG_ERR_CAMPO_CPF_MENOR_RECOMENDADO.replace("?", key).concat("<br/>");
				}
				if (cpf.length() > 11) {				
					msgErro += Constants.MSG_ERR_CAMPO_CPF_MAIOR_RECOMENDADO.replace("?", key).concat("<br/>");
				}
			} else {
				msgErro += Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", key).concat("<br/>");
			}
		}
		if (!"".equals(msgErro)) {
			throw new ValidationException(msgErro);
		}

		return true;
	}

}
