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
public class Aluno extends Pessoa {
	private Long codigo;
	private String telefone, rg, dataNascimento;
	
	public Aluno() {
		this(null, null, null, null, null);
	}
	
	public Aluno(String rg, String nome, String dataNascimento,	String endereco, String telefone) {
		this.dataNascimento = dataNascimento;
		this.rg = rg;
		this.telefone = telefone;
		new Pessoa (nome, endereco );
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigoAluno) {
		this.codigo = codigoAluno;
	}
	
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}
