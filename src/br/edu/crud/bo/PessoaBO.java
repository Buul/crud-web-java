package br.edu.crud.bo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.edu.crud.dao.PessoaDAO;
import br.edu.crud.dto.CidadeDTO;
import br.edu.crud.dto.PessoaDTO;
import br.edu.crud.exception.NegocioException;
import br.edu.crud.exception.PersistException;
import br.edu.crud.util.Constants;
import br.edu.crud.validator.CPFValidator;
import br.edu.crud.validator.DataValidator;

public class PessoaBO {
	
	private PessoaDAO pessoaDAO;
	
	public PessoaBO() {
		pessoaDAO = new PessoaDAO();
	}

	public boolean validarPessoa(PessoaDTO pessoaDTO) throws NegocioException {
		boolean isValido = true;
		try {
			if (pessoaDTO.getNome() == null || "".equals(pessoaDTO.getNome())) {
				throw new NegocioException(Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Nome"));
			}
			
			// Valida campos obg
			Map<String, Object> valores = new HashMap<>();
			valores.put("CPF", pessoaDTO.getCpf());
			if (new CPFValidator().validar(valores)) {
				isValido = true;
			}
			
			valores = new HashMap<>();
			valores.put("Data Nascimento", pessoaDTO.getDtNasc());
			if (new DataValidator().validar(valores)) {
				isValido = true;
			}
			
			if (pessoaDTO.getSexo() == ' ') {
				throw new NegocioException(Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Sexo"));
			}
			
			CidadeDTO cidade = pessoaDTO.getEndereco().getCidade();
			if (cidade.getUf().getIdUF() == null || cidade.getUf().getIdUF() == 0) {
				throw new NegocioException(Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Estado"));
			}
			
			if (cidade.getIdCidade() == null || cidade.getIdCidade() == 0) {
				throw new NegocioException(Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Cidade"));
			}
			
			if (pessoaDTO.getEndereco().getLogradouro() == null || "".equals(pessoaDTO.getEndereco().getLogradouro())) {
				throw new NegocioException(Constants.MSG_ERR_CAMPO_OBRIGATORIO.replace("?", "Logradouro"));
			}
			
			if (!isValido) {
				throw new NegocioException(Constants.MSG_ERR_PESSOA_DADOS_INVALIDOS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}

		return isValido;
	}
	
	public void cadastrarPessoa(PessoaDTO pessoaDTO) throws NegocioException {	
		try {
			pessoaDAO.cadastrarPessoa(pessoaDTO);
		} catch (PersistException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
	}

	public List<PessoaDTO> listarPessoas() throws NegocioException {
		List<PessoaDTO> listaPessoas = null;
		try {
			listaPessoas = pessoaDAO.listarPessoas();
		} catch (PersistException e) {
			e.printStackTrace();
			throw new NegocioException(e);
		}
		return listaPessoas;
	}
}
