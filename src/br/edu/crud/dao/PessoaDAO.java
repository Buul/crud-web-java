package br.edu.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.edu.crud.dto.CidadeDTO;
import br.edu.crud.dto.EnderecoDTO;
import br.edu.crud.dto.PessoaDTO;
import br.edu.crud.dto.PreferenciaMusicalDTO;
import br.edu.crud.dto.UfDTO;
import br.edu.crud.exception.PersistException;
import br.edu.crud.util.conectorUtil;;

public class PessoaDAO {

	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	public List<UfDTO> listarUFs() throws PersistException {
		List<UfDTO> lista = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_UF");	
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				UfDTO ufDTO = new UfDTO();
				ufDTO.setIdUF(resultSet.getInt(1));
				ufDTO.setSigla(resultSet.getString(2));
				ufDTO.setDescricao(resultSet.getString(3));
				
				lista.add(ufDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lista;
	}
	
	public List<PreferenciaMusicalDTO> listarPreferencias() throws PersistException {
		List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_PREFERENCIA");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PreferenciaMusicalDTO preferenciaMusical = new PreferenciaMusicalDTO();
				preferenciaMusical.setIdPreferencia(resultSet.getInt(1));
				preferenciaMusical.setDescricao(resultSet.getString(2));
				
				listaPreferencias.add(preferenciaMusical);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaPreferencias;
	}
	
	public List<CidadeDTO> consultarCidadesPorEstado(Integer idEstado) throws PersistException {
		List<CidadeDTO> listaCidades = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_CIDADE ");
			sql.append(" WHERE COD_ESTADO = ?");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			statement.setInt(1, idEstado);
			
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				CidadeDTO cidadeDTO = new CidadeDTO();
				cidadeDTO.setIdCidade(resultSet.getInt("id_cidade"));
				cidadeDTO.setDescricao(resultSet.getString("descricao"));
				
				UfDTO ufDTO = new UfDTO();
				ufDTO.setIdUF(resultSet.getInt("cod_estado"));
				
				cidadeDTO.setUf(ufDTO);
				
				listaCidades.add(cidadeDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listaCidades;
	}
	
	public void cadastrarPreferencias(List<PreferenciaMusicalDTO> preferencias, Integer codPessoa) throws PersistException {
		Connection conexao = null;
		try {
			conexao = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TB_PREFERENCIA_PESSOA ");
			sql.append(" VALUES(?, ?)");
			
			for (PreferenciaMusicalDTO preferencia : preferencias) {
				PreparedStatement statement = conexao.prepareStatement(sql.toString());
				statement.setInt(1, preferencia.getIdPreferencia());
				statement.setInt(2, codPessoa);
				
				statement.execute();
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Método principal de cadastro da pessoa, responsável por gerenciar as
	 * chamadas aos demais métodos de inserção das entidades relacionadas.
	 * 
	 * @param pessoaDTO
	 * @throws PersistenciaException
	 */
	public void cadastrarPessoa(PessoaDTO pessoaDTO) throws PersistException {
		Connection conexao = null;
		try {
			Integer codPessoa = null;
			// Cadastra o endereço e recebe o id gerado
			Integer codEndereco = cadastrarEndereco(pessoaDTO.getEndereco());
			
			conexao = conectorUtil.getConection();
			
			/*
			 * Cadastra a pessoa com o codEndereco gerado e retorna o id da
			 * pessoa.
			 */
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TB_PESSOA(NOME, CPF, DT_NASC, SEXO, MINI_BIO, COD_ENDERECO)");
			sql.append(" VALUES(?, ?, ?, ?, ?, ?)");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, pessoaDTO.getNome());
			statement.setString(2, pessoaDTO.getCpf());
			java.sql.Date dtNasc = new java.sql.Date(dateFormat.parse(pessoaDTO.getDtNasc()).getTime());
			statement.setDate(3, dtNasc);
			statement.setString(4, String.valueOf(pessoaDTO.getSexo()));
			statement.setString(5, pessoaDTO.getMiniBio());
			statement.setInt(6, codEndereco);
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.first()) {
				codPessoa = resultSet.getInt(1);
			}
			// Chama o cadastro de preferências
			cadastrarPreferencias(pessoaDTO.getPreferencias(), codPessoa);
		} catch (ClassNotFoundException | SQLException | ParseException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Integer cadastrarEndereco(EnderecoDTO enderecoDTO) throws PersistException {
		Integer idGerado = null;
		Connection conexao = null;
		try {
			conexao = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO TB_ENDERECO(LOGRADOURO, CODIGO_CIDADE)");
			sql.append(" VALUES(?, ?)");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, enderecoDTO.getLogradouro());
			statement.setInt(2, enderecoDTO.getCidade().getIdCidade());
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			if (resultSet.first()) {
				idGerado = resultSet.getInt(1);
			}
			return idGerado;
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<PessoaDTO> listarPessoas() throws PersistException {
		List<PessoaDTO> listaPessoas = new ArrayList<>();
		Connection conexao = null;
		try {
			conexao = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT PE.ID_PESSOA, PE.NOME, PE.CPF, PE.DT_NASC, PE.SEXO,");
			sql.append("	EN.LOGRADOURO, CID.DESCRICAO AS DESC_CID, UF.DESCRICAO AS DESC_UF");
			sql.append(" FROM TB_PESSOA PE");
			sql.append(" INNER JOIN TB_ENDERECO EN");
			sql.append("	ON PE.COD_ENDERECO = EN.ID_ENDERECO");
			sql.append("		INNER JOIN TB_CIDADE CID");
			sql.append("			ON EN.CODIGO_CIDADE = CID.ID_CIDADE");
			sql.append("		INNER JOIN TB_UF UF");
			sql.append("			ON CID.COD_ESTADO = UF.ID_UF");
			sql.append(" ORDER BY PE.ID_PESSOA;");
			
			PreparedStatement statement = conexao.prepareStatement(sql.toString());
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PessoaDTO pessoaDTO = new PessoaDTO();
				pessoaDTO.setIdPessoa(resultSet.getInt("id_pessoa"));
				pessoaDTO.setNome(resultSet.getString("nome"));
				pessoaDTO.setCpf(resultSet.getString("cpf"));
				pessoaDTO.setSexo(resultSet.getString("sexo").charAt(0));
				pessoaDTO.setDtNasc(dateFormat.format(resultSet.getDate("dt_nasc")));
				
				EnderecoDTO enderecoDTO = new EnderecoDTO();
				enderecoDTO.setLogradouro(resultSet.getString("logradouro"));
				
				CidadeDTO cidadeDTO = new CidadeDTO();
				cidadeDTO.setDescricao(resultSet.getString("desc_cid"));
				
				UfDTO ufDTO = new UfDTO();
				ufDTO.setDescricao(resultSet.getString("desc_uf")); 
				
				enderecoDTO.setCidade(cidadeDTO);
				cidadeDTO.setUf(ufDTO);
				pessoaDTO.setEndereco(enderecoDTO);
				
				listaPessoas.add(pessoaDTO);
			}
		} catch (ClassNotFoundException | SQLException e) {
			throw new PersistException(e);
		} finally {
			try {
				conexao.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return listaPessoas;
	}
}
