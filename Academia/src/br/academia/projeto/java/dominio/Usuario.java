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
public class Usuario extends Funcionario{
	private Long codigoUsuario;
	private String login, senha;
	private Funcionario codigoFuncionario;

	public Usuario() {
		this(null, null, null, null);
	}

	public Usuario(Long codigoUsuario, String login, String senha,
			Funcionario codigoFuncionario) {
		super();
		this.codigoUsuario = codigoUsuario;
		this.login = login;
		this.senha = senha;
		this.codigoFuncionario = codigoFuncionario;
	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Funcionario getCodigoFuncionario() {
		return this.codigoFuncionario;
	}

	public void setCodigoFuncionario(Funcionario codigoFuncionario) {
		this.codigoFuncionario = codigoFuncionario;
	}
}
