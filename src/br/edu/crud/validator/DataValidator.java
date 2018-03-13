package br.edu.crud.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import br.edu.crud.exception.ValidationException;
import br.edu.crud.util.Constants;;

public class DataValidator implements Validator {

	public boolean validar(Map<String, Object> valores) throws ValidationException {
		String msgErro = "";
		for (String key : valores.keySet()) {
			String data = (String) valores.get(key);
			if (!"".equals(data)) {
				try {
					if (data.length() < 10) {
						msgErro += Constants.MSG_ERR_CAMPO_DATA_MENOR_RECOMENDADO.replace("?", key).concat("<br/>");
					}
					if (data.length() > 11) {				
						msgErro += Constants.MSG_ERR_CAMPO_DATA_MAIOR_RECOMENDADO.replace("?", key).concat("<br/>");
					}
					new SimpleDateFormat("dd/MM/yyyy").parse(data);
				} catch (ParseException e) {
					msgErro += Constants.MSG_ERR_CAMPO_INVALIDO.replace("?", key).concat("<br/>");
				}
			}
		}
		if (!"".equals(msgErro)) {
			throw new ValidationException(msgErro);
		}
		
		return true;
	}
	
}
