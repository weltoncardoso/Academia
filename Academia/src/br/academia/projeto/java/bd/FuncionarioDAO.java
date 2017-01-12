/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.bd;

import java.util.Set;

import br.aedu.anhaguera.poo.atps.dominio.Funcionario;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;

public interface FuncionarioDAO {
	boolean updateQuery(String command, Funcionario domain) throws DataBaseException;
	Set<Funcionario> selecionar(Funcionario domain) throws DataBaseException;
}

