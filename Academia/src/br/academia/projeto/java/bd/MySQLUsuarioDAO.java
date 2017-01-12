/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import br.aedu.anhaguera.poo.atps.dominio.Usuario;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;


public class MySQLUsuarioDAO implements UsuarioDAO {
	private Connection con;
	private String sql;
	private PreparedStatement query;
	
	@Override
	public boolean authenticLogin(Usuario domain) throws DataBaseException {
		sql = "SELECT codUsuario FROM usuario WHERE login = ? AND senha = ?";
		// try-with-resources
		try(Connection con = DataSource.openMysql(); PreparedStatement query = con.prepareStatement(sql)){
			query.setString(1, domain.getLogin());
			query.setString(2, domain.getSenha());
			ResultSet rs = query.executeQuery();
			if(rs.next()){
				return true;
			}
			
			return false;
			
		}catch (SQLException | DataBaseException cause) {
			throw new DataBaseException(cause);
		} catch (NullPointerException cause){
			throw new DataBaseException("Não existi usuarios nestas condições", cause);
		}
	}

	@Override
	public void updateQuery(String command, Usuario domain) throws DataBaseException {
		try {
			con = DataSource.openMysql();
			switch (command) {
			case "insert":
				sql = "INSERT INTO usuario(login, senha, codFuncionario) VALUES(?,?,?)";
				break;
			case "update":
				sql = "UPDATE usuario SET login = ?, senha = ? WHERE codFuncionario = ?";
			case "delete":
				sql = "DELETE FROM usuario WHERE codFuncionario = ?";
			}

			try {
				query = con.prepareStatement(sql);

				switch (command) {
				case "insert":
					query.setString(1, domain.getLogin());
					query.setString(2, domain.getSenha());
					query.setObject(3, domain.getCodigoFuncionario());
					break;
				case "update":
					query.setString(1, domain.getLogin());
					query.setString(2, domain.getSenha());
					query.setObject(3, domain.getCodigoFuncionario());
					break;
				case "delete":
					query.setObject(1, domain.getCodigoFuncionario());
					break;
				}

				int row = query.executeUpdate();

				if (row == 0) {

					throw new DataBaseException(
							"NÃO FOI FEITO O " + command + " NA TABELA USUÁRIO COMO DEVERIA!");
				}
			} catch (SQLException cause) {
				String msg = "";
				if (command != "insert") {
					if(command == "update"){
						msg = "PROBLEMAS AO ATUALIZAR USUÁRIO NO MYSQL!";
					}else if (command == "delete"){
						msg = "PROBLEMAS AO APAGAR USUÁRIO NO MYSQL!";
					}
				} else {
					int errorCode = cause.getErrorCode();
				
					switch (errorCode) {
					case 1062:
						msg = "Funcionário Duplicado";
						break;

					default:
						msg = "PROBLEMAS AO INSERIR FUNCIONÁRIO NO MYSQL!";
						break;
					}
				}
				throw new DataBaseException(msg, cause);
			} finally {
				DataSource.closeMysql(con);
			}
		} catch (DataBaseException cause) {
			throw new DataBaseException(cause);
		}
	}

	@Override
	public Set<Usuario> selecionar(Usuario domain) throws DataBaseException {
		Set<Usuario> usuarios = new TreeSet<Usuario>();
		String sql = "SELECT * FROM usuario WHERE codFuncionario = ?";
		
		try(Connection con = DataSource.openMysql(); PreparedStatement query = con.prepareStatement(sql)){
			query.setObject(1, domain.getCodigoFuncionario());
			ResultSet rs = query.executeQuery();
			Usuario usuario;
			
			while(rs.next()){
				usuario = new Usuario();
				usuario.setNome(rs.getString("codUsuario"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setDataNascimento(rs.getString("dataNascimento"));
				usuario.setDataAdimissao(rs.getString("DataAdimissao"));
				usuarios.add(usuario);
			}
		}catch (SQLException | DataBaseException cause) {
			throw new DataBaseException(cause);
		} catch (NullPointerException cause){
			throw new DataBaseException("Não existi usuarios nestas condições", cause);
		}
		return usuarios;
	}
}
