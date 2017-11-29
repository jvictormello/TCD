/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.tcd.enumeration;

/**
 *
 * @author Gabriela Mello
 */
public enum ClasseAco {

	ESCOLHA_CLASSE_ACO("Escolha a Classe do Aço", 0, 0),
	ASTM_A36("ASTM A36", 250, 500);

	private String nome;
	private Integer fyk;
	private Integer fuk;

	private ClasseAco(String nome, Integer fyk, Integer fuk) {
		this.nome = nome;
		this.fyk = fyk;
		this.fuk = fuk;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getFyk() {
		return fyk;
	}

	public void setFyk(Integer fyk) {
		this.fyk = fyk;
	}

	public Integer getFuk() {
		return fuk;
	}

	public void setFuk(Integer fuk) {
		this.fuk = fuk;
	}

	@Override
	public String toString() {
		return this.nome;
	}

}
