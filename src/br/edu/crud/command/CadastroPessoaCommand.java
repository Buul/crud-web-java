package br.edu.crud.command;

import javax.servlet.http.HttpServletRequest;

import br.edu.crud.dao.CadastroDAO;

public class CadastroPessoaCommand implements Command {
	
	private String proximo;
	
	private CadastroDAO cadastroDAO;
	
	public String execute(HttpServletRequest request) {
		cadastroDAO = new CadastroDAO();
		proximo = "cadastroPessoa.jsp";
		
		
		
//		try {
//			
//		} catch (PersistenciaException e) {
//			request.setAttribute("msgErro", e.getMessage());
//		}
//		
		return proximo;
	}
	
}
