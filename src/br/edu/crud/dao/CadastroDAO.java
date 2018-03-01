package br.edu.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.crud.dto.CidadeDTO;
import br.edu.crud.dto.PreferenciaMusicalDTO;
import br.edu.crud.dto.UfDTO;
import br.edu.crud.exception.PersistException;
import br.edu.crud.util.conectorUtil;;

public class CadastroDAO {

	public List<UfDTO> listarUFs() throws PersistException {
		List<UfDTO> lista = new ArrayList<>();
		try {
			Connection conn = conectorUtil.getConection();
			
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_UF");
			
			PreparedStatement statement = conn.prepareStatement(sql.toString());
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
		}
		return lista;
	}
	
	public List<PreferenciaMusicalDTO> listarPreferencias() throws PersistException {
		List<PreferenciaMusicalDTO> listaPreferencias = new ArrayList<>();
		try {
			Connection conexao = conectorUtil.getConection();
			
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
		}
		return listaPreferencias;
	}
	
	public List<CidadeDTO> consultarCidadesPorEstado(Integer idEstado) throws PersistException {
		List<CidadeDTO> listaCidades = new ArrayList<>();
		try {
			Connection conexao = conectorUtil.getConection();
			
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
		}
		return listaCidades;
	}
	
}
