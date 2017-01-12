/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.bd;

import java.util.Set;

import br.aedu.anhaguera.poo.atps.dominio.Usuario;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;

public interface UsuarioDAO{
	boolean authenticLogin(Usuario domain) throws DataBaseException;
	void updateQuery(String command, Usuario domain) throws DataBaseException;
	Set<Usuario> selecionar(Usuario domain) throws DataBaseException;
}

