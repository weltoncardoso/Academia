/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.bd;

import java.util.Set;

import br.aedu.anhaguera.poo.atps.dominio.Aluno;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;

public interface AlunoDAO {
	void updateQuery(String command, Aluno domain) throws DataBaseException;
	Set<Aluno> selecionar(Aluno domain) throws DataBaseException;
}
