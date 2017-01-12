/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.bd;

import java.util.Set;

import br.aedu.anhaguera.poo.atps.dominio.AtividadeAcademia;
import br.aedu.anhaguera.poo.atps.execao.DataBaseException;

public interface AtividadeAcademiaDAO {
	void updateQuery(String command, AtividadeAcademia domain) throws DataBaseException;
	Set<AtividadeAcademia> selecionar(AtividadeAcademia domain) throws DataBaseException;
}
