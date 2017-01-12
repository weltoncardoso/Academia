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

import br.aedu.anhaguera.poo.atps.dominio.Funcionario;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;


public class MySQLFuncionarioDAO implements FuncionarioDAO {
	private Connection con;
	private String sql;
	PreparedStatement query;

	@Override
	public boolean updateQuery(String command, Funcionario domain) throws DataBaseException {		
		try {
			con = DataSource.openMysql();
			switch (command) {
			case "insert":
				sql = "INSERT INTO funcionario(nome, rg, cargo, dataNascimento, telefone, endereco) VALUES(?,?,?,?,?,?)";
				break;
			case "update":
				sql = "UPDATE funcionario SET nome = ?, rg = ?, cargo = ?, dataNascimento = ?, telefone = ?, endereco = ? WHERE codFuncionario = ?";
			case "delete":
				sql = "DELETE FROM funcionario WHERE nome = ? AND dataNascimento = ?";
			}

			try {
				query = con.prepareStatement(sql);

				switch (command) {
				case "insert":
					query.setString(1, domain.getNome());
					query.setString(2, domain.getRg());
					query.setString(3, domain.getCargo());
					query.setString(4, domain.getDataNascimento());
					query.setString(5, domain.getTelefone());
					query.setString(6, domain.getEndereco());
					break;
				case "update":
					query.setString(1, domain.getNome());
					query.setString(2, domain.getRg());
					query.setString(3, domain.getCargo());
					query.setString(4, domain.getDataNascimento());
					query.setString(5, domain.getTelefone());
					query.setString(6, domain.getEndereco());
					query.setLong(7, domain.getCodigo());
					break;
				case "delete":
					query.setString(1, domain.getNome());
					query.setString(2, domain.getDataNascimento());
					break;
				}

				int row = query.executeUpdate();

				if (row != 0) {
					return true;
//					throw new DataBaseException(
//							"NÃO FOI FEITO O " + command + " NA TABELA USUÁRIO COMO DEVERIA!");
				}
				
				return false;
			} catch (SQLException cause) {
				String msg = "";
				if (command != "insert") {
					if(command == "update"){
						msg = "PROBLEMAS AO ATUALIZAR FUNCIONÁRIO NO MYSQL!";
					}else if (command == "delete"){
						msg = "PROBLEMAS AO APAGAR FUNCIONÁRIO NO MYSQL!";
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
	public Set<Funcionario> selecionar(Funcionario domain) throws DataBaseException {
		Set<Funcionario> funcionarios = new TreeSet<Funcionario>();
		String sql = "SELECT * FROM funcionario WHERE nome = ? AND dataNascimento = ?";
		
		try(Connection con = DataSource.openMysql(); PreparedStatement query = con.prepareStatement(sql)){
			query.setString(1, domain.getNome());
			query.setString(2, domain.getDataNascimento());
			ResultSet rs = query.executeQuery();
			Funcionario funcionario;
			
			while(rs.next()){
				funcionario = new Funcionario();
				funcionario.setNome(rs.getString("nome"));
				funcionario.setRg(rs.getString("rg"));
				funcionario.setCargo(rs.getString("cargo"));
				funcionario.setDataAdimissao(rs.getString("dataAdimissao"));
				funcionario.setDataNascimento(rs.getString("dataNascimento"));
				funcionario.setEndereco(rs.getString("endereco"));
				funcionario.setTelefone(rs.getString("telefone"));
				funcionarios.add(funcionario);
			}
		}catch (SQLException | DataBaseException cause) {
			throw new DataBaseException(cause);
		} catch (NullPointerException cause){
			throw new DataBaseException("Não existi funcionários nestas condições", cause);
		}
		return funcionarios;
	}
}
