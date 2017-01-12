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
public class AgendaAluno {
private Long codigo;
	private String data;
	private AtividadeAcademia codigoAtividade;
	private Aluno codAluno;
	
	public AgendaAluno() {
		this(null, null, null, null);
	}
	
	public AgendaAluno(Long codigo, String data, Aluno codAluno, AtividadeAcademia codigoAtividade) {
		super();
		this.codigo = codigo;
		this.data = data;
		this.codAluno = codAluno;
		this.codigoAtividade = codigoAtividade;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Aluno getCodAluno() {
		return codAluno;
	}

	public void setCodAluno(Aluno codAluno) {
		this.codAluno = codAluno;
	}

	public AtividadeAcademia getCodigoAtividade() {
		return codigoAtividade;
	}

	public void setCodigoAtividade(AtividadeAcademia codigoAtividade) {
		this.codigoAtividade = codigoAtividade;
	}
	
	public Boolean registrarAtividadeAluno(Long codigo){
		return false;
	}
}
