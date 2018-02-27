package br.edu.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.edu.crud.dto.UsuarioDTO;
import br.edu.crud.exception.PersistException;
import br.edu.crud.util.conectorUtil;

public class UsuarioDAO {

	public boolean validarUsuario(UsuarioDTO usuarioDTO) throws PersistException {
		try {
			Connection connector = conectorUtil.getConection();

			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM TB_USUARIO ");
			sql.append(" WHERE USUARIO = ? AND SENHA= ?");

			PreparedStatement statement = connector.prepareStatement(sql.toString());
			statement.setString(1, usuarioDTO.getUsuario());
			statement.setString(2, usuarioDTO.getSenha());

			ResultSet resultSet = statement.executeQuery();
			return resultSet.next();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			throw new PersistException(e);
		}
	}

}
