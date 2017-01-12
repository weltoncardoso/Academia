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
import java.util.HashSet;
import java.util.Set;

import br.aedu.anhaguera.poo.atps.dominio.AtividadeAcademia;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;


public class MySQLAtividadeDAO implements AtividadeAcademiaDAO{
	private Connection con;
	private String sql;
	private PreparedStatement query;
	
	@Override
	public void updateQuery(String command, AtividadeAcademia domain) throws DataBaseException {		
		try {
			con = DataSource.openMysql();
			switch (command) {
			case "insert":
				sql = "INSERT INTO atividade(nome, diaSemana, horaInicio, horaFim) VALUES(?, ?, ?, ?)";
				break;
			case "update":
				sql = "UPDATE usuario SET nome = ?, diaSemana = ?, horaInicio = ?, horaFim = ? WHERE codAtividade = ?";
			case "delete":
				sql = "DELETE FROM usuario WHERE codAtividade = ?";
			}

			try {
				query = con.prepareStatement(sql);

				switch (command) {
				case "insert":
					query.setString(1, domain.getNomeAtividade());
					query.setString(2, domain.getDiaDaSemana());
					query.setString(3, domain.getHorarioInicio());
					query.setString(3, domain.getHorarioFim());
					break;
				case "update":
					query.setString(1, domain.getNomeAtividade());
					query.setString(2, domain.getDiaDaSemana());
					query.setString(3, domain.getHorarioInicio());
					query.setString(4, domain.getHorarioFim());
					query.setLong(5, domain.getCodigo());
					break;
				case "delete":
					query.setLong(1, domain.getCodigo());
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
	public Set<AtividadeAcademia> selecionar(AtividadeAcademia domain) throws DataBaseException {
		Set<AtividadeAcademia> atividades = new HashSet<AtividadeAcademia>();
		String sql = "SELECT * FROM atividades WHERE diaSemana = ?";
		
		try(Connection con = DataSource.openMysql(); PreparedStatement query = con.prepareStatement(sql)){
			query.setObject(1, domain.getDiaDaSemana());
			ResultSet rs = query.executeQuery();
			AtividadeAcademia atividade;
			
			while(rs.next()){
				atividade = new AtividadeAcademia();
				atividade.setCodigo(rs.getLong("codAtividade"));
				atividade.setNomeAtividade(rs.getString("nome"));
				atividade.setHorarioInicio(rs.getString("horaInicio"));
				atividade.setHorarioFim(rs.getString("horaFim"));
				atividades.add(atividade);
			}
		}catch (SQLException | DataBaseException cause) {
			throw new DataBaseException(cause);
		} catch (NullPointerException cause){
			throw new DataBaseException("Não existi atividades nestas condições", cause);
		}
		return atividades;
	}
}
