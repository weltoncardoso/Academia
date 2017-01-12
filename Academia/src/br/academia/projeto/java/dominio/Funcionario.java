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
public class Funcionario extends Pessoa{
	private Long codigo;
	private String cargo, dataAdimissao, telefone, rg, dataNascimento;
	public Funcionario(){
		super();
	}
	
	public Funcionario(String rg, String nome,
			String cargo, String dataNascimento, String endereco,
			String telefone, String dataAdimissao){
                super(nome, endereco);
		this.cargo = cargo;
		this.dataAdimissao = dataAdimissao;
		this.telefone = telefone;
		this.rg = rg;
		this.dataNascimento = dataNascimento;
		
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigoFuncionario) {
		this.codigo = codigoFuncionario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDataAdimissao() {
		return dataAdimissao;
	}

	public void setDataAdimissao(String dataAdimissao) {
		this.dataAdimissao = dataAdimissao;
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
