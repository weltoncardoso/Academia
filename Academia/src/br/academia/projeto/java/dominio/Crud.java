/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.aedu.anhaguera.poo.atps.dominio;

/**
 *
 * @author hp user
 */
public interface Crud {
	void inserir(String codigo, Funcionario domain, Aluno domainAluno);
	
	void atualizar(String codigo, Funcionario domain, Aluno domainAluno);
	
	void apagar(String codigo);
	
	String selecionar(String codigo);

}


