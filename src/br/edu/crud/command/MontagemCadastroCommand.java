package br.edu.crud.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.edu.crud.dao.CadastroDAO;
import br.edu.crud.dto.CidadeDTO;
import br.edu.crud.dto.PreferenciaMusicalDTO;
import br.edu.crud.dto.UfDTO;
import br.edu.crud.exception.PersistException;

public class MontagemCadastroCommand implements Command {
	
	private String proximo;
	
	private CadastroDAO cadastroDAO;
	
	public String execute(HttpServletRequest request) {
		cadastroDAO = new CadastroDAO();
		proximo = "cadastroPessoa.jsp";
		String getCidades = request.getParameter("getCidades");
		
		try {
			if (getCidades != null && !"".equals(getCidades)) {
				String id = request.getParameter("idEstado");
				int idEstado = Integer.parseInt(id);
				
				List<CidadeDTO> listaCidades = cadastroDAO.consultarCidadesPorEstado(idEstado);
				request.setAttribute("listaCidades", listaCidades);
			} else {
				List<UfDTO> listaUFs = cadastroDAO.listarUFs();
				List<PreferenciaMusicalDTO> listaPreferencias = cadastroDAO.listarPreferencias();
				request.getSession().setAttribute("listaUF", listaUFs);
				request.getSession().setAttribute("listaPreferencias", listaPreferencias);
			}
		} catch (PersistException e) {
			request.setAttribute("msgErro", e.getMessage());
		}
		
		return proximo;
	}
	
}
