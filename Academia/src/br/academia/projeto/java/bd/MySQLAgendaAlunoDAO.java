
package br.aedu.anhaguera.poo.atps.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import br.aedu.anhaguera.poo.atps.dominio.AgendaAluno;
import br.aedu.anhaguera.poo.atps.dominio.Aluno;
import br.aedu.anhaguera.poo.atps.dominio.AtividadeAcademia;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;

public class MySQLAgendaAlunoDAO implements AgendaAlunoDAO {
	private Connection con;
	private String sql;
	private PreparedStatement query;

	@Override
	public void updateQuery(String command, AgendaAluno domain)
			throws DataBaseException {
		try {
			con = DataSource.openMysql();
			switch (command) {
			case "insert":
				sql = "INSERT INTO agenda(dataAgendamento, codAluno, codigoAtividade) VALUES(?,?,?)";
				break;
			case "update":
				sql = "UPDATE agenda SET dataAgendamento = ?, codAluno = ?, codigoAtividade = ? WHERE codAgenda = ?";
			case "delete":
				sql = "DELETE FROM agenda WHERE codAgendamento = ?";
			}

			try {
				query = con.prepareStatement(sql);

				switch (command) {
				case "insert":
					query.setString(1, domain.getData());
					query.setObject(2, domain.getCodAluno());
					query.setObject(3, domain.getCodigoAtividade());
					break;
				case "update":
					query.setString(1, domain.getData());
					query.setObject(2, domain.getCodAluno());
					query.setObject(3, domain.getCodigoAtividade());
					break;
				case "delete":
					query.setObject(1, domain.getCodigoAtividade());
					break;
				}

				int row = query.executeUpdate();

				if (row == 0) {

					throw new DataBaseException("NÃO FOI FEITO O " + command
							+ " NA TABELA AGENDA COMO DEVERIA!");
				}
			} catch (SQLException cause) {
				String msg = "";
				if (command != "insert") {
					if (command == "update") {
						msg = "PROBLEMAS AO ATUALIZAR AGENDA NO MYSQL!";
					} else if (command == "delete") {
						msg = "PROBLEMAS AO APAGAR AGENDA NO MYSQL!";
					}
				} else {
					int errorCode = cause.getErrorCode();

					switch (errorCode) {
					case 1062:
						msg = "Agenda Duplicado";
						break;

					default:
						msg = "PROBLEMAS AO INSERIR AGENDA NO MYSQL!";
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
	public Set<AgendaAluno> selecionar(Aluno domain) throws DataBaseException {
		Set<AgendaAluno> agendas = new TreeSet<AgendaAluno>();
		String sql = "SELECT * FROM agenda WHERE codAluno = ?";
		
		try(Connection con = DataSource.openMysql(); PreparedStatement query = con.prepareStatement(sql)){
			query.setLong(1, domain.getCodigo());
			ResultSet rs = query.executeQuery();
			AgendaAluno agenda;
			
			while(rs.next()){
				agenda = new AgendaAluno();
				agenda.setCodigo( rs.getLong("codAgenda") );
				agenda.setData(rs.getString("dataAgendamento") );
				agenda.setCodAluno((Aluno) rs.getObject("codAluno"));
				agenda.setCodigoAtividade((AtividadeAcademia)rs.getObject("codAtividade"));
				agendas.add(agenda);
			}
		}catch (SQLException | DataBaseException cause) {
			throw new DataBaseException(cause);
		} catch (NullPointerException cause){
			throw new DataBaseException("Não existi agendas nestas condições", cause);
		}
		return agendas;
	}

}

