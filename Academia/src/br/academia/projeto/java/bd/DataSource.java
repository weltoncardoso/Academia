/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.bd;

import br.aedu.anhaguera.poo.atps.execao.DataBaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataSource {
	private static final String DRIVER;
	private static final String URL;
	private static final String USER;
	private static final String PASS;
		static{
		DRIVER = "com.mysql.jdbc.Driver";
		URL = "jdbc:mysql://localhost:3306/academia";
		USER = "root";
		PASS = "eltim325";
	}
	
	public static Connection openMysql() throws DataBaseException{
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);
		} catch (ClassNotFoundException cause) {
			throw new DataBaseException("PROBLEMAS AO CARREGAR O DRIVER!", cause);
		} catch (SQLException cause) {
			throw new DataBaseException("PROBLEMAS AO CARREGAR NO MYSQL!", cause);
		}
	}
	
	public static void closeMysql(Connection con) throws DataBaseException{
		try {
			if(con != null){
				con.close();
			}
		} catch (SQLException cause) {
			throw new DataBaseException("PROBLEMAS AO CARREGAR NO MYSQL!", cause);
		}
	}
}
