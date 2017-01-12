/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.execao;

public class DataBaseException extends Exception{
	
	private static final long serialVersionUID = 4824321023760598556L;

	public DataBaseException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DataBaseException(String message) {
		super(message);
	}
	
	public DataBaseException(Throwable cause) {
		super(cause);
	}

}

