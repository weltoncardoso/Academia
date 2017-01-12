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

import br.aedu.anhaguera.poo.atps.dominio.Aluno;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;


public class MySQLAlunoDAO implements AlunoDAO {
	private Connection con;
	private String sql;
	PreparedStatement query;

	@Override
	public void updateQuery(String command, Aluno domain)
			throws DataBaseException {
		try {
			con = DataSource.openMysql();
			switch (command) {
			case "insert":
				sql = "INSERT INTO aluno(nome, rg, dataNascimento, telefone, endereco) VALUES(?,?,?,?,?)";
				break;
			case "update":
				sql = "UPDATE aluno SET nome = ?, rg = ?, dataNascimento = ?, telefone = ?, endereco = ? WHERE codAluno = ?";
			case "delete":
				sql = "DELETE FROM aluno WHERE nome = ? dataNascimento = ?";
			}

			try {
				query = con.prepareStatement(sql);

				switch (command) {
				case "insert":
					query.setString(1, domain.getNome());
					query.setString(2, domain.getRg());
					query.setString(4, domain.getDataNascimento());
					query.setString(5, domain.getTelefone());
					query.setString(6, domain.getEndereco());
					break;
				case "update":
					query.setString(1, domain.getNome());
					query.setString(2, domain.getRg());
					query.setString(4, domain.getDataNascimento());
					query.setString(5, domain.getTelefone());
					query.setString(6, domain.getEndereco());
					query.setLong(7, domain.getCodigo());
					break;
				case "delete":
					query.setString(1, domain.getNome());
					query.setString(4, domain.getDataNascimento());
					break;
				}

				int row = query.executeUpdate();

				if (row == 0) {

					throw new DataBaseException("NÃO FOI FEITO O " + command
							+ " NA TABELA USUÁRIO COMO DEVERIA!");
				}
			} catch (SQLException cause) {
				String msg = "";
				if (command != "insert") {
					if (command == "update") {
						msg = "PROBLEMAS AO ATUALIZAR ALUNO NO MYSQL!";
					} else if (command == "delete") {
						msg = "PROBLEMAS AO APAGAR ALUNO NO MYSQL!";
					}
				} else {
					int errorCode = cause.getErrorCode();

					switch (errorCode) {
					case 1062:
						msg = "Aluno Duplicado";
						break;

					default:
						msg = "PROBLEMAS AO INSERIR ALUNO NO MYSQL!";
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
	public Set<Aluno> selecionar(Aluno domain) throws DataBaseException {
		Set<Aluno> alunos = new TreeSet<Aluno>();
		String sql = "SELECT * FROM aluno WHERE codAluno = ?";

		try (Connection con = DataSource.openMysql();
				PreparedStatement query = con.prepareStatement(sql)) {
			query.setString(1, domain.getNome());
			query.setString(2, domain.getDataNascimento());
			ResultSet rs = query.executeQuery();
			Aluno aluno;

			while (rs.next()) {
				aluno = new Aluno();
				aluno.setNome(rs.getString("nome"));
				aluno.setRg(rs.getString("rg"));
				aluno.setDataNascimento(rs.getString("dataNascimento"));
				aluno.setEndereco(rs.getString("endereco"));
				aluno.setTelefone(rs.getString("telefone"));
				alunos.add(aluno);
			}
		} catch (SQLException | DataBaseException cause) {
			throw new DataBaseException(cause);
		} catch (NullPointerException cause) {
			throw new DataBaseException("Não existi alunos nestas condições",
					cause);
		}
		return alunos;
	}
}
