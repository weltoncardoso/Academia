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
public class AtividadeAcademia {
private Long codigo;
	private String horarioInicio, horarioFim, nomeAtividade, diaDaSemana;
	
	public AtividadeAcademia() {
		this(null, null, null, null, null);
	}
	public AtividadeAcademia(Long codigo, String horarioInicio, String horarioFim, String nomeAtividade, String diaDaSemana) {
		super();
		this.codigo = codigo;
		this.horarioInicio = horarioInicio;
		this.horarioFim = horarioFim;
		this.nomeAtividade = nomeAtividade;
		this.diaDaSemana = diaDaSemana;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getHorarioInicio() {
		return horarioInicio;
	}

	public void setHorarioInicio(String horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public String getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioFim(String horarioFim) {
		this.horarioFim = horarioFim;
	}

	public String getNomeAtividade() {
		return nomeAtividade;
	}

	public void setNomeAtividade(String nomeAtividade) {
		this.nomeAtividade = nomeAtividade;
	}

	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}
}
