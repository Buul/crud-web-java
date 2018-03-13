package br.edu.crud.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.edu.crud.bo.PessoaBO;
import br.edu.crud.dto.PessoaDTO;
import br.edu.crud.exception.NegocioException;

public class ConsultasPessoaCommand implements Command {

	private String proximo;
	
	private PessoaBO pessoaBO;

	public String execute(HttpServletRequest request) {
		proximo = "consultas.jsp";
		this.pessoaBO = new PessoaBO();
		
		try {
			List<PessoaDTO> listaPessoas = pessoaBO.listarPessoas();
			request.setAttribute("listaPessoas", listaPessoas);
		} catch (NegocioException e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proximo;
	}

}
